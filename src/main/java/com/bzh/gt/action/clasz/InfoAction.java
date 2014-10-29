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

import java.util.*;

/**
 * Created by Administrator on 2014/10/10.
 */
@Controller
@Scope("prototype")
public class InfoAction extends ClaszBaseAction<Student> {

    static Logger logger = Logger.getLogger(InfoAction.class);
    /**
     * 概要: 转向到列表页面
     */
    public String list() {
        /**
         * 步骤1：准备年级、班级数据
         */
        // 年级-班级信息
        List<GradeClaszInfoVO> gradeClaszInfoVOs = new ArrayList<GradeClaszInfoVO>();
        // 获取年级信息
        List<String> grades = claszService.getGradeInfo();
        for (String grade : grades) {
            GradeClaszInfoVO vo = new GradeClaszInfoVO();
            vo.setGrade(grade);
            List<Clasz> claszs = claszService.getGradeInClaszInfo(grade);
            vo.setClaszs(claszs);
            gradeClaszInfoVOs.add(vo);
        }
        ActionContext.getContext().put("gradeClaszInfoVOs", gradeClaszInfoVOs);

        /**
         * 步骤2：加载班级信息
         */
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
        // 准备待分配寝室公寓数据
        ActionContext.getContext().put("apartmentList", getApartmetnListCopy(apartmentService.getAll()));
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        Clasz clasz = claszService.getById(claszId);
        if (clasz != null) {
            model.setClasz(clasz);
            // 保存学生数据
            studentService.save(model);
        }

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
     */
    public String editUI() {
        // 准备三级联动 公寓列表
        ActionContext.getContext().put("apartmentList", getApartmetnListCopy(apartmentService.getAll()));
        // 准备学生数据
        Student student = studentService.getById(model.getId());
        if (student.getBed() != null)  {
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
