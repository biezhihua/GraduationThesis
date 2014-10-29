package com.bzh.gt.action.apartment;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import com.bzh.gt.bean.Bed;
import com.bzh.gt.bean.Dormitory;
import com.bzh.gt.bean.ExcelFile;
import com.bzh.gt.utils.FileIOUtil;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Apartment;
import com.opensymphony.xwork2.ActionContext;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称 ： GraduationThesis-ApartmentAction
 * 类描述 ： 公寓的控制层
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月26日 下午1:30:07
 */

@Controller
@Scope("prototype")
public class ApartmentAction extends BaseAction<Apartment> {

    static Logger logger = Logger.getLogger(ApartmentAction.class);

    private Long[] apartmentIds;
    private InputStream excelStream; // 输出流
    private String excelFileName; // 下载文件名
    private Object data; // json数据

    private static DataImportAction.Progress progress = new DataImportAction.Progress(); // 数据导出进度
    private static String tempFilePath = "";  // 临时文件路径
    @Resource
    private SessionFactory sessionFactory; // 给Thread打开Session使用的

    /**
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {
        // 查询出公寓数据
        QueryHelper queryHelper = new QueryHelper(Apartment.class, "a");
        PageBean pageBean = apartmentService.getPageBean(pageNum, pageSize, queryHelper);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        apartmentService.delete(model.getId());
        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     */
    public String addUI() throws Exception {
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 1，添加公寓
        apartmentService.save(model);
        // 2，为新创建的公寓初始化楼层和房间，还有床铺
        for (int floor = 1, top = model.getTopFloor(); floor <= top; floor++) { // 每层
            for (int roomNO = 1, max = model.getRoomNumber(); roomNO <= max; roomNO++) { // 房间
                // 3，添加寝室
                Dormitory newDor = new Dormitory();
                newDor.setName(floor + "" + (roomNO < 10 ? "0" + roomNO : roomNO));
                newDor.setLevel(floor + "");
                newDor.setApartment(model);
                dormitoryService.save(newDor);
                // 4，添加床位
                for (int bed = 1, bedsNumber = model.getBedNumber(); bed <= bedsNumber; bed++) {
                    Bed newBed = new Bed();
                    newBed.setBedNO(bed);
                    newBed.setDormitory(newDor);
                    bedService.save(newBed);
                    logger.info("为" + newDor.getName() + "寝室，增加" + newBed.getBedNO() + "号床铺");
                }
            }
        }
        return "toList";
    }

    /**
     * 概要: 转达到编辑页面
     */
    public String editUI() throws Exception {
        return "saveUI";
    }

