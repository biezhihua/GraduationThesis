package com.bzh.gt.action.commonInfo;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.ExcelFile;
import com.bzh.gt.utils.FileIOUtil;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;
import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by Administrator on 2014/10/18.
 */
@Component
@Scope("prototype")
public class TemplateAction extends BaseAction<ExcelFile> {

    static Logger logger = Logger.getLogger(TemplateAction.class);

    private File file; // 上传文件
    private String fileContentType; // 上传类型
    private String fileFileName;// 上传名字
    private InputStream excelStream; // 输出流
    private String excelFileName; // 下载文件名

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
            excelFile.setTemplate(true);
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

    /**
     * 下载模板
     */
    public String download() {
        ExcelFile excelFile = excelFileService.getById(model.getId());
        File file = new File(excelFile.getFilePath());
        if (!file.exists()) {
            return "list";
        }
        try {
            excelStream = new FileInputStream(file);
            excelFileName = new String(excelFile.getFileName().getBytes(), "ISO8859-1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "download";
    }

    /**
     * C(Create) R(Read) U(Update) D(Delete)
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {
        QueryHelper queryHelper = new QueryHelper(ExcelFile.class, "e");
        queryHelper.addWhereCondition("e.template=?", true);
        PageBean pageBean = excelFileService.getPageBean(pageNum, pageSize, queryHelper);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() {

        ExcelFile excelFile = excelFileService.getById(model.getId());
        File file = new File(excelFile.getFilePath());
        if (file.exists()) {
            file.delete();
        }
        excelFileService.delete(model.getId());

        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String addUI() throws Exception {
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {

        return "toList";
    }


    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
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
}
