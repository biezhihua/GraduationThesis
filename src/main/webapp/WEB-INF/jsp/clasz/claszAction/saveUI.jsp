<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>班级管理</title>
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
            <h1 style="display:inline;">班级信息${id == null ? '添加' : '编辑'}</h1>

            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <s:form action="clasz_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
                    <s:hidden name="id"></s:hidden>
                    <s:hidden name="teacherId" value="%{teacherId}"></s:hidden>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="clasz"> 班级名称 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入班级名称，例如：A1121，此项必填" name="name" cssClass="col-xs-10 col-sm-5"
                                         id="clasz"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="grade"> 所在年级 </label>

                        <div class="col-sm-9">
                            <s:select list="#year" headerKey="" name="grade" id="grade"
                                      headerValue="===请选择年级==="></s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="student"> 班长 </label>

                        <div class="col-sm-9">
                                <s:select list="#students" headerKey="" name="studentId" id="student" listKey="%{id}"
                                          listValue="%{name}"
                                          headerValue="===请选择班长==="></s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="teacherName"> 班主任姓名 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入班主任姓名" name="teacherName" cssClass="col-xs-10 col-sm-5"
                                         id="teacherName"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="teacherPhoneNumber"> 班主任电话 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入班主任电话" name="teacherPhoneNumber" cssClass="col-xs-10 col-sm-5"
                                         id="teacherPhoneNumber"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
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

</script>
</body>
</html>
