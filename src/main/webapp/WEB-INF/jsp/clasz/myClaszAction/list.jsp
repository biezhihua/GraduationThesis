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
    <!-- inline styles related to this page -->

</head>

<body>
<div class="main-container">
    <input id="claszId" value="${claszId}" type="hidden"/>

    <div class="page-content">
        <div class="page-header">
            <h1>学生列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
            <s:a action="myClasz_addUI?claszId=%{claszId}" role="button" cssClass="green"> 新增学生 </s:a>
        </h4>

        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="sample-table-1"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>电话</th>
                            <th>班级</th>
                            <th>公寓</th>
                            <th>寝室</th>
                            <th>床铺</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <s:iterator value="records" status="dis" var="dormitory">
                            <tr>
                                <td>${sno }</td>
                                <td>${name}</td>
                                <td>${sex}</td>
                                <td>${phoneNumber }</td>
                                <td>${clasz.name }</td>
                                <td>${bed.dormitory.apartment.name}</td>
                                <td>${bed.dormitory.name}</td>
                                <td>${bed.bedNO}</td>
                                <td>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <s:a action="myClasz_editUI?id=%{id}&claszId=%{claszId}&pageNum=%{pageNum}"
                                             cssClass="btn btn-xs btn-info">
                                            <i class="icon-edit bigger-120"></i>
                                        </s:a>

                                        <s:a action="myClasz_delete?id=%{id}&claszId=%{claszId}&pageNum=%{pageNum}"
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
                <!-- start paging -->
                <!-- 分页 -->
                <s:form id="page" action="myClasz_list">
                    <s:hidden name="claszId" value="%{claszId}"/>
                </s:form>
                <s:if test="records != null">
                    <%@include file="/WEB-INF/jsp/public/commons-pageView.jspf" %>
                </s:if>
                <!-- PAGE CONTENT ENDS -->
                <!-- end paging -->
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /.page-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/assets/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {

        // 无负责班级提示
        console.log($("#claszId").val());
        if ("" === $("#claszId").val()) {
            bootbox.dialog({
                message: "您没有负责的班级，无法使用此项功能！",
                buttons: {
                    "success": {
                        "label": "确认",
                        "className": "btn-sm btn-primary"
                    }
                }
            });
        }
    });
</script>
</body>
</html>
