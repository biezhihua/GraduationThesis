package com.bzh.gt.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * 床铺
 * Created by biezhihua on 14-9-11.
 */
public class Bed implements Serializable {
    private Long id;
    private Integer bedNO; // 床铺号
    private Dormitory dormitory;// 所属寝室
    private Student student; // 床铺对应的学生

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBedNO() {
        return bedNO;
    }

    public void setBedNO(Integer bedNO) {
        this.bedNO = bedNO;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bed)) return false;

        Bed bed = (Bed) o;

        if (bedNO != null ? !bedNO.equals(bed.bedNO) : bed.bedNO != null) return false;
        if (id != null ? !id.equals(bed.id) : bed.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bedNO != null ? bedNO.hashCode() : 0);
        return result;
    }
}
