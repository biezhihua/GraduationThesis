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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jquery-ui-1.10.3.full.min.css"/>

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
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->


                <div id="user-profile-3" class="user-profile row">
                    <div class="col-sm-offset-1 col-sm-10">

                        <s:form id="form" cssClass="form-horizontal" action="user_setProfile">
                            <s:hidden name="id" value="%{id}"/>
                            <div class="tabbable">
                                <ul class="nav nav-tabs padding-16">
                                    <li class="active">
                                        <a data-toggle="tab" href="#edit-basic">
                                            <i class="green icon-edit bigger-125"></i>
                                            基本信息
                                        </a>
                                    </li>

                                    <li>
                                        <a data-toggle="tab" href="#edit-password">
                                            <i class="blue icon-key bigger-125"></i>
                                            密码
                                        </a>
                                    </li>
                                </ul>

                                <div class="tab-content profile-edit-tab-content">
                                    <div id="edit-basic" class="tab-pane in active">
                                        <h4 class="header blue bolder smaller">常规</h4>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right "
                                                   for="form-field-username">登录名</label>

                                            <div class="col-sm-9 ">
                                                <input type="text" disabled="disabled" class="col-sm-6"
                                                       id="form-field-username"
                                                       name="loginName" value="${loginName}"/>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-name">姓名</label>

                                            <div class="col-sm-9">
                                                <s:textfield cssClass="col-sm-6" id="form-field-name"
                                                             name="name"></s:textfield>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-role"> 岗位 </label>

                                            <div class="col-sm-9">

                                                <input type="text" class="col-sm-6" id="form-field-role"
                                                       disabled="disabled"
                                                       value="<s:iterator value='roles'><s:property value='name'/>/</s:iterator>"/>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-clasz">
                                                所负责的班级 </label>

                                            <div class="col-sm-9">
                                                <input type="text" class="col-sm-6" id="form-field-clasz"
                                                       disabled="disabled"
                                                       value="${clasz.name}"/>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"> 性别 </label>

                                            <div class="col-sm-9">
                                                <div class="control-group">
                                                    <div class="radio">
                                                        <label>
                                                            <input name="gender" type="radio" class="ace" value="男"
                                                                   <s:if test='gender=="男"'>checked="checked"</s:if>   />
                                                            <span class="lbl"> 男 </span>
                                                        </label>
                                                    </div>

                                                    <div class="radio">
                                                        <label>
                                                            <input name="gender" type="radio" value="女" class="ace"
                                                                   <s:if test='gender=="女"'>checked="checked"</s:if>
                                                                    />
                                                            <span class="lbl"> 女 </span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right" for="phoneNumber">
                                                手机号 </label>

                                            <div class="col-sm-9">
                                                <s:textfield placeholder="请输入手机号" name="phoneNumber"
                                                             cssClass="col-sm-6"
                                                             id="phoneNumber"></s:textfield>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right" for="qq"> QQ </label>

                                            <div class="col-sm-9">
                                                <s:textfield placeholder="请输入QQ号" name="qq"
                                                             cssClass="col-sm-6"
                                                             id="qq"></s:textfield>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="bootstrap-editor"> 个人描述 </label>

                                            <div class="col-sm-9">
                                                <s:textarea name="description" id="bootstrap-editor"
                                                            cssClass="col-sm-6"
                                                            placeholder="输入个人描述"></s:textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="edit-password" class="tab-pane">
                                        <h4 class="header blue bolder smaller">新密码</h4>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-pass1">新密码</label>

                                            <div class="col-sm-9">
                                                <input type="password" id="form-field-pass1" name="password"/>
                                            </div>
                                        </div>

                                        <div class="space-4"></div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-pass2">确认密码</label>

                                            <div class="col-sm-9">
                                                <input type="password" id="form-field-pass2"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-info" type="button">
                                        <i class="icon-ok bigger-110"></i>
                                        保存
                                    </button>

                                    &nbsp; &nbsp;
                                    <button class="btn" type="reset">
                                        <i class="icon-undo bigger-110"></i>
                                        重置
                                    </button>
                                </div>
                            </div>
                        </s:form>
                    </div>
                    <!-- /span -->
                </div>
                <!-- /user-profile -->

                <!-- PAGE CONTENT ENDS -->
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
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootbox.min.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {
        $("button[type=button]").click(function () {
            $.ajax({
                type: "POST",
                url: "user_setProfile.action",
                data: $("#form").serialize(),
                success: function (result) {
                    if (result.status === "success") {
                        var parent = $(window.parent.document);
                        parent.find("#top").load("../home_top.action");
                        bootbox.dialog({
                            message: "<div class='center'>信息修改成功!</div>",
                            buttons: {
                                "success": {
                                    "label": "确认",
                                    "className": "btn-sm btn-primary"
                                }
                            }
                        });
                    }
                }
            })
        });
    });
</script>
</body>
</html>
