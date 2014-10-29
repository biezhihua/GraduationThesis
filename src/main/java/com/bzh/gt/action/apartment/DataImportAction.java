package com.bzh.gt.action.apartment;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.*;
import com.bzh.gt.utils.FileIOUtil;
import com.bzh.gt.utils.RegExpUtil;
import com.bzh.gt.vo.ExcelHeader;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 数据导入
 * Created by biezhihua on 14-9-23.
 */
@Controller
@Scope("prototype")
public class DataImportAction extends BaseAction<ExcelFile> {

    static Logger logger = Logger.getLogger(DataImportAction.class);

    private File file; // 上传文件
    private String fileContentType; // 上传类型
    private String fileFileName;// 上传名字
    private Object data; // json数据
    private static Progress progress = new Progress(); // 静态“进度”变量
    private InputStream excelStream; // 输出流
    private String excelFileName; // 下载文件名
    @Resource
    private SessionFactory sessionFactory; // 给Thread打开Session使用的

    public String list() {
        return "list";
    }

    // ============AJAX==============

    /**
     * 加载Excel中的数据到系统中
     */
    public String loadExcelInfo() {
        Map<String, String> result = new HashMap<String, String>();
        // 获取excel对象
        ExcelFile excel = excelFileService.getById(model.getId());
        File excelFile = null;// Execl文件对象
        InputStream is = null; // 输入流对象
        excelFile = new File(excel.getFilePath());
        // 文件不存在,则终止
        if (!excelFile.exists()) {
            result.put("status", "error");
            data = result;
            return "json";
        }
        try {
            is = new FileInputStream(excelFile); // 获取文件输入流
            String suffix = excel.getFileName().substring(excel.getFileName().lastIndexOf("."));
            if (".xls".equals(suffix)) { // 如果是2003版
                new LoadExcel2003Thread(progress, is, sessionFactory.openSession()).start();
            } else {
                // new LoadExcel2007Thread(progress, is).start();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
     * 获取Excel集合
     */
    public String getExcelFiles() {
        List<ExcelFile> excelFiles = excelFileService.getAll();
        data = excelFiles;
        return "json";
    }

    /**
     * 删除
     */
    public String delete() {
        Map<String, String> result = new HashMap<String, String>();

        if (model.getId() != null) {
            // 根据要删除的Id获取对象
            ExcelFile excelFile = excelFileService.getById(model.getId());

            // 删除本地存放的文件
            if (excelFile != null && !"".equals(excelFile.getFilePath())) {
                File file = new File(excelFile.getFilePath());
                // 存在则删除,不存在则继续删除数据库中的数据
                if (file.exists() && file.delete()) {
                    logger.debug("删除成功");
                    // 删除数据库中的对象
                    excelFileService.delete(model.getId());
                    result.put("status", "success");
                    result.put("deleteId", model.getId() + "");
                } else {
                    logger.debug("删除失败");
                    result.put("status", "error");
                }
            }
        } else {
            result.put("status", "error");
        }
        data = result;
        return "json";
    }

    /**
     * 下载模板
     */
    public String download() {
        String excelFilePath = ServletActionContext.getServletContext().getRealPath("/excelFile/宿舍分布一览表模板.xls");
        File file = new File(excelFilePath);
        if (!file.exists()) {
            return "list";
        }
        try {
            excelStream = new FileInputStream(file);
            excelFileName = new String("宿舍分布一览表模板.xls".getBytes(), "ISO8859-1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "download";
    }

    /**
     * 上传文件
     */
    public void upload() {
        // 响应
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 上传的路径
        String excelFilePath = ServletActionContext.getServletContext().getRealPath("/excelFile");
        logger.debug("保存路径：" + excelFilePath);

        // 封装成对象，检查目录是否存在，不存在则创建
        File uploadDirectory = new File(excelFilePath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
            logger.debug("已创建存储目录：" + uploadDirectory.exists());
        }

        // 后缀
        String suffix = fileFileName.substring(fileFileName.lastIndexOf("."));
        logger.debug("文件后缀:" + suffix);

        // UUID文件名
        String uuidFileName = UUID.randomUUID().toString() + suffix;
        logger.debug("UUID文件名：" + uuidFileName);

        // 全路径
        String filePath = excelFilePath + "/" + uuidFileName;

        // 保存文件
        if (FileIOUtil.UploadFile(file, filePath)) {
            logger.debug("文件已经保存到：" + filePath);
            // 保存对象
            ExcelFile excelFile = new ExcelFile(fileFileName, filePath, fileContentType);
            excelFileService.save(excelFile);
            logger.debug("文件对象已经保存到数据库中");
        } else {
            response.setStatus(401);
            try {
                PrintWriter out = response.getWriter();
                out.println("文件上传失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //  ======================内部类========================================

    /**
     * 内部进度类
     */
    public static class Progress {
        private int currentLine = 0; // 当前行
        private int countLine = 0; // 总行数
        private String progress = ""; // 当前进度

        public synchronized int getCurrentLine() {
            return currentLine;
        }

        public synchronized void setCurrentLine(int currentLine) {
            this.currentLine = currentLine;
        }

        public synchronized int getCountLine() {
            return countLine;
        }

        public synchronized void setCountLine(int countLine) {
            this.countLine = countLine;
        }

        public synchronized String getProgress() {
            return progress;
        }

        public synchronized void setProgress(String progress) {
            this.progress = progress;
        }
    }

    /**
     * 内部线程类，用于载入文件
     */
    class LoadExcel2003Thread extends Thread {
        private InputStream is;
        private Progress progress;
        private Session session;

        public LoadExcel2003Thread(Progress progress, InputStream is) {
            this.progress = progress;
            this.is = is;
        }

        public LoadExcel2003Thread(Progress progress, InputStream is, Session session) {
            this.progress = progress;
            this.is = is;
            this.session = session;
        }

        @Override
        public void run() {
            try {
                // 创建Excel2003文件对象
                HSSFWorkbook workbook2003 = new HSSFWorkbook(is);
                // 取出第一个工作表,索引是0
                HSSFSheet sheet = workbook2003.getSheetAt(0);
                // 开始解析行
                this.analysisLine(sheet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 循环解析行数据
         *
         * @param sheet
         */
        private boolean analysisLine(HSSFSheet sheet) {
            // double保留两位
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            // 设置总行数
            progress.setCountLine(sheet.getLastRowNum());

            // 开始循环遍历表,表头不处理,从1开始
            for (int i = 1, len = sheet.getLastRowNum(); i <= len; i++) {
                // 获取行对象
                HSSFRow row = sheet.getRow(i);

                // 设置当前进度
                progress.setCurrentLine(i);
                progress.setProgress(Double.valueOf(decimalFormat.format((double) progress.getCurrentLine() / progress.getCountLine())) * 100 + "");
                logger.info("处理进度:" + this.progress.getProgress() + "当前行：" + i + ",当前线程：" + Thread.currentThread());

                // 如果行为空则跳过对当前行的处理
                if (row == null) {
                    continue;
                }

                // 开始解析具体的列信息
                if (!this.analysisRow(row)) {
                    continue;
                }
            }
            // 关闭session
            session.close();
            return true;
        }

        /**
         * 解析行中列数据，解析出错则返回false
         */
        private boolean analysisRow(HSSFRow row) {

            /**
             * 步骤1：解析班级字段，获取班级实体
             */
            //获取Excel文件中的班级字段
            String className = this.getCellString(row.getCell(ExcelHeader.CLASS.getIndex()));

            // 信息不能匹配正则规则"A1121"，则跳过处理此行
            if (!RegExpUtil.isMatchesClass(className)) {
                return false;
            }

            // 班级对象
            // Clasz clasz = apartmentService.getByName(className);
            // 开始事务
            Clasz clasz = (Clasz) session.createQuery("FROM Clasz c WHERE c.name like '" + className + "%'").uniqueResult();
            if (clasz == null) { // 没有查询到
                clasz = new Clasz(className, "");
                clasz.setGrade("20" + className.substring(1, 3) + "级");
                claszService.save(clasz);
            }

            /**
             * 步骤2：解析公寓、寝室字段，获取寝室实体，并设置与班级实体的关联关系
             */
            // 拿到Excel行中的，公寓列、寝室列
            String apartmentName = this.getCellString(row.getCell(ExcelHeader.APARTMENT.getIndex()));

            // 截取楼栋的前两位，例如：31栋的 31
            apartmentName = apartmentName.substring(0, apartmentName.length() - 1);

            String dormitoryName = this.getCellString(row.getCell(ExcelHeader.DORMITORY.getIndex()));

            // 根据公寓名称和寝室房间号，进行模糊匹配，但是结果唯一(此处可能会出现异常),公寓+寝室号应该唯一，应该在添加寝室的地方添加验证条件
            // Dormitory dormitory = dormitoryService.getByDorNameAndByApartmentName(apartmentName, dormitoryName);
            if (apartmentName == null || dormitoryName == null || "".equals(apartmentName) || "".equals(dormitoryName)) {
                return false;
            }
            Dormitory dormitory = (Dormitory) session//
                    .createQuery("FROM Dormitory d WHERE d.apartment.name like '"
                            + apartmentName + "%' AND d.name=" + dormitoryName)//
                    .uniqueResult();

            //未查询到寝室，则代表数据后续数据无法继续存入，跳过后续步骤
            if (dormitory == null) {
                return false;
            }

            // 设置寝室与班级的关联关系，一般都在多的一方设置关联，前提一的一方已经持久化到数据库中。
            dormitory.setClasz(clasz);
            session.update(dormitory);

            /**
             * 步骤3：解析床铺字段，根据床铺上的信息，创建学生实体，并设置与寝室和班级的关联关系
             */
            ExcelHeader[] excelHeaders = ExcelHeader.values();
            // 从床铺1 遍历至 床铺8
            for (int start = ExcelHeader.BED_NO1.getIndex(), end = ExcelHeader.BED_NO8.getIndex(); start <= end; start++) {
                ExcelHeader excelHeader = excelHeaders[start];

                // 获取单元格对象
                HSSFCell cell = row.getCell(start);

                // 如果床铺没人，则读取下一个单元格
                if (cell == null || "".equals(cell.toString())) {
                    continue;
                }

                // 对床铺位的信息进行解析，如果班级前缀，则将班级、姓名剥离出来
                String strCell = cell.getStringCellValue();

                // 班级实体
                Clasz newClasz = this.getClaszEntity(strCell);

                // 床铺上的学生姓名
                String studentName = this.getStudentName(strCell);

                // 根据班级和姓名获取学生实体，不存在则创建
                // 注意：此时的student还未保存到数据库总，是否保存到数据库中是根据下面的逻辑所决定的
                Student student = this.getStudentByClaszAndByName(newClasz, clasz, studentName);

                // 设置床铺与学生的对应关系，只有学生能够拥有“床铺”时，才把学生保存到数据库中
                // List<Bed> beds = bedService.getByDormitory(dormitory.getId());
                Set<Bed> beds = dormitory.getBeds();
                this.setStudentMappingBed(excelHeader.getBedNo() <= beds.size(), excelHeader, student, beds);

            }
            return true;
        }

        /**
         * 设置床铺与学生的对应关系，只有学生能够拥有“床铺”时，才把学生保存到数据库中
         *
         * @param b
         * @param student
         * @param beds
         */
        private void setStudentMappingBed(boolean b, ExcelHeader excelHeader, Student student, Set<Bed> beds) {
            for (Bed bed : beds) {
                boolean pass = b ? bed.getBedNO() == excelHeader.getBedNo() : bed.getStudent() == null;
                if (pass) {
                    // 设置和班级的关联关系
                    // 只有新的学生才需要设置关联关系
                    if (student.getId() == null) {
                        session.getTransaction().begin();
                        session.save(student);

                        bed.setStudent(student);// 设置一对一双向关联
                        session.update(bed);
                        student.setBed(bed);
                        session.update(student);

                        session.getTransaction().commit();
                    }
                    break;
                }
            }
        }

        /**
         * 根据班级和姓名获取学生实体，如果不存在则创建新的
         *
         * @param newClasz
         * @param clasz
         * @param studentName
         * @return
         */
        private Student getStudentByClaszAndByName(Clasz newClasz, Clasz clasz, String studentName) {
//            Student student = studentService.getByClassAndName(newClasz != null ? newClasz.getId() : clasz.getId(), studentName);
            Long id = newClasz != null ? newClasz.getId() : clasz.getId();
            if (id == null || studentName == null || "".equals(studentName)) {
                return null;
            }
            Student student = (Student) session.createQuery("FROM Student s WHERE s.clasz.id=" + id + " AND s.name='" + studentName + "'").uniqueResult();

            if (student == null) {
                student = new Student();
                student.setName(studentName);
                student.setClasz(newClasz != null ? newClasz : clasz);
            }
            return student;
        }

        /**
         * 获取单元格的String格式
         */
        private String getCellString(Object cell) {
            if (cell == null) {
                return "";
            }
            if (cell.getClass().getName().equals("org.apache.poi.hssf.usermodel.HSSFCell")) {
                HSSFCell xls = ((HSSFCell) cell);
                if (xls.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    return Double.valueOf(xls.getNumericCellValue()).longValue() + "";
                } else {
                    return xls.getStringCellValue();
                }
            } else {
                XSSFCell xls = ((XSSFCell) cell);
                if (xls.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    return Double.valueOf(xls.getNumericCellValue()).longValue() + "";
                } else {
                    return xls.getStringCellValue();
                }
            }

        }

        /**
         * 获取单元格中学生的姓名
         *
         * @param strCell
         * @return
         */
        private String getStudentName(String strCell) {
            // 判断是否匹配 类似"A1121别志华" 这样的带有班级信息的单元格
            if (RegExpUtil.isMatchesSpecialStudentName(strCell)) {
                // 截取学生姓名
                return strCell.substring(5);
            }
            return strCell;
        }

        /**
         * 根据获取的字段获取到班级实体
         *
         * @param strCell
         * @return 如果匹配到A1121别志华这样的，就返回“A1121”的实体，如果不存在就新创建。没有匹配就返回null
         */
        private Clasz getClaszEntity(String strCell) {
            // 判断是否匹配 类似"A1121别志华" 这样的带有班级信息的单元格
            if (RegExpUtil.isMatchesSpecialStudentName(strCell)) {
                // 截取班级信息
                String newClaszName = strCell.substring(0, 5);
                // 根据班级信息获取实体
                Clasz newClasz = apartmentService.getByName(newClaszName);
                // 实体不存在则new新实体
                if (newClasz == null) {
                    newClasz = new Clasz(newClaszName, "");
                    newClasz.setGrade("20" + newClaszName.substring(1, 3) + "级");
                    claszService.save(newClasz);
                }
                return newClasz;
            }
            return null;
        }


    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

}
