package com.bzh.gt.action.base;

import javax.annotation.Resource;

import com.bzh.gt.action.clasz.ClaszAction;
import com.bzh.gt.bean.ExcelFile;
import com.bzh.gt.service.*;
import com.bzh.gt.utils.GenericsUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 项目名称 ： GraduationThesis-BaseAction
 * 类描述 ： 所有Action的基类，内置了service引用，ModelDriven模型、以及 一些更为通用的操作，方便Action操作。
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月13日 下午2:20:35
 */

public class BaseAction<T> implements Action, ModelDriven<T> {

    /**
     * 字段:ModelDriven支持
     */
    protected T model;
    // 当前页
    protected int pageNum = 1;
    // 每页显示多少条
    protected int pageSize = 10;

    // 注入依赖
    @Resource
    protected DepartmentService departmentService;
    @Resource
    protected RoleService roleService;
    @Resource
    protected UserService userService;
    @Resource
    protected ApartmentService apartmentService;
    @Resource
    protected DormitoryService dormitoryService;
    @Resource
    protected BedService bedService;
    @Resource
    protected ClaszService claszService;
    @Resource
    protected TeacherService teacherService;
    @Resource
    protected ExcelFileService excelFileService;
    @Resource
    protected StudentService studentService;
    @Resource
    protected PrivilegeService privilegeService;

    /**
     * @构造函数:通过反射获取model的真实类型，并构造新的T对象
     */
    @SuppressWarnings("unchecked")
    public BaseAction() {
        // 获取参数化类型的真实类型
        Class<T> clazz = (Class<T>) GenericsUtils//
                .getSuperClassGenricType(this.getClass());
        try {
            // 构造新的对象
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    @Override
    public T getModel() {
        return model;
    }


    // =================


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
