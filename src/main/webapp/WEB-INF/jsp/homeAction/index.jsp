<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>控制台</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- commons styles -->
    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>

    <!-- page specific plugin styles -->

    <!-- inline styles related to this page -->
</head>

<body>

<div id="top">

</div>
<%--<div class="navbar navbar-default" id="navbar">--%>
<%--<div class="navbar-container" id="navbar-container">--%>
<%--<div class="navbar-header pull-left">--%>
<%--<a href="#" class="navbar-brand">--%>
<%--<small>--%>
<%--<i class="icon-leaf"></i>--%>
<%--信息科学与技术学院宿舍管理系统--%>
<%--</small>--%>
<%--</a><!-- /.brand -->--%>
<%--</div>--%>
<%--<!-- /.navbar-header -->--%>
<%--<div class="navbar-header pull-right" role="navigation">--%>
<%--<ul class="nav ace-nav">--%>
<%--<li class="light-blue">--%>
<%--<a data-toggle="dropdown" href="#" class="dropdown-toggle">--%>

<%--<s:if test='#session.user.gender=="女"'>--%>
<%--<img class="nav-user-photo"--%>
<%--src="${pageContext.request.contextPath}/assets/avatars/user_women.jpg"/>--%>
<%--</s:if>--%>
<%--<s:else>--%>
<%--<img class="nav-user-photo"--%>
<%--src="${pageContext.request.contextPath}/assets/avatars/user_men.jpg"/>--%>
<%--</s:else>--%>

<%--<span class="user-info">--%>
<%--<small>欢迎光临,</small>--%>
<%--${user.name}--%>
<%--</span>--%>

<%--<i class="icon-caret-down"></i>--%>
<%--</a>--%>

<%--<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">--%>
<%--<li>--%>
<%--<a target="rightMain"--%>
<%--href="${pageContext.request.contextPath}/system/user_setProfileUI.action?id=${user.id}">--%>
<%--<i class="icon-user"></i>--%>
<%--个人资料--%>
<%--</a>--%>
<%--</li>--%>

<%--<li class="divider"></li>--%>

<%--<li>--%>
<%--<a href="system/user_logout.action ">--%>
<%--<i class="icon-off"></i>--%>
<%--退出--%>
<%--</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<!-- /.ace-nav -->--%>
<%--</div>--%>
<%--<!-- /.navbar-header -->--%>
<%--</div>--%>
<%--<!-- /.container -->--%>
<%--</div>--%>


<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                    <button class="btn btn-success">
                        <i class="icon-signal"></i>
                    </button>

                    <button class="btn btn-info">
                        <i class="icon-pencil"></i>
                    </button>

                    <button class="btn btn-warning">
                        <i class="icon-group"></i>
                    </button>

                    <button class="btn btn-danger">
                        <i class="icon-cogs"></i>
                    </button>
                </div>

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>

                    <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span>

                    <span class="btn btn-danger"></span>
                </div>
            </div>
            <!-- #sidebar-shortcuts -->

            <ul class="nav nav-list">
                <s:iterator value="#application.topPrivileges">
                    <s:if test="#session.user.hasPrivilegeByName(name)">
                        <li>
                            <a href="#" class="dropdown-toggle">
                                <i class="icon-list"></i>
                                <span class="menu-text"> ${name } </span>

                                <b class="arrow icon-angle-down"></b>
                            </a>

                            <ul class="submenu">
                                <s:iterator value="childrens">
                                    <s:if test="#session.user.hasPrivilegeByName(name)">
                                        <li>
                                            <a target="rightMain" 
                                               href="${pageContext.request.contextPath }${url}.action">
                                                <i class="icon-double-angle-right"></i>
                                                    ${name }
                                            </a>
                                        </li>
                                    </s:if>
                                </s:iterator>
                            </ul>
                        </li>
                    </s:if>
                </s:iterator>
            </ul>
            <!-- /.nav-list -->

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">

                </ul>
                <!-- .breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <a href="javascript: window.parent.rightMain.location.reload(true);"><span
                            class="fa fa-refresh fa-lg"></span></a>
                </div>
                <!-- #nav-search -->
            </div>
            <!-- PAGE CONTENT BEGINS -->
            <iframe name="rightMain" id="rightMain" frameborder="false" src="" scrolling="auto" style="border: none;"
                    width="100%" height="100%" allowtransparency="true"></iframe>
            <!-- PAGE CONTENT ENDS -->
        </div>
        <!-- /.main-content -->

        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>

            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp; 选择皮肤</span>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                    <label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                    <label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                    <label class="lbl" for="ace-settings-breadcrumbs"> 固定面包屑</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                    <label class="lbl" for="ace-settings-rtl"> 切换到左边</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                    <label class="lbl" for="ace-settings-add-container">
                        切换窄屏
                        <b></b>
                    </label>
                </div>
            </div>
        </div>
        <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<!-- commons scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>

<!-- page specific plugin scripts -->

<!-- inline scripts related to this page -->

<script type="text/javascript">
    $(function () {
        // 载入顶部栏
        $("#top").load("home_top.action");

        $(".submenu  a").click(function () {
            var level_one = $(this).parents("li").find("a span[class=menu-text]").text();
            var level_two = $(this).text();
            $(".breadcrumb").empty();
            $(".breadcrumb").append("<i class='icon-home home-icon'></i>");
            $(".breadcrumb").append(" <li>"+level_one+"</li>");
            $(".breadcrumb").append(" <li>"+level_two+"</li>");

        });

    });
    // rightMain高度自适应
    if (window.parent != window) {
        window.parent.location.reload(true);

    }
    var getWindowSize = function () {
        return [ "Height", "Width" ].map(function (name) {
            return window["inner" + name] || document.compatMode === "CSS1Compat" && document.documentElement["client" + name] || document.body["client" + name]
        });
    }
    window.onload = function () {
        if (!+"\v1" && !document.querySelector) { // for IE6 IE7
            document.body.onresize = resize;
        } else {
            window.onresize = resize;
        }
        function resize() {
            wSize();
            return false;
        }
    }
    function wSize() {
        //这是一字符串
        var str = getWindowSize();
        var strs = new Array(); //定义一数组
        strs = str.toString().split(","); //字符分割
        var heights = strs[0] - 120, Body = $('body');
        $('#rightMain').height(heights);
        windowW();
    }
    wSize();
    function windowW() {
        if ($('#Scroll').height() < $("#leftMain").height()) {
            $(".scroll").show();
        } else {
            $(".scroll").hide();
        }
    }
    windowW();
</script>

</body>
</html>

