package com.bzh.gt.action.clasz;

import com.bzh.gt.action.apartment.DataImportAction;
import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.*;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;
import com.opensymphony.xwork2.ActionContext;
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

import javax.annotation.Resource;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by biezhihua on 14-9-21.
 */
@Controller
@Scope("prototype")
public class ClaszAction extends BaseAction<Clasz> {

    static Logger logger = Logger.getLogger(ClaszAction.class.getClass());

    private String teacherName; // 老师姓名
    private String teacherPhoneNumber;//  老师电话
    private Long teacherId; // 教师ID
    private Long studentId; // 学生ID

    private Long[] claszIds; // 导出数据使用的班级ID数组
    private InputStream excelStream; // 输出流
    private String excelFileName; // 下载文件名
    private static DataImportAction.Progress progress = new DataImportAction.Progress();
    private Object data; // json数据
    private static String tempFilePath = "";  // 临时文件路径
    @Resource
    private SessionFactory sessionFactory; // 给Thread打开Session使用的

    /**
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {
        QueryHelper queryHelper = new QueryHelper(Clasz.class, "c");
        queryHelper.addOrderByProperty("c.name", true);
        PageBean pageBean = apartmentService.getPageBean(pageNum, pageSize, queryHelper);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        Clasz clasz = claszService.getById(model.getId());
        // 清除与宿舍的关联关系
        for (Dormitory dormitory : clasz.getDormitories()) {
            dormitory.setClasz(null);
            dormitoryService.update(dormitory);
        }
        // 清除学生与床铺的关联关系
        for (Student student : clasz.getStudents()) {
            Bed bed = bedService.getbyStudentId(student.getId());
            if (bed != null) {
                bed.setStudent(null);
                bedService.update(bed);
            }
        }
        claszService.delete(model.getId());
        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     */
    public String addUI() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);
        Map<String, String> year = new LinkedHashMap<String, String>();
        for (int i = nowYear - 5; i <= nowYear; i++) {
            year.put(i + "级", i + "级");
        }
        ActionContext.getContext().put("year", year);
        ActionContext.getContext().put("students", new ArrayList<Student>());
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 保存老师信息
        Teacher teacher = new Teacher();
        teacher.setName(teacherName);
        teacher.setPhoneNumber(teacherPhoneNumber);
        teacherService.save(teacher);
        // 保存班级信息
        model.setTeacher(teacher);
        claszService.save(model);
        return "toList";
    }

    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
     */
    @Deprecated
    public String editUI() throws Exception {
        // 准备班级数据
        Clasz clasz = claszService.getById(model.getId());
        // 准备老师数据
        if (clasz.getTeacher() != null) {
            teacherName = clasz.getTeacher().getName();
            teacherPhoneNumber = clasz.getTeacher().getPhoneNumber();
            teacherId = clasz.getTeacher().getId();
            // 回显老师的数据
            ActionContext.getContext().getValueStack().push(teacherId);
            ActionContext.getContext().getValueStack().push(teacherName);
            ActionContext.getContext().getValueStack().push(teacherPhoneNumber);
        }
        // 准备年级数据
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);
        Map<String, String> year = new LinkedHashMap<String, String>();
        for (int i = nowYear - 5; i <= nowYear; i++) {
            year.put(i + "级", i + "级");
        }
        ActionContext.getContext().put("year", year);

        // 准备学生数据
        // 无论students是否存在都放入
        List<Student> students = studentService.getByClaszId(model.getId());
        ActionContext.getContext().put("students", students);

        // 回显数据
        ActionContext.getContext().getValueStack().push(clasz);
        if (clasz.getMonitor() != null) {
            studentId = clasz.getMonitor().getId();
        }
        return "saveUI";
    }

    /**
     * 概要: 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {
        // 更新或者保存班主任
        Teacher teacher = null;
        if (teacherId == null) {
            teacher = new Teacher();
        } else {
            teacher = teacherService.getById(teacherId);
        }
        teacher.setName(teacherName);
        teacher.setPhoneNumber(teacherPhoneNumber);
        teacherService.saveOrUpdate(teacher);
        // 更新班级数据
        Clasz clasz = claszService.getById(model.getId());
        clasz.setName(model.getName());
        clasz.setGrade(model.getGrade());
        clasz.setTeacher(teacher);
        // 设置班长
        if (studentId != null) {
            Student student = studentService.getById(studentId);
            clasz.setMonitor(student);

        }
        claszService.update(clasz);

        return "toList";
    }

    /**
     * 数据导出
     */
    public String dataExport() {
        Map<String, String> result = new HashMap<String, String>();
        // 删除上次下载的临时文件
        if (!("").equals(tempFilePath)) {
            File file = new File(tempFilePath);
            if (file.exists()) {
                if (file.delete()) {
                    logger.info("文件删除成功");
                } else {
                    logger.info("文件删除失败");
                }
            }
        }
        tempFilePath = ServletActionContext.getServletContext().getRealPath("/temp") + "/" + UUID.randomUUID();
        // 开启导出线程
        new ExportExcel2003Thread(sessionFactory.openSession(), tempFilePath, progress).start();

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

    public String download() {
        if (!("").equals(tempFilePath)) {
            File file = new File(tempFilePath);
            if (!file.exists()) {
                return "list";
            }
        }

        try {
            excelStream = new FileInputStream(new File(tempFilePath));
            excelFileName = new String("班级信息情况.xls".getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "download";
    }

    // ===============JAVA==============
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
            List<Clasz> claszList = this.getByIds(claszIds);

            if (claszList == null || claszList.size() == 0) {
                return;
            }
            // double保留两位
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            // 设置总行数
            progress.setCountLine(0); // 初始化
            for (Clasz clasz : claszList) {
                progress.setCountLine(progress.getCountLine() + clasz.getNumber());
            }
            progress.setCountLine(progress.getCountLine() + 100);
            int progressIndex = 1;

            // 1，准备Excel文件
            HSSFWorkbook workbook2003 = new HSSFWorkbook(); // 创建工作簿对象

            for (Clasz clasz : claszList) {
                HSSFSheet sheet = workbook2003.createSheet(clasz.getName() + "信息统计");// 创建工作表对象并命名sheet.setColumnWidth(0, 32 * 80);
                sheet.setColumnWidth(1, 32 * 80);
                sheet.setColumnWidth(2, 32 * 80);
                sheet.setColumnWidth(3, 32 * 80);
                sheet.setColumnWidth(4, 32 * 80);
                sheet.setColumnWidth(5, 32 * 80);
                sheet.setColumnWidth(6, 32 * 80);
                // 创建表头
                HSSFRow headerRow = sheet.createRow(0);

                // 第一列 -- 学号
                HSSFCell stuNOHeader = headerRow.createCell(0);
                stuNOHeader.setCellValue("学号");

                // 第二列 -- 姓名
                HSSFCell nameHeader = headerRow.createCell(1);
                nameHeader.setCellValue("姓名");

                // 第三列 -- 性别
                HSSFCell sexHeader = headerRow.createCell(2);
                sexHeader.setCellValue("性别");

                // 第四列 -- 楼栋
                HSSFCell apartmentHeader = headerRow.createCell(3);
                apartmentHeader.setCellValue("楼栋");

                // 第五列 -- 寝室号
                HSSFCell dormitoryHeader = headerRow.createCell(4);
                dormitoryHeader.setCellValue("寝室号");

                // 第六列 -- 床位
                HSSFCell bedHeader = headerRow.createCell(5);
                bedHeader.setCellValue("床位号");

                // 第七列 -- 手机
                HSSFCell phoneHeader = headerRow.createCell(6);
                phoneHeader.setCellValue("手机号");

                int rowIndex = 1;
                for (Student student : clasz.getStudents()) {

                    // 准备写入一行数据
                    HSSFRow row = sheet.createRow(rowIndex++);

                    // 第一列 -- 学号
                    stuNOHeader = row.createCell(0);
                    stuNOHeader.setCellValue(student.getSno());

                    // 第二列 -- 姓名
                    nameHeader = row.createCell(1);
                    nameHeader.setCellValue(student.getName());

                    // 第三列 -- 性别
                    sexHeader = row.createCell(2);
                    sexHeader.setCellValue(student.getSex());

                    // 第四列 -- 楼栋
                    apartmentHeader = row.createCell(3);
                    apartmentHeader.setCellValue(student.getDormitory() == null ? "" : student.getDormitory().getApartment().getName());

                    // 第五列 -- 寝室号
                    dormitoryHeader = row.createCell(4);
                    dormitoryHeader.setCellValue(student.getDormitory() == null ? "" : student.getDormitory().getName());

                    // 第六列 -- 床位
                    bedHeader = row.createCell(5);
                    bedHeader.setCellValue(student.getBed() == null ? "" : student.getBed().getBedNO() + "");

                    phoneHeader = row.createCell(6);
                    phoneHeader.setCellValue(student.getPhoneNumber());
                    // 遍历集合对象，创建行和单元格
                    // 设置当前进度
                    progress.setCurrentLine(progressIndex++);
                    progress.setProgress(Double.valueOf(decimalFormat.format((double) progress.getCurrentLine() / progress.getCountLine())) * 100 + "");
                    logger.info("处理进度:" + this.progress.getProgress());
                }
            } // end clasz
            this.saveTempFile(workbook2003, tempFilePath);

            progress.setCurrentLine(progressIndex + 99);
            progress.setProgress(Double.valueOf(decimalFormat.format((double) progress.getCurrentLine() / progress.getCountLine())) * 100 + "");
            logger.info("处理进度:" + this.progress.getProgress());
        }

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

        private List<Clasz> getByIds(Long[] ids) {
            if (ids == null || ids.length == 0) {
                return Collections.EMPTY_LIST;
            }
            return session.createQuery(//
                    "FROM Clasz  WHERE id IN (:ids)")//
                    .setParameterList("ids", ids)//
                    .list();
        }
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public static DataImportAction.Progress getProgress() {
        return progress;
    }

    public static void setProgress(DataImportAction.Progress progress) {
        ClaszAction.progress = progress;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static String getTempFilePath() {
        return tempFilePath;
    }

    public static void setTempFilePath(String tempFilePath) {
        ClaszAction.tempFilePath = tempFilePath;
    }

    public Long[] getClaszIds() {
        return claszIds;
    }

    public void setClaszIds(Long[] claszIds) {
        this.claszIds = claszIds;
    }
}
