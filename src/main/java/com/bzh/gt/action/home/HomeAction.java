package com.bzh.gt.action.home;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bzh.gt.action.base.BaseAction;

/**
 * 项目名称 ： GraduationThesis-HomeAction
 * 类描述 ： 主页面框架
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月23日 下午4:17:52
 */

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction<Object> {

    public String top() throws  Exception {
        return "top";
    }

    /**
     * 概要: 主页框架
     */
    public String index() throws Exception {
        return "index";
    }
}
