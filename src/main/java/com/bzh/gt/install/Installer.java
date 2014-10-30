package com.bzh.gt.install;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.bzh.gt.bean.Privilege;
import com.bzh.gt.bean.User;

// 将类放入容器中管理

/**
 * 最后修改成配置文件方式的
 * <p/>
 * version ：
 * 项目名称 ： GraduationThesis-Installer
 * 类描述 ：对此WEB应用程序所需的初始化数据进行添加，包括：超级管理员，权限列表（功能列表）
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月23日 下午2:31:40
 */

@Component
public class Installer {

    @Resource
    SessionFactory sessionFactory;

    /**
     * 概要: 安装超级管理员
     */
    @Transactional
    public void install_admin() {
        Session session = sessionFactory.getCurrentSession();
        User user = new User();
        user.setLoginName("administrator");
        user.setName("超级管理员");
        user.setPassword(DigestUtils.md5DigestAsHex("administrator".getBytes()));
        session.save(user);
    }

    /**
     * 概要:安装公寓管理数据
     */
    @Transactional
    public void install_apartment() {
        Session session = sessionFactory.getCurrentSession();

        Privilege menu;
        Privilege menu1;
        Privilege menu2;
        Privilege menu3;
        Privilege menu4;

        menu = new Privilege("公寓管理", "", null);
        menu1 = new Privilege("公寓信息管理", "/apartment/apartment_list", menu);
        menu2 = new Privilege("宿舍信息管理", "/apartment/dormitory_list", menu);
        menu3 = new Privilege("综合数据导入", "/apartment/dataImport_list", menu);
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);

        // 公寓
        session.save(new Privilege("公寓列表", "/apartment/apartment_list", menu1));
        session.save(new Privilege("公寓添加", "/apartment/apartment_add", menu1));
        session.save(new Privilege("公寓删除", "/apartment/apartment_delete", menu1));
        session.save(new Privilege("数据导出", "/apartment/apartment_dataExport", menu1));

