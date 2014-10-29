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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jquery.treeview.css"/>
</head>

<body>

<div class="main-container" id="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1 style="display:inline;">正在为[${name}]设置权限</h1>

            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <s:form action="role_setPrivilege" cssClass="form-horizontal">
                    <s:hidden name="id"/>
                    <div class="widget-box">
                        <div class="widget-header header-color-blue2">
                            <div class="checkbox" style="cursor: pointer;">
                                <label>
                                    <input class="ace" type="checkbox" onclick="selectAll(this)">
                                    <label class="lbl"> 全选 </label>
                                </label>
                            </div>
                            <button class="btn btn-info pull-right" type="submit">
                                <i class="icon-ok bigger-110"></i> 保存
                            </button>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main padding-8">
                                <ul id="tree">
                                    <s:iterator value="#application.topPrivileges">
                                        <li style="">
                                            <input class="ace" type="checkbox" name="privilegeIds" value="${id}"
                                                   id="cb_${id}"
                                                    <s:property value="%{id in privilegeIds ? 'checked': ''}"/>
                                                    />
                                            <label class="lbl" for="cb_${id}" style="cursor:pointer;"> ${name} </label>

                                            <ul>
                                                <s:iterator value="childrens">
                                                    <li>
                                                        <input class="ace" type="checkbox" name="privilegeIds"
                                                               value="${id}" id="cb_${id}"
                                                                <s:property
                                                                        value="%{id in privilegeIds ? 'checked': ''}"/>
                                                                />
                                                        <label class="lbl" for="cb_${id}"
                                                               style="cursor:pointer;"> ${name} </label>
                                                        <ul>
                                                            <s:iterator value="childrens">
                                                                <li class="closed">
                                                                    <input class="ace" type="checkbox"
                                                                           name="privilegeIds" value="${id}"
                                                                           id="cb_${id}"
                                                                            <s:property
                                                                                    value="%{id in privilegeIds ? 'checked': ''}"/>
                                                                            />
                                                                    <label class="lbl" for="cb_${id}"
                                                                           style="cursor:pointer;"> ${name} </label>
                                                                </li>
                                                            </s:iterator>
                                                        </ul>
                                                    </li>
                                                </s:iterator>
                                            </ul>
                                        </li>
                                    </s:iterator>
                                </ul>

                            </div>
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

<script src="${pageContext.request.contextPath }/assets/js/jquery.treeview.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">

    function selectAll(cb) {
        $('[name=privilegeIds]').each(function () {
            this.checked = cb.checked;
        });
    }

    jQuery(function ($) {
        $("[name=privilegeIds]").click(function () {
            var me = this;
            // 当选中或者取消一个权限时，也同时选中或者取消所有的下级权限
            $(this).siblings("ul").find("input").each(function () {
                this.checked = me.checked;
            });

            // 当选中一个权限时，也要选中所有的上级权限
            if (me.checked == true) {
                $(this).parents("li").children("input").each(function () {
                    this.checked = me.checked;
                });
            }
        });


        $("#tree").treeview({
            collapsed: true
        });

    });
</script>
</body>
</html>
