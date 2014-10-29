<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="navbar navbar-default" id="navbar">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    信息科学与技术学院宿舍管理系统
                </small>
            </a><!-- /.brand -->
        </div>
        <!-- /.navbar-header -->
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">

                        <s:if test='#session.user.gender=="女"'>
                            <img class="nav-user-photo"
                                 src="${pageContext.request.contextPath}/assets/avatars/user_women.jpg"/>
                        </s:if>
                        <s:else>
                            <img class="nav-user-photo"
                                 src="${pageContext.request.contextPath}/assets/avatars/user_men.jpg"/>
                        </s:else>

								<span class="user-info">
									<small>欢迎光临,</small>
									${user.name}
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a target="rightMain"
                               href="${pageContext.request.contextPath}/system/user_setProfileUI.action?id=${user.id}">
                                <i class="icon-user"></i>
                                个人资料
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="system/user_logout.action ">
                                <i class="icon-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- /.ace-nav -->
        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>