        // 宿舍
        session.save(new Privilege("宿舍列表", "/apartment/dormitory_list", menu2));
        session.save(new Privilege("宿舍添加", "/apartment/dormitory_add", menu2));
        session.save(new Privilege("宿舍删除", "/apartment/dormitory_delete", menu2));
        session.save(new Privilege("宿舍修改", "/apartment/dormitory_edit", menu2));


    }


    /**
     * 概要:安装班级管理数据
     */
    @Transactional
    public void install_clasz() {
        Session session = sessionFactory.getCurrentSession();

        Privilege menu;
        Privilege menu1;
        Privilege menu2;
        Privilege menu3;
        Privilege menu4;

        menu = new Privilege("班级管理", "", null);
        menu1 = new Privilege("班级信息管理", "/clasz/clasz_list", menu);
        menu2 = new Privilege("成员信息管理", "/clasz/info_list", menu);
        menu3 = new Privilege("我的班级", "/clasz/myClasz_list", menu);
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);

        // 班级
        session.save(new Privilege("班级列表", "/clasz/clasz_list", menu1));
        session.save(new Privilege("班级添加", "/clasz/clasz_add", menu1));
        session.save(new Privilege("班级删除", "/clasz/clasz_delete", menu1));
        session.save(new Privilege("班级修改", "/clasz/clasz_edit", menu1));
        session.save(new Privilege("数据导出", "/clasz/clasz_dataExport", menu1));

        // 成员信息
        session.save(new Privilege("成员列表", "/clasz/info_list", menu2));
        session.save(new Privilege("成员添加", "/clasz/info_add", menu2));
        session.save(new Privilege("成员删除", "/clasz/info_delete", menu2));
        session.save(new Privilege("成员修改", "/clasz/info_edit", menu2));

        // 我的班级
        session.save(new Privilege("我的班级-成员列表", "/clasz/myClasz_list", menu3));
        session.save(new Privilege("我的班级-成员添加", "/clasz/myClasz_add", menu3));
        session.save(new Privilege("我的班级-成员删除", "/clasz/myClasz_delete", menu3));
        session.save(new Privilege("我的班级-成员修改", "/clasz/myClasz_edit", menu3));
        session.save(new Privilege("我的班级-数据导出", "/clasz/myClasz_dataExport", menu3));
    }

    /**
     * 概要:安装公寓管理数据
     */
    @Transactional
    public void install_dormitoryAllocation() {
        Session session = sessionFactory.getCurrentSession();

        Privilege menu;
        Privilege menu1;
        Privilege menu2;
        Privilege menu3;
        Privilege menu4;
        Privilege menu5;

        menu = new Privilege("新生宿舍分配", "", null);
        menu1 = new Privilege("待分配房间设置", "/allocation/roomSetting_list", menu);
        menu2 = new Privilege("待分配房间概览", "/allocation/overView_list", menu);
        menu3 = new Privilege("待分配班级录入及预分配", "/allocation/allocation_infoEntering", menu);
        menu4 = new Privilege("新生入住自动分配", "/allocation/allocation_checkInUI", menu);
        menu5 = new Privilege("新生入住手动分配", "/allocation/allocation_manualUI", menu);


        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(menu4);
        session.save(menu5);


        session.save(new Privilege("宿舍预分配", "/allocation/allocation_preDistribution", menu3));
        // 模板
    }

    /**
     * 概要:安装公寓管理数据
     */
    @Transactional
    public void install_commonInfo() {
        Session session = sessionFactory.getCurrentSession();

        Privilege menu;
        Privilege menu1;
        Privilege menu2;
        Privilege menu3;
        Privilege menu4;

        menu = new Privilege("公共信息", "", null);
        menu1 = new Privilege("模板管理", "/commonInfo/template_list", menu);
        session.save(menu);
        session.save(menu1);

        // 模板
        session.save(new Privilege("模板列表", "/commonInfo/template_list", menu1));
        session.save(new Privilege("模板添加", "/commonInfo/template_add", menu1));
        session.save(new Privilege("模板下载", "/commonInfo/template_download", menu1));
        session.save(new Privilege("模板删除", "/commonInfo/template_delete", menu1));
    }


    /**
     * 概要:安装系统设置数据
     */
    @Transactional
    public void install_system() {
        Session session = sessionFactory.getCurrentSession();

        Privilege menu;
        Privilege menu1;
        Privilege menu2;
        Privilege menu3;

        menu = new Privilege("系统管理", "", null);
        menu1 = new Privilege("部门管理", "/system/department_list", menu);
        menu2 = new Privilege("岗位管理", "/system/role_list", menu);
        menu3 = new Privilege("用户管理", "/system/user_list", menu);
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);

        session.save(new Privilege("部门列表", "/system/department_list", menu1));
        session.save(new Privilege("部门删除", "/system/department_delete", menu1));
        session.save(new Privilege("部门添加", "/system/department_add", menu1));
        session.save(new Privilege("部门修改", "/system/department_edit", menu1));

        session.save(new Privilege("岗位列表", "/system/role_list", menu2));
        session.save(new Privilege("岗位删除", "/system/role_delete", menu2));
        session.save(new Privilege("岗位添加", "/system/role_add", menu2));
        session.save(new Privilege("岗位修改", "/system/role_edit", menu2));
        session.save(new Privilege("设置权限", "/system/role_setPrivilege", menu2));

        session.save(new Privilege("用户列表", "/system/user_list", menu3));
        session.save(new Privilege("用户删除", "/system/user_delete", menu3));
        session.save(new Privilege("用户添加", "/system/user_add", menu3));
        session.save(new Privilege("用户修改", "/system/user_edit", menu3));
        session.save(new Privilege("初始化密码", "/system/user_initPassword", menu3));
    }


    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        Installer installer = (Installer) ac.getBean("installer");
        installer.install_admin();
        installer.install_apartment();
        installer.install_clasz();
        installer.install_dormitoryAllocation();
        installer.install_commonInfo();
        installer.install_system();
    }
}
