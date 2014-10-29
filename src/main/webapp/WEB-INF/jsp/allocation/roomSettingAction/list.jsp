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
            <h1>待分配房间设置</h1>
        </div>
        <h4 class="pink">
            <s:a id="setDormitorys" cssClass="btn btn-sm btn-primary pull-right" style="margin-right: 12px;"
                 action="roomSetting_setDormitorys?apartmentId=%{apartmentId}&dormitoryLevel=%{dormitoryLevel}">
                <i class=" icon-cogs "></i>
                提交
            </s:a>
        </h4>


        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <s:form action="roomSetting_list" id="filter" name="filter">
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
                                <th width="16%" class="center" colspan="6">
                                    <input type="checkbox" class="ace"/>
                                    <span class="lbl"></span>全选
                                    宿舍
                                </th>

                            </tr>
                            </thead>

                            <tbody>
                            <s:iterator value="#records" var="dormitories">
                                <tr>
                                    <s:iterator value="%{dormitories}" var="dormitory">
                                        <td class="center">
                                            <input type="checkbox" name="dormitoryIds" value="${dormitory.id}"
                                                   class="ace" <s:property
                                                    value="%{#dormitory.id in dormitoryIds ? 'checked': ''}"/>
                                                    />
                                            <span class="lbl"></span>　
                                                ${dormitory.name}
                                        </td>
                                    </s:iterator>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <div class="alert alert-block alert-success">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="icon-remove"></i>
                            </button>

                            <p>
                                <strong>
                                    <i class="icon-ok"></i>
                                    请注意!
                                </strong>
                                勾选寝室并提交，会将寝室更改为待分配房间!
                            </p>
                        </div>
                    </div>
                    <!-- /.table-responsive -->
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
    // 提交表单
    function submit(a) {
        var form = document.forms[0];
        var i = a.text.lastIndexOf("-");
        form.action = "roomSetting_list.action?apartmentId=" + a.name + "&dormitoryLevel=" + a.text.substring(i + 1, a.text.length - 1);
        form.submit();
    }

    jQuery(function ($) {
        //Menu
        $("#menu").menu();


        // checkbox全选功能
        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find('tr > td input:checkbox').each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });
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


        // 给提交按钮增加点击事件
        $("#setDormitorys").on("click", function (e) {
            var me = this;
            var $me = $(me);
            var checkeds = $("[name=dormitoryIds]:checkbox:checked");
            // 阻止a事件
            e.preventDefault();
            var args = urlArgs(this.href);
            if (args.apartmentId == "" || args.dormitoryId == "") {
                bootbox.dialog({
                    message: "<div class='center'>在提交之前，请确认是否选择了楼层!</div>",
                    buttons: {
                        "success": {
                            "label": "确认",
                            "className": "btn-sm btn-primary"
                        }
                    }
                });
            } else {
                var params = "";
                checkeds.each(function () {
                    params += "&dormitoryIds=" + this.value;
                });
                var href = this.href + params;
                $.ajax({
                    url: href,
                    success: function (data) {
                        if (data.status === "success") {
                            $me.html("<i class='icon-cogs'></i>已提交");
                            $me.removeClass("btn-primary");
                            window.setTimeout(function () {
                                $me.html("<i class='icon-cogs'></i>提交");
                                $me.addClass("btn-primary");
                            }, 2000);
                        }
                    }
                });
            }
        });
    })
</script>
</body>
</html>
