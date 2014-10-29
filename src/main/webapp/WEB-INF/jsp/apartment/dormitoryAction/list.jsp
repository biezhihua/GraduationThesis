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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery-ui-1.10.3.full.min.css"/>
</head>

<body>
<div class="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1>宿舍列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
            <s:a action="dormitory_addUI" role="button" cssClass="green">
                新增宿舍
            </s:a>
        </h4>
    </div>
    <!-- end page-content -->

    <div class="col-xs-12">
        <div class="row">
            <s:form action="dormitory_list" id="filter" name="filter">
                <div class="col-xs-2">
                    <ul id="menu" style="z-index: 99;" class="ui-menu ui-widget ui-widget-content ui-corner-all"
                        role="menu"
                        tabindex="0" aria-activedescendant="ui-id-9">
                        <s:iterator value="#apartments">
                            <li class="ui-menu-item" role="presentation">
                                <a href="#" aria-haspopup="true" id="" class="ui-corner-all" tabindex="-1"
                                   role="menuitem">
                                    <span class="ui-menu-icon ui-icon ui-icon-carat-1-e"></span><s:if
                                        test="name != null">${name}</s:if><s:else>无</s:else>
                                </a>

                                <ul class="ui-menu ui-widget ui-widget-content ui-corner-all" role="menu"
                                    aria-hidden="true"
                                    aria-expanded="false" style="display: none;">
                                    <s:if test="#topLevel != null">
                                        <s:iterator begin="1" end="#topLevel" var="curr">
                                            <li class="ui-menu-item" role="presentation">
                                                <a href="#" name="${id}" class="ui-corner-all" tabindex="-1"
                                                   onclick="javascript:submit(this);"
                                                   role="menuitem">${name}-${curr}层</a>
                                            </li>
                                        </s:iterator>
                                    </s:if>
                                    <s:else>无</s:else>
                                </ul>
                            </li>
                        </s:iterator>

                    </ul>
                </div>
            </s:form>
            <div class="col-xs-10">
                <div class="table-responsive">
                    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center"><label> <input type="checkbox" class="ace"/> <span
                                    class="lbl"></span>
                            </label></th>
                            <th>宿舍名称</th>
                            <th>宿舍床铺</th>
                            <th>床铺数</th>
                            <th>楼层</th>
                            <th>所属公寓</th>
                            <th>所属班级</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <s:iterator value="records" status="dis" var="dormitory">
                            <tr>
                                <td class="center"><label> <input type="checkbox" class="ace"/> <span
                                        class="lbl"></span>
                                </label></td>
                                <td>${name }</td>
                                <td>
                                    <a href="#" id="id-btn-dialog1" class="btn btn-xs"
                                       name="${id }">
                                        <i class="icon-home bigger-110"></i>
                                        <i class="icon-arrow-right icon-on-right"></i>
                                    </a>
                                </td>
                                <td><s:property value="%{beds.size()}"/></td>
                                <td>${level }</td>
                                <td>${apartment.name }</td>
                                <td><s:if test="clasz == null">无</s:if><s:else>${clasz.name}</s:else></td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <s:a action="dormitory_editUI?id=%{id}"
                                             cssClass="btn btn-xs btn-info">
                                            <i class="icon-edit bigger-120"></i>
                                        </s:a>

                                        <s:a action="dormitory_delete?id=%{id}"
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
        </div>

        <div class="col-md-offset-2 col-xs-10">
            <!-- 分页 -->
            <s:form id="page" action="dormitory_list">
                <s:hidden name="apartmentId" value="%{#session._dor_filter_apartmentId}"/>
                <s:hidden name="dormitoryLevel" value="%{#session._dor_filter_level}"/>
            </s:form>
            <s:if test="records != null">
                <%@include file="/WEB-INF/jsp/public/commons-pageView.jspf" %>
            </s:if>
        </div>
    </div>

    <!-- 对话框 -->
    <div id="dialog-message" class="hide">
        <table class="table table-striped table-bordered table-hover">
            <thead class="thin-border-bottom">
            <tr>
                <th>床铺号</th>
                <th>
                    <i class="icon-user"></i>
                    姓名
                </th>
            </tr>
            </thead>
            <tbody id="dialog-table-tbody">
            </tbody>
        </table>
    </div>
</div>
<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/jquery.ui.touch-punch.min.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    // 提交表单
    function submit(a) {
        var form = document.forms[0];
        var i = a.text.lastIndexOf("-");
        form.action = "dormitory_list.action?apartmentId=" + a.name + "&dormitoryLevel=" + a.text.substring(i + 1, a.text.length - 1);
        form.submit();
    }

    jQuery(function ($) {
        //Menu
        $("#menu").menu();

        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));

        // 对话框
        $("tbody td a[href=#]").on('click', function (e) {
            e.preventDefault();
            var dormitoryId = this.name; // 获取寝室ID
            $.ajax({
                async: false,
                url: "dormitory_getBedsInfo.action?dormitoryId=" + dormitoryId,
                success: function (data) {
                    console.log(data);
                    $("#dialog-table-tbody").children().remove();
                    $.each(data, function (i, d) {
                        $("#dialog-table-tbody").append("<tr><td>" + d.bedNO + "</td><td>" + (d.student == null ? '无' : d.student.name ) + "</td></tr>");
                    });
                }
            });

            var dialog = $("#dialog-message").removeClass('hide').dialog({
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i> 床位</h4></div>",
                title_html: true,
                buttons: [
                    {
                        text: "取消",
                        "class": "btn btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    },
                    {
                        text: "确认",
                        "class": "btn btn-primary btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]
            });
        });

        // 全部选择
        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find(
                    'tr > td:first-child input:checkbox').each(
                    function () {
                        this.checked = that.checked;
                        $(this).closest('tr').toggleClass('selected');
                    });
        });
    })
</script>
</body>
</html>
