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
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2014/10/21.
 */

@Controller
@Scope("prototype")
public class MyClaszAction extends ClaszBaseAction<Student> {

    static Logger logger = Logger.getLogger(MyClaszAction.class);

    /**
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
     */
    public String addUI() throws Exception {
        ActionContext.getContext().put("apartmentList", getApartmetnListCopy(apartmentService.getAll()));
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 为班级添加学生
        Clasz clasz = claszService.getById(claszId);
        if (clasz != null) {
            model.setClasz(clasz);
            studentService.save(model);
        }

        // 为学生设置床铺
        Bed bed = bedService.getById(bedId);
        if (bed != null) {
            Dormitory dormitory = bed.getDormitory();
            if (dormitory.getClasz() == null) {
                dormitory.setClasz(clasz);
                dormitoryService.update(dormitory);
            }
            model.setBed(bed);
            studentService.update(model);
            bed.setStudent(model);
            bedService.update(bed);
        }
        return "toList";
    }

    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String editUI() {
        // 准备三级联动 公寓列表
        ActionContext.getContext().put("apartmentList", getApartmetnListCopy(apartmentService.getAll()));
        // 准备学生数据
        Student student = studentService.getById(model.getId());
        if (student.getBed() != null) {
            //  回显所在公寓，学生数据
            ActionContext.getContext().getValueStack().push(apartmentId = student.getBed().getDormitory().getApartment().getId());
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

        // 更新与寝室的关联关系
        Clasz clasz = claszService.getById(claszId);
        Bed bed = bedService.getById(bedId);
        if (bed != null) {
            Dormitory dormitory = bed.getDormitory();
            if (dormitory.getClasz() == null) {
                dormitory.setClasz(clasz);
                dormitoryService.update(dormitory);
            }
            if (bed.getStudent() == null) {
                // 解除与之前bed的关系
                Bed oldBed = student.getBed();
                if (oldBed != null) {
                    oldBed.setStudent(null);
                    bedService.update(oldBed);
                }
                student.setBed(bed);
                studentService.update(student);
                bed.setStudent(student);
                bedService.update(bed);
            }
        }
        ActionContext.getContext().put("claszId", claszId);
        ActionContext.getContext().put("pageNum", pageNum);
        return "toList";
    }
}
