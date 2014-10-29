package com.bzh.gt.action.apartment;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bzh.gt.bean.Bed;
import com.bzh.gt.bean.Student;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.bean.Dormitory;
import com.opensymphony.xwork2.ActionContext;

import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称 ： GraduationThesis-DormitoryAction
 * 类描述 ： 宿舍的控制层代码
 * 创建人 ： 别志华 F
 * 创建时间 ： 2014年7月26日 下午2:43:30
 */
@Controller
@Scope("prototype")
public class DormitoryAction extends BaseAction<Dormitory> {

    private Long apartmentId; // 前台传递过来的公寓的ID，用于对公寓进行过滤，进而筛选出
    private String dormitoryLevel;//选择公寓的楼层
    private Long dormitoryId;   //
    private List data;  // json数据
    private Integer bedNumber; // 床铺数

    /**
     * sdsd
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {

        /**f
         * 获取所有公寓的信息，存到上下文中
         * 在JSP的页面显示出一个Menu
         */
        List<Apartment> apartments = apartmentService.getAll();
        ActionContext.getContext().put("apartments", apartments);

        /**
         * 获取公寓的最高楼层
         * 在Menu中生成每栋公寓的每一层的链接
         */
        Integer topLevel = apartmentService.getTopLevel();
        ActionContext.getContext().put("topLevel", topLevel);

        /**
         * 对寝室信息进行过滤选择，由于公寓ID和楼层Level信息是同时传递过来的
         * 在此就只判断一个
         */
        if (apartmentId != null) {
            // 添加过滤条件
            QueryHelper queryHelper = new QueryHelper(Dormitory.class, "d");
            queryHelper.addWhereCondition("d.apartment.id=?", apartmentId);
            queryHelper.addWhereCondition("d.level=?", dormitoryLevel);
            queryHelper.addOrderByProperty("d.name", true);
            //
            PageBean pageBean = dormitoryService.getPageBean(pageNum, pageSize, queryHelper);
            // 压入到Struts的Map区域中
            ActionContext.getContext().getValueStack().push(pageBean);

            // 把过滤条件(公寓ID，楼层Level)保存到Session中
            ActionContext.getContext().getSession().put("_dor_filter_apartmentId", apartmentId);
            ActionContext.getContext().getSession().put("_dor_filter_level", dormitoryLevel);
        }
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        dormitoryService.delete(model.getId());
        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String addUI() throws Exception {
        // 1，准备公寓信息
        List<Apartment> apartments = apartmentService.getAll();
        // 2，放入contextMap中
        ActionContext.getContext().put("apartments", apartments);
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 1，设置所属公寓属性
        model.setApartment(apartmentService.getById(apartmentId));
        // 2，存储
        dormitoryService.save(model);
        // 3，为新寝室添加床铺
        for (int bed = 1; bed <= bedNumber; bed++) {
            Bed newBed = new Bed();
            newBed.setBedNO(bed);
            newBed.setDormitory(model);
            bedService.save(newBed);
        }
        return "toList";
    }

    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String editUI() throws Exception {
        // 1，准备公寓信息
        List<Apartment> apartments = apartmentService.getAll();
        // 2，放入contextMap中
        ActionContext.getContext().put("apartments", apartments);
        // 3，查询需要修改的数据
        Dormitory dormitory = dormitoryService.getById(model.getId());
        // 4，回显数据
        ActionContext.getContext().getValueStack().push(dormitory);
        ActionContext.getContext().getValueStack().push(apartmentId = dormitory.getApartment().getId());

        return "saveUI";
    }

    /**
     * 概要: 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {

        // 1，获取数据库中的源对象
        Dormitory dormitory = dormitoryService.getById(model.getId());
        dormitory.setName(model.getName());
        dormitory.setLevel(model.getLevel());
        dormitory.setApartment(apartmentService.getById(apartmentId));

        if (dormitory.getBeds().size() == bedNumber) {

        } else if (dormitory.getBeds().size() < bedNumber) {
            // 增加床铺
            for (int i = dormitory.getBeds().size(); i < bedNumber; i++) {
                Bed newBed = new Bed();
                newBed.setBedNO(i + 1);
                newBed.setDormitory(dormitory);
                bedService.save(newBed);
                dormitory.getBeds().add(newBed);
            }
        } else if (dormitory.getBeds().size() > bedNumber) {
            // 删除末尾的床铺
            for (int i = bedNumber, size = dormitory.getBeds().size(); i < size; i++) {
                Bed bed = dormitoryService.getBedByBedNo(model.getId(), i + 1);
                dormitory.getBeds().remove(bed);
                bedService.delete(bed.getId());
            }
        }
        // 2，更新
        dormitoryService.update(dormitory);

        return "toList";
    }

    // ============Ajax============

    /**
     * 获取床位信息
     */
    public String getBedsInfo() throws Exception {
        // 获取所有床铺信息
        QueryHelper queryHelper = new QueryHelper(Bed.class, "b");
        queryHelper.addWhereCondition(dormitoryId != null, "b.dormitory.id=?", dormitoryId);
        //
        PageBean pageBean = bedService.getPageBean(pageNum, pageSize, queryHelper);
        // 由于使用json返回，所以将不需要的关联关系设置为null
        for (int i = 0, l = pageBean.getRecords().size(); i < l; i++) {
            Bed bed = (Bed) pageBean.getRecords().get(i);
            bed.setDormitory(null);
            if (bed.getStudent() != null) {
                Student stu = bed.getStudent();
                stu.setClasz(null);
                stu.setBed(null);
                stu.setDormitory(null);
            }
        }
        data = pageBean.getRecords();
        return "json";
    }

    public String getDormitoryLevel() {
        return dormitoryLevel;
    }

    public void setDormitoryLevel(String dormitoryLevel) {
        this.dormitoryLevel = dormitoryLevel;
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

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }
}