    /**
     * 概要: 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {
        return "toList";
    }

    // ========AJAX================

    /**
     * 数据导出
     */
    public String dataExport() {
        Map<String, String> result = new HashMap<String, String>(); // 返回ajax执行结果

        // 删除上次下载的临时文件
        if (!("").equals(tempFilePath)) {
            File file = new File(tempFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
        // 临时存储路径名
        tempFilePath = ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + UUID.randomUUID();
        // 打开新session给线程使用
        new ExportExcel2003Thread(sessionFactory.openSession(), tempFilePath, progress).start();

        // 返回执行结果
        result.put("status", "success");
        data = result;
        return "json";

    }

    /**
     * 获取当前“进度”
     */
    public String getCurrentProgress() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("progress", progress.getProgress());
        data = result;
        return "json";
    }

    /**
     * 下载文件
     */
    public String download() {
        // 临时存储文件存在
        if (!("").equals(tempFilePath)) {
            File file = new File(tempFilePath);
            if (!file.exists()) {
                return "list";
            }
        }
        // 输出下载流
        try {
            excelStream = new FileInputStream(new File(tempFilePath));
            excelFileName = new String("公寓分布信息.xls".getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "download";
    }
    // ==============内部类==================
    class ExportExcel2003Thread extends Thread {

        private DataImportAction.Progress progress;
        private Session session;
        private String tempFilePath; // 存储路径

        ExportExcel2003Thread(Session session, String tempFilePath, DataImportAction.Progress progress) {
            this.session = session;
            this.tempFilePath = tempFilePath;
            this.progress = progress;
        }

        @Override
        public void run() {
            List<Apartment> apartmentList = this.getByIds(apartmentIds);

            if (apartmentList == null || apartmentList.size() == 0) {
                return;
            }

            // double保留两位
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            // 设置总行数
            progress.setCountLine(0); // 初始化
            for (Apartment apartment : apartmentList) {
                progress.setCountLine(progress.getCountLine() + apartment.getTopFloor() * apartment.getRoomNumber());
            }
            progress.setCountLine(progress.getCountLine() + 100);

            // 1，准备Excel文件
            HSSFWorkbook workbook2003 = new HSSFWorkbook(); // 创建工作簿对象
            HSSFSheet sheet = workbook2003.createSheet("公寓信息");// 创建工作表对象并命名sheet.setColumnWidth(0, 32 * 80);
            // 创建表头
            HSSFRow headerRow = sheet.createRow(0);
            // 第一列 -- 序号
            HSSFCell serialNumberHeader = headerRow.createCell(0);
            serialNumberHeader.setCellValue("序号");
            // 第二列 -- 公寓
            HSSFCell apartmentHeader = headerRow.createCell(1);
            apartmentHeader.setCellValue("楼栋");
            // 第三列 -- 房间号
            HSSFCell dormitoryHeader = headerRow.createCell(2);
            dormitoryHeader.setCellValue("房间号");
            // 第四列 -- 公寓类别（男女）
            HSSFCell sexHeader = headerRow.createCell(3);
            sexHeader.setCellValue("男/女");
            // 第五列 -- 住宿标准
            HSSFCell rankHeader = headerRow.createCell(4);
            rankHeader.setCellValue("住宿标准");
            // 第六列 -- 班级
            HSSFCell claszHeader = headerRow.createCell(5);
            claszHeader.setCellValue("班级");
            // 第七列 -- 床位1
            HSSFCell bedNo1Header = headerRow.createCell(6);
            bedNo1Header.setCellValue("床位1");
            // 第八列 -- 床位2
            HSSFCell bedNo2Header = headerRow.createCell(7);
            bedNo2Header.setCellValue("床位2");
            // 第九列 -- 床位3
            HSSFCell bedNo3Header = headerRow.createCell(8);
            bedNo3Header.setCellValue("床位3");
            // 第10列 -- 床位4
            HSSFCell bedNo4Header = headerRow.createCell(9);
            bedNo4Header.setCellValue("床位4");
            // 第11列 -- 床位5
            HSSFCell bedNo5Header = headerRow.createCell(10);
            bedNo5Header.setCellValue("床位5");
            // 第12列 -- 床位6
            HSSFCell bedNo6Header = headerRow.createCell(11);
            bedNo6Header.setCellValue("床位6");
            // 第13列 -- 床位7
            HSSFCell bedNo7Header = headerRow.createCell(12);
            bedNo7Header.setCellValue("床位7");
            // 第14列 -- 床位8
            HSSFCell bedNo8Header = headerRow.createCell(13);
            bedNo8Header.setCellValue("床位8");

            // 2，写入数据
            // 遍历集合对象，创建行和单元格
            int rowIndex = 1;
            int prgressIndex = 1;
            for (int i = 0; i < apartmentList.size(); i++) {
                Apartment apartment = apartmentList.get(i);

                // 按楼层写行
                for (int j = 1; j <= apartment.getTopFloor(); j++) {
                    List<Dormitory> dormitoryList = this.getByApartmentIdAndFloor(apartment.getId(), j);

                    for (Dormitory dormitory : dormitoryList) {
                        // 设置当前进度
                        progress.setCurrentLine(prgressIndex++);
                        progress.setProgress(Double.valueOf(decimalFormat.format((double) progress.getCurrentLine() / progress.getCountLine())) * 100 + "");
                        logger.info("处理进度:" + this.progress.getProgress() + "当前行：" + i + ",当前线程：" + Thread.currentThread());

                        // 准备写入一行数据
                        HSSFRow row = sheet.createRow(rowIndex++);
                        // 第一列 -- 序号
                        serialNumberHeader = row.createCell(0);
                        serialNumberHeader.setCellValue(rowIndex - 1);
                        // 第二列 -- 公寓
                        apartmentHeader = row.createCell(1);
                        apartmentHeader.setCellValue(apartment.getName());
                        // 第三列 -- 房间号
                        dormitoryHeader = row.createCell(2);
                        dormitoryHeader.setCellValue(dormitory.getName());
                        // 第四列 -- 公寓类别（男女）
                        sexHeader = row.createCell(3);
                        sexHeader.setCellValue(apartment.getSex());
                        // 第五列 -- 住宿标准
                        rankHeader = row.createCell(4);
                        rankHeader.setCellValue(apartment.getRank());
                        // 第六列 -- 班级
                        claszHeader = row.createCell(5);
                        claszHeader.setCellValue(dormitory.getClasz() == null ? "" : dormitory.getClasz().getName());
                        // 第七列开始 -- 输出床位信息
                        List<Bed> bedList = this.getByApartmentAndDormitory(apartment.getId(), dormitory.getId());
                        for (int n = 0; n < bedList.size(); n++) {
                            Bed bed = bedList.get(n);
                            HSSFCell bedCell = row.createCell(n + 6);
                            bedCell.setCellValue(bed.getStudent() == null ? "" : bed.getStudent().getName());
                        }
                    } // end for j
                } // end for e
            } // end for i
            // 保存到临时文件中
            this.saveTempFile(workbook2003, tempFilePath);
            // 设置处理进度
            progress.setCurrentLine(prgressIndex + 99);
            progress.setProgress(Double.valueOf(decimalFormat.format((double) progress.getCurrentLine() / progress.getCountLine())) * 100 + "");
            logger.info("处理进度:" + this.progress.getProgress());
        }

        /**
         * 把workbook2003对象的数据，存出来tempFilePath代表的临时文件中
         * @param workbook2003
         * @param tempFilePath
         * @return
         */
        private File saveTempFile(HSSFWorkbook workbook2003, String tempFilePath) {
            // 临时保存
            File tempFile = new File(tempFilePath);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(tempFile);
                workbook2003.write(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return tempFile;
        }
        private List<Apartment> getByIds(Long[] ids) {
            if (ids == null || ids.length == 0) {
                return Collections.EMPTY_LIST;
            }
            return session.createQuery(//
                    "FROM Apartment WHERE id IN (:ids)")//
                    .setParameterList("ids", ids)//
                    .list();
        }
        private List<Dormitory> getByApartmentIdAndFloor(Long id, int level) {
            return session.createQuery("FROM Dormitory d WHERE d.apartment.id=" + id + "AND d.level=" + level + " ORDER BY d.name ASC").list();
        }
        private List<Bed> getByApartmentAndDormitory(Long apartmentId, Long dormitoryId) {
            return session.createQuery("FROM Bed b WHERE b.dormitory.apartment.id=" + apartmentId + " AND b.dormitory.id=" + dormitoryId + " ORDER BY b.bedNO ASC").list();
        }
    }

    public Long[] getApartmentIds() {
        return apartmentIds;
    }

    public void setApartmentIds(Long[] apartmentIds) {
        this.apartmentIds = apartmentIds;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getTempFilePath() {
        return tempFilePath;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }
}
