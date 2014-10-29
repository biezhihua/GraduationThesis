package com.bzh.gt.vo;

import java.util.List;

/**
 * 项目名称 ： GraduationThesis-PageBean
 * 类描述 ： JSP页面所需的数据
 * 创建人 ： 别志华
 * 创建时间 ： 2014年8月3日 下午12:20:21
 */
@SuppressWarnings("rawtypes")
public class PageBean {

    // 此块变量是通过查询数据库获取的
    private int totalRecords; // 总记录数

    private List records; // 本页的数据列表

    // 此块变量是通过指定的或页面参数获取的
    private int currentPage; // 当前页
    private int pageSize; // 每页显示多少条数据

    // 此块变量是通过计算得到的
    private int pageCount; // 总页数
    private int beginPageIndex; // 页码列表开始的索引
    private int endPageIndex; // 页码列表的结束列表

    /**
     * 只接收前四个必要属性的值，会自动计算出其他三个属性的值
     */
    public PageBean(int currentPage, int pageSize, int totalRecords, List records) {
        this.totalRecords = totalRecords;
        this.records = records;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        // 计算总页码
        pageCount = (totalRecords + pageSize - 1) / pageSize;

        // 计算页码列表beginPageIndex endPageIndex
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } else {
            // 总数多于10页，则显示当前页附近的10个页码
            // 当前页附近的共10个页码
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            if (beginPageIndex < 1) {
                // 当前页码不足4个，则显示前10个
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            if (endPageIndex > pageCount) {
                // 当后面页码不足5个时，则显示后10个页码
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }


    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }
}
