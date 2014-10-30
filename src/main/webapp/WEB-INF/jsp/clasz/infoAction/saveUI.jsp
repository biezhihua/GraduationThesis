<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>信息管理</title>
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
            <h1 style="display:inline;">学生信息${id == null ? '添加' : '编辑'}</h1>
            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <s:form id="form" action="info_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
                    <s:hidden name="id"/>
                    <s:hidden name="claszId" value="%{claszId}"/>
                    <s:hidden name="pageNum" value="%{pageNum}"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for> 所在寝室 </label>
                        <div class="col-sm-9">
                            <s:select id="apartment" list="#apartmentList" name="apartmentId" cssClass="col-xs-2" listKey="id"
                                      listValue="name" headerKey="" headerValue="请选择公寓" ></s:select>
                            <select id="dor" name="dormitoryId" class="col-xs-2">
                            </select>
                            <select id="bed" name="bedId" class="col-xs-2">
                            </select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="sno"> 学号 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入学号!" name="sno" cssClass="col-xs-10 col-sm-5"
                                         id="sno"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name"> 姓名 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入姓名!" name="name" cssClass="col-xs-10 col-sm-5"
                                         id="name"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"> 性别 </label>

                        <div class="col-sm-9">
                            <div class="control-group">
                                <div class="radio">
                                    <label>
                                        <input name="sex" type="radio" class="ace" value="男" <s:if test='sex=="男"'>checked="checked"</s:if>>
                                        <span class="lbl"> 男 </span>
                                    </label>
                                </div>

                                <div class="radio">
                                    <label>
                                        <input name="sex" type="radio" value="女" class="ace" <s:if test='sex=="女"'>checked="checked"</s:if>>
                                        <span class="lbl"> 女 </span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name"> 电话 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入电话!" name="phoneNumber" cssClass="col-xs-10 col-sm-5"
                                         id="phoneNumber"></s:textfield>
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
    jQuery(function ($) {
        $("#apartment").change(function () {
            var apartmentId = $(this).val();
            $.ajax({
                url:"info_getReservoirDormitory.action?apartmentId="+apartmentId,
                success: function (result) {
                    console.log(result);
                    $("#dor").empty();
                    $("#dor").append(" <option>请选择寝室</option>");
                    $.each(result, function (index, value) {
                        $("#dor").append("<option value='"+value.id+"'>"+value.name+"</option>");
                    })
                }
            });
        });

        $("#dor").change(function () {
            var dormitoryId = $(this).val();
            $.ajax({
                url:"info_getEmptyBed.action?dormitoryId="+dormitoryId,
                success: function (result) {
                    console.log(result);
                    $("#bed").empty();
                    $("#bed").append(" <option>请选择床铺</option>");
                    $.each(result, function (index, value) {
                        $("#bed").append("<option value='"+value.id+"'>"+value.bedNO+"床</option>");
                    })
                }
            })
        });


        $("#form").validate({
            rules: {
                sno: {
                    number:true,
                    minlength:11,
                    maxlength:11
                }
            },
            messages: {
                sno: {
                    number:"必须为数字",
                    minlength: "学号只能为11位",
                    maxlength: "学号只能为11位"

                }
            },
            debug:true,
            submitHandler:function(form){
                console.log("submitted");
                form.submit();
            }
        });
    });
</script>
</body>
</html>
