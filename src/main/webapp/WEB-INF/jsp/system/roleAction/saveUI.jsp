<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>欢迎使用宿舍管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- basic styles -->
<%@include file="/WEB-INF/jsp/public/commons-styles.jspf"%>


<!-- page specific plugin styles -->

</head>

<body>

	<div class="main-container" id="main-container">
		<div class="page-content">
			<div class="page-header">
				<h1 style="display:inline;">岗位信息${id == null ? '添加' : '编辑'}</h1>
				<div style="display:inline;" class="pull-right">
					<a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
					</a>
				</div>
			</div>
			<!-- /.page-header -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<s:form id="form" action="role_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
						<s:hidden name="id"></s:hidden>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-select-1"> 所属部门 </label>
							<div class="col-sm-9">
								<s:select id="form-field-select-1" list="%{#departmentListTree}" name="departmentId" cssClass="col-xs-10 col-sm-5" listKey="%{id}" listValue="%{name}" headerKey="" headerValue="===请选择部门==="></s:select>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="departmentname"> 岗位名称 </label>
							<div class="col-sm-9">
								<s:textfield placeholder="请输入部门名称，此项必填" name="name" cssClass="col-xs-10 col-sm-5" id="departmentname"></s:textfield>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="bootstrap-editor"> 岗位说明 </label>
							<div class="col-sm-9">
								<s:textarea name="description" id="bootstrap-editor" cssClass="col-xs-10 col-sm-5" placeholder="请输入岗位说明" style="height: 50px"></s:textarea>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="icon-undo bigger-110"></i> 重置
								</button>
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
	<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf"%>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
        jQuery(function ($) {
            $("#form").validate({
                rules: {
                    departmentId: {
                        required:true
                    },
                    name:{
                        required:true
                    }
                },
                messages: {
                    departmentId: {
                        required:"请输选择所属部门"
                    },
                    name: {
                        required:"请输入岗位名称"
                    }

                },
                submitHandler:function(form){
                    console.log("submitted");
                    form.submit();
                }
            });
        });
	</script>
</body>
</html>
