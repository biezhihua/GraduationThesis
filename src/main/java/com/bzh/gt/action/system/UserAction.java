package com.bzh.gt.action.system;

import java.util.*;

import com.bzh.gt.bean.Clasz;
import com.opensymphony.xwork2.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import com.bzh.gt.action.base.BaseAction;
import com.bzh.gt.bean.Role;
import com.bzh.gt.bean.User;
import com.opensymphony.xwork2.ActionContext;

/**
 * 项目名称 ： GraduationThesis-UserAction
 * 类描述 ：
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月11日 下午12:45:08
 */

// 将控制层交给Spring管理
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    /**
     * 字段:选择的岗位组
     */
    private Long[] roleIds;
    private Long claszId; // 负责班级
    private Object data; // json
    /**
     * 字段:初始化密码
     */
    public static final String INIT_PASSWORD = "123456";

    /**
     * 概要: 转向到列表页面
     */
    public String list() throws Exception {
        // 1，获取全部用户数据，不进行分组
        List<User> users = userService.getCustomers();
        ActionContext.getContext().put("users", users);
        return "list";
    }

    /**
     * 概要: 重定向到列表页面（地址栏发生变化）
     */
    public String delete() throws Exception {
        userService.delete(model.getId());
        return "toList";
    }

    /**
     * 概要: 转达到添加页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String addUI() throws Exception {
        // 1，准备岗位数据
        List<Role> roles = roleService.getAll();
        ActionContext.getContext().put("roles", roles);
        // 准备班级数据
        List<Clasz> claszList = claszService.getAllOrderByName();
        ActionContext.getContext().put("claszList", claszList);

        return "saveUI";
    }

    /**
     * 概要: 执行添加数据操作，完成后重定向到列表页面
     */
    public String add() throws Exception {
        // 1，获取岗位组
        List<Role> roles = roleService.getByIds(roleIds);
        // 2，持久化数据
        model.setPassword(DigestUtils.md5DigestAsHex(INIT_PASSWORD.getBytes()));
        // 3，设置关联关系
        Set<Role> sets = new HashSet<Role>();
        sets.addAll(roles);
        model.setRoles(sets);
        model.setClasz(claszService.getById(claszId));
        userService.save(model);
        return "toList";
    }

    /**
     * 初始化密码
     */
    public String initPassword() {
        User user = userService.getById(model.getId());
        user.setPassword(DigestUtils.md5DigestAsHex(INIT_PASSWORD.getBytes()));
        return "list";
    }

    /**
     * 概要: 转达到编辑页面
     * 返回类型: 添加和编辑共用一个页面
     */
    public String editUI() throws Exception {
        // 1，准备岗位数据
        List<Role> roles = roleService.getAll();
        ActionContext.getContext().put("roles", roles);

        // 准备班级数据
        List<Clasz> claszList = claszService.getAllOrderByName();
        ActionContext.getContext().put("claszList", claszList);

        // 2，准备回显的
        User user = userService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(user);
        if (user.getClasz() != null) {
            claszId = user.getClasz().getId();
        }
        // 3，回显所属岗位
        if (user.getRoles() != null) {
            roleIds = new Long[user.getRoles().size()];
            int index = 0;
            for (Role role : user.getRoles()) {
                roleIds[index++] = role.getId();
            }
        }
        return "saveUI";
    }

    /**
     * 概要: 执行编辑数据操作，完成后重定向到列表页面
     */
    public String edit() throws Exception {
        // 1，取出数据对象
        User user = userService.getById(model.getId());
        user.setDescription(model.getDescription());
        user.setGender(model.getGender());
        user.setLoginName(model.getLoginName());
        user.setPassword(DigestUtils.md5DigestAsHex(INIT_PASSWORD.getBytes()));
        user.setName(model.getName());
        user.setPhoneNumber(model.getPhoneNumber());
        user.setQq(model.getQq());
        Set<Role> sets = new HashSet<Role>(roleService.getByIds(roleIds));
        user.setRoles(sets);
        user.setClasz(claszService.getById(claszId));
        // 3，更新到数据库
        userService.update(user);
        return "toList";
    }

    /**
     * 概要:转到登陆页面
     */
    public String loginUI() throws Exception {
        return "loginUI";
    }

    /**
     * 概要:登录
     */
    public String login() throws Exception {
        User user = userService.findByLoginNameAndPassword(model.getLoginName(), DigestUtils.md5DigestAsHex(model.getPassword().getBytes()));
        if (user == null) {
            ActionContext.getContext().put("loginError", "用户名或者密码不正确！");
            return "loginUI";
        } else {
            // 登录用户
            ActionContext.getContext().getSession().put("user", user);
            return "toIndex";
        }
    }

    /**
     * 设置个人信息界面
     *
     * @return
     */
    public String setProfileUI() {
        // 回显数据
        User user = userService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(user);
        return "setProfileUI";
    }

    public String setProfile() {
        User user = userService.getById(model.getId());
        user.setName(model.getName());
        user.setGender(model.getGender());
        user.setPhoneNumber(model.getPhoneNumber());
        user.setQq(model.getQq());
        user.setDescription(model.getDescription());
        if (!"".equals(model.getPassword())) {
            user.setPassword(DigestUtils.md5DigestAsHex(model.getPassword().getBytes()));
        }
        userService.update(user);
        ActionContext.getContext().getSession().put("user", user);
        // 返回执行状态
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "success");
        data = result;
        return "json";
    }

    /**
     * 退出登陆
     */
    public String logout() throws Exception {
        ActionContext.getContext().getSession().remove("user");
        return "logout";
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long getClaszId() {
        return claszId;
    }

    public void setClaszId(Long claszId) {
        this.claszId = claszId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
