<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>待分配班级信息管理</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->
    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>
    <!-- page specific plugin styles -->
</head>

<body>

<div class="main-container" id="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1 style="display:inline;">待分配班级信息${id == null ? '添加' : '编辑'}</h1>

            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <s:form id="form" action="allocation_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
                    <s:hidden name="id"></s:hidden>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="claszname"> 班级名称 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入班级名称" name="name" cssClass="col-xs-10 col-sm-5"
                                         id="claszname"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="mans"> 男生人数 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="男生人数" name="manReservoir" cssClass="col-xs-10 col-sm-5"
                                         id="mans"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="womans"> 女生人数 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="女生人数" name="womanReservoir" cssClass="col-xs-10 col-sm-5"
                                         id="womans"></s:textfield>
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
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    $().ready(function () {
        $("#form").validate({
            rules: {
                name: {
                    required: true,
                    jjuClaszName: true
                },
                manReservoir: {
                    required: true,
                    number: true
                },
                womanReservoir: {
                    required: true,
                    number: true
                }

            },
            messages: {
                name: {
                    jjuClaszName: "请填写正确的班级名称，例如：A1121"
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
