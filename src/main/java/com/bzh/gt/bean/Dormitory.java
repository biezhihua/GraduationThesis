package com.bzh.gt.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * @version ：
 * @项目名称 ： GraduationThesis-Dormitory
 * @类描述 ： 宿舍
 * @创建人 ： 别志华
 * @创建时间 ： 2014年7月25日 下午8:23:08
 */

public class Dormitory implements Serializable {

    private Long id;
    /**
     * 字段:房间号，例如：504
     */
    private String name;

    /**
     * 字段:楼层
     */
    private String level;

    /**
     * 是否被分配
     */
    private Boolean isAllocation = false;

    // =============关联关系================
    /**
     * 字段:该宿舍属于哪个公寓，宿舍和公寓的多对一关系
     */
    private Apartment apartment;

    /**
     * 字段:宿舍所属班级
     */
    private Clasz clasz;

    /**
     * @字段:宿舍中的学生，该宿舍与学生的一对多关系
     */
    // private Set<Student> students;

    /**
     * 宿舍对应的床铺
     */
    private Set<Bed> beds;

    /**
     * 寝室长
     */

    private Student monitor;
    // ===========方法区===========

    public Set<Bed> getBeds() {
        return beds;
    }

    public void setBeds(Set<Bed> beds) {
        this.beds = beds;
    }

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }

    public Student getMonitor() {
        return monitor;
    }

    public void setMonitor(Student monitor) {
        this.monitor = monitor;
    }

    public Boolean getIsAllocation() {
        return isAllocation;
    }

    public void setIsAllocation(Boolean isAllocation) {
        this.isAllocation = isAllocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dormitory)) return false;

        Dormitory dormitory = (Dormitory) o;

        if (id != null ? !id.equals(dormitory.id) : dormitory.id != null) return false;
        if (level != null ? !level.equals(dormitory.level) : dormitory.level != null) return false;
        if (name != null ? !name.equals(dormitory.name) : dormitory.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
