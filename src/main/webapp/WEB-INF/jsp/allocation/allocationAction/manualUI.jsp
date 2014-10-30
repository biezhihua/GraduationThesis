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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery-ui-1.10.3.full.min.css"/>
</head>
<body>

<div class="main-container" id="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1 style="display:inline;">新生入住手动分配</h1>

            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <s:form id="form" action="" cssClass="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for> 选择的公寓 </label>

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
                        <label class="col-sm-3 control-label no-padding-right"> 班级 </label>

                        <div class="col-sm-9">
                            <s:select list="#claszList" name="claszId" cssClass="col-xs-10 col-sm-5" listKey="id"
                                      listValue="name" headerKey="" headerValue="===请选择班级==="></s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="stuSno"> 学号 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入学号" name="stuSno" cssClass="col-xs-10 col-sm-5"
                                         id="stuSno"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="stuName"> 姓名 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入姓名" name="stuName" cssClass="col-xs-10 col-sm-5"
                                         id="stuName"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="button" id="allocation">
                                <i class="icon-ok bigger-110"></i> 分配
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
        <div id="dialog-message" class="modal hide">
            <div class="center">
                <h3 id="progress-label" class="blue lighter">准备中...</h3>
            </div>
        </div>
        <!-- #dialog-message -->
    </div>
    <!-- /.page-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>

<!-- inline scripts related to this page -->
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootbox.min.js"></script>

<script type="text/javascript">
    jQuery(function ($) {
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));

        $("#allocation").click(function () {
            if (!$("#form").valid()) {
                return false;
            }
            $.ajax({
                type: "POST",
                url: "allocation_manual.action",
                data: $("#form").serialize(),
                success: function (result) {
                    if (result.status === "success") {
                        bootbox.dialog({
                            message: "<div class='center'>" + result.result + "!</div>",
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

        $("#apartment").change(function () {
            var apartmentId = $(this).val();
            $.ajax({
                url:"allocation_getReservoirDormitory.action?apartmentId="+apartmentId,
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
                url:"allocation_getEmptyBed.action?dormitoryId="+dormitoryId,
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
                bedId: {
                    required: true
                },
                claszId: {
                    required: true
                },
                stuSno: {
                    required: true,
                    number: true,
                    minlength: 11,
                    maxlength: 11
                },
                stuName :{
                    required: tru
                }
            },
            messages: {
                stuSno: {
                    number: "必须为数字",
                    minlength: "学号只能为11位",
                    maxlength: "学号只能为11位"
                }
            },
            submitHandler: function (form) {
                console.log("submitted");
                form.submit();
            }
        });
    })
</script>
</body>
</html>
