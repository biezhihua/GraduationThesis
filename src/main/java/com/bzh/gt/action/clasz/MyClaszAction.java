package com.bzh.gt.action.clasz;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.*;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.utils.RegExpUtil;
import com.bzh.gt.vo.GradeClaszInfoVO;
import com.bzh.gt.vo.PageBean;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/10/21.
 */

@Controller
@Scope("prototype")
public class MyClaszAction extends BaseAction<Student> {

    static Logger logger = Logger.getLogger(MyClaszAction.class);

    private Long claszId;
    private String apartment_dor_bed; // 公寓#寝室-床铺
    private Object data; // json数据
    private Long apartmentId;

    /**
     * C(Create) R(Read) U(Update) D(Delete)
     * <p/>
     * 概要: 转向到列表页面
     */
    public String list() {
        // 获取到当前登陆的用户
        User user = (User) ActionContext.getContext().getSession().get("user");

        // 用户不存负责班级，则返回到list页面，并提示
        if (user.getClasz() == null) {
            ActionContext.getContext().put("claszId", claszId = null);
            return "list";
        }
        // 拿到班级ID，获取班级中的学生信息，
        claszId = user.getClasz().getId();
        if (claszId != null) {
            QueryHelper queryHelper = new QueryHelper(Student.class, "s");
            queryHelper.addWhereCondition("s.clasz.id=?", claszId);
            queryHelper.addOrderByProperty("s.sno", true);
            queryHelper.addOrderByProperty("s.name", true);
            PageBean pageBean = studentService.getPageBean(pageNum, pageSize, queryHelper);
            ActionContext.getContext().getValueStack().push(pageBean);
            ActionContext.getContext().put("claszId", claszId);
            ActionContext.getContext().put("pageNum", pageNum);
        }
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        Student student = studentService.getById(model.getId());
        // 解除与床铺之间的关联关系
        Bed bed = student.getBed();
        if (bed != null) {
            bed.setStudent(null);
            bedService.update(bed);
        }
        // 解除与班级班长之间的关联关系
        Clasz clasz = student.getClasz();
        if (student.equals(clasz.getMonitor())) {
            clasz.setMonitor(null);
            claszService.update(clasz);
        }
        studentService.delete(student.getId());

        ActionContext.getContext().put("claszId", claszId);
        ActionContext.getContext().put("pageNum", pageNum);
        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     * 返回类型: 添加和编辑共用一个页面
     */
    @Deprecated
    public String addUI() throws Exception {
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        Clasz clasz = claszService.getById(claszId);
        if (clasz != null) {
            model.setClasz(clasz);
            studentService.save(model);
        }

        // 是否符合格式 31#504-1
        if (RegExpUtil.isMatchesSpecialApartDorBed(apartment_dor_bed)) {
            String apartmentName = apartment_dor_bed.substring(0, 2) + "栋";
            String dormitoryName = apartment_dor_bed.substring(3, 6);
            String bedNo = apartment_dor_bed.substring(7, 8);
            Bed bed = bedService.getByApartmentAndDormitoryAndBed(apartmentName, dormitoryName, bedNo);
            if (bed != null) {
                Dormitory dormitory = bed.getDormitory();
                if (dormitory.getClasz() == null) {
                    dormitory.setClasz(clasz);
                    dormitoryService.update(dormitory);
                }

                if (bed.getStudent() == null) {
                    model.setBed(bed);
                    studentService.update(model);
                    bed.setStudent(model);
                    bedService.update(bed);
                }
            }

        }
        return "toList";
    }

    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
     */
    @Deprecated
    public String editUI() {
        Student student = studentService.getById(model.getId());
        Bed bed = student.getBed();
        if (bed != null) {
            Dormitory dormitory = bed.getDormitory();
            Apartment apartment = dormitory.getApartment();
            apartment_dor_bed = apartment.getName().substring(0, apartment.getName().length() - 1) + "#" + dormitory.getName() + "-" + bed.getBedNO();
        }
        ActionContext.getContext().getValueStack().push(student);
        ActionContext.getContext().put("claszId", claszId);
        ActionContext.getContext().put("pageNum", pageNum);
        return "saveUI";
    }

    /**
     * 概要: 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {
        // 更新普通数据
        Student student = studentService.getById(model.getId());
        student.setName(model.getName());
        student.setSno(model.getSno());
        student.setSex(model.getSex());
        student.setPhoneNumber(model.getPhoneNumber());
        studentService.update(student);
        ActionContext.getContext().put("claszId", claszId);
        ActionContext.getContext().put("pageNum", pageNum);
        return "toList";
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

    public Long getClaszId() {
        return claszId;
    }

    public void setClaszId(Long claszId) {
        this.claszId = claszId;
    }

    public String getApartment_dor_bed() {
        return apartment_dor_bed;
    }

    public void setApartment_dor_bed(String apartment_dor_bed) {
        this.apartment_dor_bed = apartment_dor_bed;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }
}
