package com.bzh.gt.bean;

import java.io.Serializable;

/**
 * 表格文件
 * Created by biezhihua on 14-9-23.
 */
public class ExcelFile implements Serializable {

    private Long id;

    // 文件名
    private String fileName;

    // 文件存储的路径
    private String filePath;

    // 文件类型
    private String contentType;

    private boolean template;

    public ExcelFile() {

    }

    public ExcelFile(String fileName, String filePath, String contentType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExcelFile)) return false;

        ExcelFile excelFile = (ExcelFile) o;

        if (contentType != null ? !contentType.equals(excelFile.contentType) : excelFile.contentType != null)
            return false;
        if (fileName != null ? !fileName.equals(excelFile.fileName) : excelFile.fileName != null) return false;
        if (filePath != null ? !filePath.equals(excelFile.filePath) : excelFile.filePath != null) return false;
        if (id != null ? !id.equals(excelFile.id) : excelFile.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        return result;
    }
}
