package com.bzh.gt.interceptor;


import com.bzh.gt.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by Administrator on 2014/10/16.
 */
public class CheckPrivilegeInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        // 获取信息
        User user = (User) ActionContext.getContext().getSession().get("user");
        String nameSpace = actionInvocation.getProxy().getNamespace() == "/" ? "" : actionInvocation.getProxy().getNamespace() +"/";
        String actionName = actionInvocation.getProxy().getActionName();
        String privUrl = nameSpace + actionName;
        // 如果未登陆，就转到登陆页面
        if (user == null) {
            // 如果是去登陆
            if (privUrl.startsWith("/system/user_login")) {
                return actionInvocation.invoke();
            } else {
                return "loginUI";
            }
        } else {
            // 如果登陆了，就判断权限
            if (user.hasPrivilegeByUrl(privUrl)) {
                // 如果有权限就放行
                return actionInvocation.invoke();
            } else {
                // 如果乜有权限，就转到提示页面
                return "noPrivilegeError";
            }
        }
    }
}
