package com.bzh.gt.action.system;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Department;
import com.bzh.gt.utils.DepartmentUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 项目名称 ： GraduationThesis-DepartmentAction
 * 类描述 ：部门的控制流转
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月12日 下午8:34:45
 */

// 将控制层交给Spring管理
@Controller
// 多例对象
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

    /**
     * 上级部门ID
     */
    private Long parentId;

    /**
     * 转向到列表页面
     */
    public String list() throws Exception {

        // 1， 获取部门列表
        List<Department> topList = departmentService.getTopList(); // 顶级部门
        List<Department> departmentListTree = DepartmentUtil.getListTree(topList, DepartmentUtil.AUTO);

        // 2，将其放入值栈上下文的Map区域中
        ActionContext.getContext().put("departmentListTree", departmentListTree);
        return "list";
    }

    /**
     * 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        departmentService.delete(model.getId());
        return "toList";
    }

    /**
     * 转达到添加页面
     * 添加和编辑共用一个页面
     */
    public String addUI() throws Exception {
        // 1，获取顶级部门列表
        List<Department> topList = departmentService.getTopList();
        // 2，获取部门树状列表
        List<Department> departmentListTree = DepartmentUtil.getListTree(topList, DepartmentUtil.MANUAL);
        // 2，将其放入值栈上下文的Map区域中
        ActionContext.getContext().put("departmentListTree", departmentListTree);
        return "saveUI";
    }

    /**
     * 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 1，根据上级部门ID获取上级部门
        Department parent = departmentService.getById(parentId);
        // 2，给新部门设置上级部门的关联关系
        model.setParent(parent);
        // 3，持久化新部门到数据库
        departmentService.save(model);
        return "toList";
    }

    /**
     * 转达到编辑页面
     * 添加和编辑共用一个页面
     */
    public String editUI() throws Exception {
        // 1，获取顶级部门列表
        List<Department> topList = departmentService.getTopList();
        // 2，获取部门树状列表
        List<Department> departmentListTree = DepartmentUtil.getListTree(topList, DepartmentUtil.MANUAL);
        // 3，将其放入值栈上下文的Map区域中
        ActionContext.getContext().put("departmentListTree", departmentListTree);
        // 4，准备回显数据，根据model.id获取需要被回显的数据
        Department department = departmentService.getById(model.getId());
        // 5，将需要回显的数据压入strutsVS栈顶
        ActionContext.getContext().getValueStack().push(department);
        // 6，回显上级部门
        if (department.getParent() != null) {
            parentId = department.getParent().getId();
        }
        return "saveUI";
    }

    /**
     * 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {
        // 需要将修改的信息同步到数据库中
        // 1，根据id获取数据库中的记录到对象中
        Department department = departmentService.getById(model.getId());
        // 2，设置要修改的属性
        department.setName(model.getName());
        department.setDescription(model.getDescription());
        // 3，设置上级部门关联关系
        department.setParent(departmentService.getById(parentId));
        // 4，同步到数据库中
        departmentService.update(department);

        return "toList";
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
