package com.bzh.gt.action.clasz;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.bean.Bed;
import com.bzh.gt.bean.Dormitory;
import com.bzh.gt.utils.QueryHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2014/10/29.
 */
public class ClaszBaseAction<T> extends BaseAction<T> {

    protected Long apartmentId;   // 三级联动 公寓ID
    protected Long dormitoryId;   // 三级联动 寝室ID
    protected Long bedId;         // 三级联动 床铺ID
    protected Object data;        // json数据
    protected Long claszId;       // 选择的班级ID

    /**
     * 根据公寓ID获取寝室列表 三级联动
     */
    public String getReservoirDormitory() {
        QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d")
                .addWhereCondition("d.apartment.id=?", apartmentId)//
                .addOrderByProperty("d.apartment.name", true)//
                .addOrderByProperty("d.level", false)//
                .addOrderByProperty("d.name", true);//
        List<Dormitory> dormitoryList = dormitoryService.getPageBean(pageNum, Integer.MAX_VALUE, queryHelper).getRecords();
        List<Dormitory> copyList = new ArrayList<Dormitory>();
        for (Dormitory dormitory : dormitoryList) {
            Dormitory copy = new Dormitory();
            copy.setId(dormitory.getId());
            copy.setName(dormitory.getName());
            copyList.add(copy);
        }
        data = copyList;
        return "json";
    }

    /**
     * 根据寝室ID获取空床铺的ID 三级联动
     */
    public String getEmptyBed() {
        List<Bed> bedList = bedService.getByDormitory(dormitoryId);
        List<Bed> copyList = new ArrayList<Bed>();
        for (Bed bed : bedList) {
            if (bed.getStudent() == null) {
                Bed copy = new Bed();
                copy.setId(bed.getId());
                copy.setBedNO(bed.getBedNO());
                copyList.add(copy);
            }
        }
        data = copyList;
        return "json";
    }

    /**
     * 获取公寓集合的副本
     */
    public List<Apartment> getApartmetnListCopy(Collection<Apartment> apartments) {
        List<Apartment> apartmentList = new ArrayList<Apartment>();
        for (Apartment apartment : apartments) {
            Apartment copy = new Apartment();
            copy.setId(apartment.getId());
            copy.setName(apartment.getName() + "-" + apartment.getRank());
            apartmentList.add(copy);
        }
        return apartmentList;
    }

    // ========AJAX============
    public String getApartment() {
        List<Apartment> apartments = apartmentService.getAll();
        for (Apartment apartment : apartments) {
            apartment.setDormitories(null);
        }
        data = apartments;
        return "json";
    }

    public String getDormitory() {
        List<Dormitory> dormitories = dormitoryService.getByApartmentId(apartmentId);
        for (Dormitory dormitory : dormitories) {
            dormitory.setClasz(null);
            dormitory.setApartment(null);
            dormitory.setBeds(null);
            dormitory.setMonitor(null);
        }
        data = dormitories;
        return "json";
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getClaszId() {
        return claszId;
    }

    public void setClaszId(Long claszId) {
        this.claszId = claszId;
    }
}
