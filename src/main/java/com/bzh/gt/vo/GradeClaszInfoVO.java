package com.bzh.gt.vo;

import com.bzh.gt.bean.Clasz;

import java.util.List;

/**
 * 年级-班级-信息
 * Created by Administrator on 2014/10/10.
 */
public class GradeClaszInfoVO {

    private String grade;
    private List<Clasz> claszs;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Clasz> getClaszs() {
        return claszs;
    }

    public void setClaszs(List<Clasz> claszs) {
        this.claszs = claszs;
    }
}
