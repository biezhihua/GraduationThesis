<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>欢迎使用宿舍管理系统</title>

    <%@ include file="/WEB-INF/jsp/public/commons-styles.jspf" %>
    <style type="text/css">
        .container {
            width: auto;
            height: auto;
            margin: 170px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="col-lg-offset-4 col-lg-4">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="text-muted bootstrap-admin-box-title">警告！</div>
                </div>
                <div class="bootstrap-admin-panel-content text-muted" style="padding: 60px 0; text-align: center">
                    您没有该权限！<a href="${pageContext.request.contextPath}/system/user_loginUI.action"><i class="icon-reply icon-only"></i></a>
            </div>
        </div>
    </div>
</div>
<!-- /container -->
</body>
</html>
