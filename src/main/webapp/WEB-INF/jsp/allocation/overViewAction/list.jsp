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
            <h1>待分配房间概览</h1>
        </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width="16%" class="center">宿舍(1)</th>
                                <th width="16%" class="center">宿舍(2)</th>
                                <th width="16%" class="center">宿舍(3)</th>
                                <th width="16%" class="center">宿舍(4)</th>
                                <th width="16%" class="center">宿舍(5)</th>
                                <th width="16%" class="center">宿舍(6)</th>
                            </tr>
                            </thead>

                            <tbody>
                            <s:iterator value="#records" var="dormitories">
                                <tr>
                                    <s:iterator value="%{dormitories}" var="dormitory">
                                        <td class="center">
                                            ${dormitory.apartment.name}-${dormitory.name}　预分配在${dormitory.clasz.name}班
                                            <br/>
                                            <s:iterator value="#dormitory.beds" var="bed">
                                               床铺${bed.bedNO}:${bed.student.name}<br/>
                                            </s:iterator>
                                        </td>
                                    </s:iterator>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
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

    })
</script>
</body>
</html>
