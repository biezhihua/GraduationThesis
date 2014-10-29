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
            <h1>用户列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
            <s:a action="user_addUI" role="button" cssClass="green"> 添加新用户 </s:a>
        </h4>

        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>登录名</th>
                            <th>用户名</th>
                            <th>岗位-部门</th>
                            <th width="10%"></th>
                            <th width="8%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#users">
                            <tr>
                                <td>${loginName }</td>
                                <td>${name }</td>
                                <td>
                                    <s:iterator value="roles">
                                        <s:property value="name"/>-<s:property value="department.name"/>　
                                    </s:iterator>
                                </td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <s:a action="user_initPassword?id=%{id}" cssClass="btn btn-xs btn-light">
                                            <i class=" icon-cog  bigger-110"></i>
                                            初始化密码
                                        </s:a>
                                    </div>
                                </td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <s:a action="user_editUI?id=%{id}" cssClass="btn btn-xs btn-info">
                                            <i class="icon-edit bigger-120"></i>
                                        </s:a>

                                        <s:a action="user_delete?id=%{id}" cssClass="btn btn-xs btn-danger">
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
        <div id="dialog-message" class="hide">
            <br>

            <p>
                此操作将会把该用户的密码初始化为：123456
            </p>
        </div>
        <!-- #dialog-message -->
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


        $(".btn-light").on('click', function (e) {
            var a = this;
            var url = this.href;
            e.preventDefault();
            var dialog = $("#dialog-message").removeClass('hide').dialog({
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>初始化密码</h4></div>",
                title_html: true,
                buttons: [
                    {
                        text: "确认",
                        "class": "btn btn-primary btn-xs",
                        click: function () {
                            $.ajax({url: url, async: false, success: function () {
                                //window.location = "department_list.action";
                                a.innerHTML = "已经初始化";
                                $(a).addClass("disabled");
                            }});
                            $(this).dialog("close");
                        }

                    },
                    {
                        text: "取消",
                        "class": "btn btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]
            });

        });
    })
</script>
</body>
</html>
