<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>部门管理</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->
    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>

</head>

<body>
<div class="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1>岗位列表</h1>
        </div>
        <h4 class="pink">
            <i class="icon-hand-right icon-animated-hand-pointer blue"></i> <s:a action="role_addUI" role="button"
                                                                                 cssClass="green"> 新增岗位 </s:a>
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
                                    <th>岗位名称</th>
                                    <th>描述</th>
                                    <th>所属部门</th>
                                    <th width="10%"></th>
                                    <th width="8%">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <s:iterator value="#roles">
                                    <tr>
                                        <td>${name }</td>
                                        <td>${description }</td>
                                        <td>${department.name }</td>
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <s:a cssClass="btn btn-xs btn-light"
                                                     action="role_setPrivilegeUI?id=%{id}">
                                                    <i class=" icon-cog  bigger-110"></i>
                                                    设置权限
                                                </s:a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <s:a action="role_editUI?id=%{id}"
                                                     cssClass="btn btn-xs btn-info">
                                                    <i class="icon-edit bigger-120"></i>
                                                </s:a>

                                                <s:a action="role_delete?id=%{id}"
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
                    <!-- /span -->
                </div>
                <!-- /row -->

                <!-- PAGE CONTENT ENDS -->
            </div>
        </div>
    </div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- page specific plugin scripts -->
<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {

        $('table th input:checkbox').on(
                'click',
                function () {
                    var that = this;
                    $(this).closest('table').find(
                            'tr > td:first-child input:checkbox').each(
                            function () {
                                this.checked = that.checked;
                                $(this).closest('tr').toggleClass(
                                        'selected');
                            });

                });
    })
</script>
</body>
</html>
