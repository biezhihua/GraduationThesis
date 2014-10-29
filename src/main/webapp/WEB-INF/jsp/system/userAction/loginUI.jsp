<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>登录页面</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%@ include file="/WEB-INF/jsp/public/commons-styles.jspf" %>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
    </style>
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="center" style="padding-left:60px;padding-bottom: 60px">
                    <h1 style="display: inline">
                        <i class="icon-leaf green"></i>
                        <span class="red">数字校园-学生宿舍管理系统</span>
                    </h1>
                    <h5 style="display: inline" class="blue">&copy;2014 别志华</h5>
                </div>

                <div class="login-container">
                    <div class="position-relative">
                        <div id="login-box" class="panel panel-default">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        请输入你的登陆信息
                                    </h4>

                                    <div class="space-6"></div>

                                    <s:form role="form" action="user_login" namespace="/system">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="loginName" type="text" class="form-control"
                                                                   placeholder="登录名"/>
															<i class="icon-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="password" type="password" class="form-control"
                                                                   placeholder="密码"/>
															<i class="icon-lock"></i>
														</span>
                                            </label>
                                                ${loginError}
                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"> 记住我的信息</span>
                                                </label>

                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    登录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </s:form>
                                </div>
                                <!-- /widget-main -->
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <!-- /login-box -->
                    </div>
                    <!-- /position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
    </div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@ include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>

<!-- inline scripts related to this page -->

<script type="text/javascript">

     if (window.parent != window) {
         window.parent.location.reload();
     }

    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#' + id).addClass('visible');
    }
</script>
</body>
</html>
