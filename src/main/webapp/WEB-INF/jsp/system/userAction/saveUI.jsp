<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>欢迎使用宿舍管理系统</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>
    <!-- page specific plugin styles -->
    <style type="text/css">
        .radio {
            display: inline;
        }
    </style>
</head>
<body>
<div class="main-container" id="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1 style="display:inline">用户信息${id == null ? '添加' : '编辑'}</h1>

            <div class="text-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <s:form id="form" action="user_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
                    <s:hidden name="id"></s:hidden>

                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName"> 登录名 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入登录名，此项必填" name="loginName" cssClass="col-xs-10 col-sm-5"
                                         id="loginName"></s:textfield>
                        </div>
                    </div>

                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name"> 用户名 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入用户名" name="name" cssClass="col-xs-10 col-sm-5"
                                         id="name"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-select-1"> 岗位 </label>

                        <div class="col-sm-9">
                            <s:select id="form-field-select-1" name="roleIds" cssClass="col-xs-10 col-sm-5"
                                      multiple="true" size="5" list="#roles" listKey="id" listValue="name"/>
                            <span class="help-block">按住Ctrl键可以多选或取消选择。</span>
                        </div>
                    </div>

                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-select-2">
                            所负责的班级 </label>

                        <div class="col-sm-9">
                            <s:select id="form-field-select-2" name="claszId" cssClass="col-xs-10 col-sm-5"
                                      list="#claszList" listKey="id" listValue="name"/>
                            <span class="help-block">岗位是班长的，请在此选择负责的班级。</span>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="submit">
                                <i class="icon-ok bigger-110"></i> 提交
                            </button>
                            &nbsp; &nbsp; &nbsp;
                            <button class="btn" type="reset">
                                <i class="icon-undo bigger-110"></i> 重置
                            </button>
                        </div>
                    </div>
                </s:form>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.page-content -->
</div>
<!-- /.main-container -->
<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
        $("#form").validate({
            rules: {
                loginName: {
                    required: true,
                    maxlength: 20
                },
                name: {
                    required: true
                }
            },
            messages: {
                loginName: {
                    required: "请输入登陆名",
                    maxlength: "登陆名不能多于20个字符"
                },
                name: {
                    required: "请输入姓名"
                }

            },
            submitHandler: function (form) {
                console.log("submitted");
                form.submit();
            }
        });
    });
</script>
</body>
</html>
