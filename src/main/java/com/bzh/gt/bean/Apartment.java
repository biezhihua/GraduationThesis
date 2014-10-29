package com.bzh.gt.bean;

import org.apache.struts2.json.annotations.JSON;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称 ： GraduationThesis-Apartment
 * 类描述 ： 公寓（楼栋）
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月25日 下午8:01:37
 */

public class Apartment implements Serializable{


    private Long id;

    /**
     * 字段:楼栋名称，例如：31栋
     */
    private String name;

    /**
     * 字段:公寓等级（高等、普通）
     */
    private String rank;

    /**
     * 字段:性别 (男生公寓/女生公寓)
     */
    private String sex;

    /**
     * 字段:最高楼层
     */
    private Integer topFloor;

    /**
     * 每层房间数
     */
    private Integer roomNumber;

    /**
     * 床铺数
     */
    private Integer bedNumber;
    // ============关联关系========
    /**
     * 字段:公寓所有的房间，公寓和宿舍的一对多关系
     */
    private Set<Dormitory> dormitories = new HashSet<Dormitory>();


    // ======方法区===============
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getTopFloor() {
        return topFloor;
    }

    public void setTopFloor(Integer topFloor) {
        this.topFloor = topFloor;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Set<Dormitory> getDormitories() {
        return dormitories;
    }

    public void setDormitories(Set<Dormitory> dormitories) {
        this.dormitories = dormitories;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apartment)) return false;

        Apartment apartment = (Apartment) o;

        if (bedNumber != null ? !bedNumber.equals(apartment.bedNumber) : apartment.bedNumber != null) return false;
        if (id != null ? !id.equals(apartment.id) : apartment.id != null) return false;
        if (name != null ? !name.equals(apartment.name) : apartment.name != null) return false;
        if (rank != null ? !rank.equals(apartment.rank) : apartment.rank != null) return false;
        if (roomNumber != null ? !roomNumber.equals(apartment.roomNumber) : apartment.roomNumber != null) return false;
        if (sex != null ? !sex.equals(apartment.sex) : apartment.sex != null) return false;
        if (topFloor != null ? !topFloor.equals(apartment.topFloor) : apartment.topFloor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (topFloor != null ? topFloor.hashCode() : 0);
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        result = 31 * result + (bedNumber != null ? bedNumber.hashCode() : 0);
        return result;
    }

    
}
