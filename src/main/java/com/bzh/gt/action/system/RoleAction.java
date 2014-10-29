package com.bzh.gt.action.system;

import java.util.HashSet;
import java.util.List;

import com.bzh.gt.bean.Privilege;
import com.opensymphony.xwork2.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Department;
import com.bzh.gt.bean.Role;
import com.bzh.gt.utils.DepartmentUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 项目名称 ： GraduationThesis-RoleAction
 * 类描述 ： 岗位/角色控制层
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月22日 上午12:19:50
 */

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    private Long departmentId; // 岗位所属部门
    private Long[] privilegeIds;    // 权限集合

    /**
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {
        // 1，获取所有岗位
        List<Role> roles = roleService.getAll();
        // 2，放入Struts值栈中
        ActionContext.getContext().put("roles", roles);
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        roleService.delete(model.getId());
        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String addUI() throws Exception {
        // 1，准备树状部门
        List<Department> topList = departmentService.getTopList();
        List<Department> departmentListTree = DepartmentUtil.getListTree(topList, DepartmentUtil.MANUAL);
        ActionContext.getContext().put("departmentListTree", departmentListTree);
        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 1，根据部门ID获取实体
        Department department = departmentService.getById(departmentId);
        // 2，持久化对象
        model.setDepartment(department);
        roleService.save(model);
        return "toList";
    }

    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String editUI() throws Exception {
        // 1，准备树状部门
        List<Department> topList = departmentService.getTopList();
        List<Department> departmentListTree = DepartmentUtil.getListTree(topList, DepartmentUtil.MANUAL);
        ActionContext.getContext().put("departmentListTree", departmentListTree);
        // 2，根据model.id获取回显的数据
        Role role = roleService.getById(model.getId());
        // 3，压到栈顶
        ActionContext.getContext().getValueStack().push(role);
        // 4，回显部门
        departmentId = role.getDepartment().getId();
        return "saveUI";
    }

    /**
     * 概要: 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {
        // 1，获取要被更新的对象
        Role role = roleService.getById(model.getId());
        // 2，设置属性
        role.setName(model.getName());
        role.setDescription(model.getDescription());
        role.setDepartment(departmentService.getById(departmentId));
        // 3，更新
        roleService.update(role);
        return "toList";
    }

    /**
     * 设置权限页面
     */
    public String setPrivilegeUI() {
        // 1，准备回显的数据
        Role role = roleService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(role);
        // 2，回显拥有的权限
        if (role.getPrivileges() != null) {
            privilegeIds = new Long[role.getPrivileges().size()];
            int index = 0;
            for (Privilege privilege : role.getPrivileges()) {
                privilegeIds[index++] = privilege.getId();
            }
        }
        // 3，准备所有权限数据
        List<Privilege> privileges = privilegeService.getAll();
        ActionContext.getContext().put("privileges", privileges);
        return "setPrivilegeUI";
    }

    /**
     * 设置权限页面
     */
    public String setPrivilege() {
        // 1，从数据库中获取原对象
        Role role = roleService.getById(model.getId());
        // 2，设置要修改的属性
        List<Privilege> privileges = privilegeService.getByIds(privilegeIds);
        role.setPrivileges(new HashSet<Privilege>(privileges));
        // 3，更新到数据库
        roleService.update(role);
        return "toList";
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(Long[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
