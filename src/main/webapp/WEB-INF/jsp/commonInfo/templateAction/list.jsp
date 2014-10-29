<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>欢迎使用宿舍管理系统</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jquery-ui-1.10.3.full.min.css"/>


</head>

<body>
<div class="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1>模板列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i> <s:a action="template_addUI"
                                                                                 role="button" cssClass="green">
            新增模板 </s:a>
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
                                    <th>文件名称</th>
                                    <th width="8%">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <s:iterator value="records" status="dis">
                                    <tr>
                                        <td>${fileName }</td>
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <s:a action="template_download?id=%{id}"
                                                     cssClass="btn btn-xs btn-info">
                                                    <i class="icon-download bigger-120"></i>
                                                </s:a>
                                                <s:a action="template_delete?id=%{id}&pageNum=%{currentPage}"
                                                     cssClass="btn btn-xs btn-danger">
                                                    <i class="icon-trash bigger-120"></i>
                                                </s:a>
                                            </div>
                                        </td>
                                    </tr>
                                </s:iterator>
                                <!-- #dialog-confirm -->
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /span -->
                </div>
                <!-- 分页 -->
                <s:form id="page" action="template_list"></s:form>
                <%@include file="/WEB-INF/jsp/public/commons-pageView.jspf" %>
                <!-- /row -->
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
        $("#open-event").tooltip({
            show: null,
            position: {
                my: "left top",
                at: "left bottom"
            },
            open: function (event, ui) {
                ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast");
            }
        });

        //override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));

        $(".btn-danger").on('click', function (e) {
            var url = this.name;
            // 阻止事件
            e.preventDefault();
            $("#dialog-confirm").removeClass('hide').dialog({
                resizable: false,
                modal: true,
                title: "<div class='widget-header'><h4 class='smaller'><i class='icon-warning-sign red'></i> 您将删除该班级！</h4></div>",
                title_html: true,
                buttons: [
                    {
                        html: "<i class='icon-trash bigger-110'></i>&nbsp; 删除此班级",
                        "class": "btn btn-danger btn-xs",
                        click: function () {
                            $.ajax({url: url, async: true, success: function () {
                                window.location = "clasz_list.action?" + url.substring(url.lastIndexOf("&"));
                            }});
                            $(this).dialog("close");
                        }
                    }
                    ,
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

        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find(
                    'tr > td:first-child input:checkbox').each(
                    function () {
                        this.checked = that.checked;
                        $(this).closest('tr').toggleClass(
                                'selected');
                    });

        });

        //tooltips
        $(".grey").tooltip({
            show: {
                effect: "slideDown",
                delay: 250
            }
        });
    })
</script>
</body>
</html>
