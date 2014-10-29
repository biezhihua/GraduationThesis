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
            <h1>待分配班级信息列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i> <s:a action="infoEntering_addUI"
                                                                                 role="button" cssClass="green">
            新增信息 </s:a>
            <s:a id="predistribution" cssClass="btn btn-sm btn-primary pull-right" style="padding-right: 25px;"
                 action="allocation_preDistribution">
                <i class="fa fa-external-link"></i>
                宿舍预分配
            </s:a>
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
                                    <th class="center">
                                        班级
                                    </th>
                                    <th class="center">
                                        男生人数
                                    </th>
                                    <th class="center">
                                        女生人数
                                    </th>
                                    <th class="center">
                                        操作
                                    </th>
                                </tr>
                                </thead>

                                <tbody>

                                <s:iterator value="#claszList">
                                    <tr>
                                        <td>${name}</td>
                                        <td>${manReservoir}</td>
                                        <td>${womanReservoir}</td>
                                        <td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                            <s:a action="infoEntering_editUI?id=%{id}"
                                                 cssClass="btn btn-xs btn-info">
                                                <i class="icon-edit bigger-120"></i>
                                            </s:a>
                                            <s:a action="infoEntering_delete?id=%{id}"
                                                 cssClass="btn btn-xs btn-danger">
                                                <i class="icon-trash bigger-120"></i>
                                            </s:a>
                                        </div></td>
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
//override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));

        $("#predistribution").click(function (e) {
            var me = this;
            e.preventDefault();
            bootbox.dialog({
                message: "<div class='center'>该操作会对待分配班级进行普通公寓的宿舍预分配，请确认！<br/>预分配后可以在待分配房间概览中查看</div>",
                buttons: {
                    "confirm": {
                        "label": "确认",
                        "className": "btn-sm btn-primary",
                        "callback": function() {
                            console.log("确认");
                            $.ajax({
                                url:me.href
                            });
                        }
                    },
                    "cancel": {
                        "label": "取消",
                        "className": "btn-sm",
                        "callback": function() {
                            console.log("取消");
                        }
                    }
                }
            });

        });

    });
</script>
</body>
</html>
