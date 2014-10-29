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
</head>

<body>
<div class="main-container">
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="tabbable">
                    <ul class="nav nav-tabs" id="myTab">
                        <s:iterator value="#gradeClaszInfoVOs">
                            <li class="dropdown active">
                                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                        ${grade} &nbsp;
                                    <i class="icon-caret-down bigger-110 width-auto"></i>
                                </a>

                                <ul class="dropdown-menu dropdown-info">
                                    <s:iterator value="claszs">
                                        <li>
                                            <s:a action="info_list?claszId=%{id}">${name}</s:a>
                                        </li>
                                    </s:iterator>
                                </ul>
                            </li>
                        </s:iterator>
                        <li class="pull-right" style="padding-right: 25px;">
                            <s:a id="add_student" cssClass="btn btn-sm btn-primary "
                                 action="info_addUI?claszId=%{claszId}">
                                <i class="fa fa-plus-circle"></i>
                                新增学生
                            </s:a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="dropdown" class="tab-pane in active">
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
                                                    <s:a action="info_editUI?id=%{id}&claszId=%{claszId}&pageNum=%{pageNum}"
                                                         cssClass="btn btn-xs btn-info">
                                                        <i class="icon-edit bigger-120"></i>
                                                    </s:a>

                                                    <s:a action="info_delete?id=%{id}&claszId=%{claszId}&pageNum=%{pageNum}"
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
                            <!-- 分页 -->
                            <s:form id="page" action="info_list">
                                <s:hidden name="claszId" value="%{claszId}"/>
                            </s:form>
                            <s:if test="records != null">
                                <%@include file="/WEB-INF/jsp/public/commons-pageView.jspf" %>
                            </s:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/assets/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<script type="text/javascript">
    jQuery(function ($) {
        $("#add_student").on("click", function (e) {
            console.log(this.href);
            var args = urlArgs(this.href);
            if (args.claszId == "") {
                e.preventDefault();
                bootbox.dialog({
                    message: "添加学生之前，请确认你是否已经选择了某个班级！",
                    buttons: {
                        "success": {
                            "label": "确认",
                            "className": "btn-sm btn-primary"
                        }
                    }
                });
            }
            // 继续事件传播
        });
    });

</script>
</body>
</html>
