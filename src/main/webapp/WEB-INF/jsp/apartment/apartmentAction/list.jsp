<%@ page language="java" pageEncoding="utf-8" %>
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
            <h1>公寓列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
            <s:a action="apartment_addUI" role="button" cssClass="green">
                新增公寓
            </s:a>

            <s:a id="data_export" cssClass="btn btn-sm btn-primary pull-right" style="padding-right: 25px;"
                 action="apartment_dataExport">
                <i class="fa fa-external-link"></i>
                数据导出
            </s:a>
        </h4>

        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label> <input type="checkbox" class="ace"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>公寓名称</th>
                            <th>公寓等级</th>
                            <th>公寓类型</th>
                            <th>最高层数</th>
                            <th>层房间数</th>
                            <th>床铺数</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <s:iterator value="records" status="dis" var="apartment">
                            <tr>
                                <td class="center">
                                    <label>
                                        <input name="apartmentIds" type="checkbox" class="ace"
                                               value="${id}"/>
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${name }</td>
                                <td>${rank }</td>
                                <td>${sex }</td>
                                <td>${topFloor}</td>
                                <td>${roomNumber}</td>
                                <td>${bedNumber}</td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <s:a action="apartment_delete.action?id=%{id}&pageNum=%{currentPage}"
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
            </div>
        </div>
        <!-- 分页 -->
        <s:form id="page" action="apartment_list"></s:form>
        <%@include file="/WEB-INF/jsp/public/commons-pageView.jspf" %>

        <!-- #dialog-confirm -->
        <div id="dialog-confirm" class="hide">
            <div class="alert alert-info bigger-110">
                这个操作将会删除此公寓下的所有寝室信息（寝室信息包括床铺信息以及寝室成员信息），请慎重！
            </div>
            <div class="space-6"></div>
            <p class="bigger-110 bolder center grey">
                <i class="icon-hand-right blue bigger-120"></i>
                您确认吗?
            </p>
        </div>

        <!-- #dialog-message -->
        <div id="dialog-message" class="modal hide">
            <div class="center">
                <h3 id="progress-label" class="blue lighter">准备中...</h3>

                <div id="progressbar"
                     class="ui-progressbar ui-widget ui-widget-content ui-corner-all progress progress-striped active"
                     role="progressbar" aria-valuemin="0" aria-valuemax="100"
                     aria-valuenow="37">
                    <div id="progressbar-value"
                         class="ui-progressbar-value ui-widget-header ui-corner-left progress-bar progress-bar-success"></div>
                </div>
            </div>
        </div>
    </div>
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
                title: "<div class='widget-header'><h4 class='smaller'><i class='icon-warning-sign red'></i> 您将删除该公寓！</h4></div>",
                title_html: true,
                buttons: [
                    {
                        html: "<i class='icon-trash bigger-110'></i>&nbsp; 删除此公寓",
                        "class": "btn btn-danger btn-xs",
                        click: function () {
                            $.ajax({url: url, async: false, success: function () {
                                window.location = "apartment_list.action?" + url.substring(url.lastIndexOf("&"));
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

        // checkbox全选功能
        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox').each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });
        });

        // 导出数据按钮
        $("#data_export").on("click", function (e) {
            var checkeds = $("[name=apartmentIds]:checkbox:checked");

            if (checkeds.length == 0) {// 没有选择项，就提示
                e.preventDefault();
                bootbox.dialog({
                    message: "导出数据之前，请确认您是否选择了要被导出的公寓！<br/>这个操作将会把选择公寓中的所有信息导出为一个Excel表格!",
                    buttons: {
                        "success": {
                            "label": "确认",
                            "className": "btn-sm btn-primary"
                        }
                    }
                });
            } else {
                // 有就发出ajax请求，调用导出数据的方法
                e.preventDefault();
                var params = "";
                checkeds.each(function () {
                    params += "apartmentIds=" + this.value + "&";
                });
                params = params.substring(0, params.length - 1);
                var href = this.href + "?" + params;
                // 发出请求，监听进度
                $.ajax({
                    url: href,
                    success: function (data) {
                        var progressbar = $("#progressbar");
                        var progressLabel = $("#progress-label");
                        var progressbarValue = $("#progressbar-value");

                        if (data.status === "success") {
                            // 弹出监督监督的对话框
                            var dialog = $("#dialog-message").removeClass('hide').dialog({
                                title: "<h4><i class='icon-warning-sign red'></i> 准备数据中...</h4>",
                                height: 250,
                                width: 530,
                                modal: true,
                                closeOnEscape: false,
                                open: function (event, ui) {
                                    $(".ui-dialog-titlebar-close").hide();
                                },
                                title_html: true
                            });
                            //
                            progressbar.progressbar({
                                value: 0,
                                change: function () {
                                    progressLabel.text(progressbar.progressbar("value") + "%");
                                    progressbarValue.attr("style", "width:" + progressbar.progressbar("value") + "%");
                                },
                                complete: function () {
                                    progressLabel.text("完成!");

                                    // 延迟关闭对话框
                                    window.setTimeout(function () {
                                        $("#dialog-message").dialog("close");
                                        // 创建一个隐式的a标签，发出普通请求，进行下载
                                        $("body").append("<a id='hidden'></a>");
                                        var a = $("#hidden");
                                        a.addClass("hide");
                                        a.attr("href", "apartment_download.action");
                                        document.getElementById("hidden").click();
                                    }, 1000);


                                }
                            });

                            function progress() {
                                var val = progressbar.progressbar("value") || 0;
                                $.ajax({
                                    url: "apartment_getCurrentProgress.action",
                                    async: false,
                                    success: function (data) {
                                        val = parseInt(data.progress);
                                        progressbar.progressbar("value", val);
                                    }
                                });

                                if (val < 100) {
                                    setTimeout(progress, 80);
                                }
                            }

                            // 调用方法
                            setTimeout(progress, 1000);
                        }
                    }
                });
            }
        });
    })
</script>
</body>
</html>
