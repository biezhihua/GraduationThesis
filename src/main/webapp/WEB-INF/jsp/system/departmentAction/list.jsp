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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jquery-ui-1.10.3.full.min.css"/>
</head>

<body>
<div class="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1>部门列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i> <s:a action="department_addUI"
                                                                                 role="button" cssClass="green">
            新增部门 </s:a>
        </h4>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th width="45%">部门名称</th>
                                    <th>描述</th>
                                    <th width="15%">上级部门</th>
                                    <th width="7%">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <s:iterator value="#departmentListTree" status="dis" var="department">
                                    <tr>
                                        <td id="name">${name }</td>
                                        <td>${description }</td>
                                        <td id="parent">${parent.name }</td>
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <s:a action="department_editUI?id=%{id}"
                                                     cssClass="btn btn-xs btn-info">
                                                    <i class="icon-edit bigger-120"></i>
                                                </s:a>
                                                <s:a action="department_delete?id=%{id}"
                                                     cssClass="btn btn-xs btn-danger">
                                                    <i class="icon-trash bigger-120"></i>
                                                </s:a>
                                            </div>
                                        </td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /span -->
                </div>
                <!-- /row -->
                <div id="dialog-confirm" class="hide">
                    <div class="alert alert-info bigger-110">
                        这个操作将会删除此部门下的所有下级部门，请慎重！
                    </div>
                    <div class="space-6"></div>
                    <p class="bigger-110 bolder center grey">
                        <i class="icon-hand-right blue bigger-120"></i>
                        您确认吗?
                    </p>
                </div>
                <!-- PAGE CONTENT ENDS -->
            </div>
        </div>
    </div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {
//override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));

        // 删除确认框
        $(".btn-danger").on('click', function (e) {
            var url = this.href;
            // 阻止事件
            e.preventDefault();
            $("#dialog-confirm").removeClass('hide').dialog({
                resizable: false,
                modal: true,
                title: "<div class='widget-header'><h4 class='smaller'><i class='icon-warning-sign red'></i> 您将删除该部门！</h4></div>",
                title_html: true,
                buttons: [
                    {
                        html: "<i class='icon-trash bigger-110'></i>&nbsp; 删除此部门",
                        "class": "btn btn-danger btn-xs",
                        click: function () {
                            $.ajax({url: url, async: false, success: function () {
                                window.location = "department_list.action";
                            }});
                            $(this).dialog("close");
                        }
                    },
                    {
                        html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
                        "class": "btn btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]
            });
        });

        // 消除上级部门的空格
        $("tbody tr").each(function () {
            var parent = $(this).find("#parent");
            parent.text(parent.text().trim());
        });
    });
</script>
</body>
</html>
