<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>宿舍管理</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>
</head>
<body>
<div class="main-container" id="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1 style="display:inline;">宿舍信息${id == null ? '添加' : '编辑'}</h1>
            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <s:form id="form" name="apartment" action="dormitory_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
                    <s:hidden name="id"></s:hidden>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-select-1"> 所属公寓 </label>

                        <div class="col-sm-9">
                            <s:select id="form-field-select-1" list="%{#apartments}" name="apartmentId"
                                      value="%{apartmentId}" cssClass="col-xs-10 col-sm-5" listKey="%{id}"
                                      listValue="%{name}" headerKey="" headerValue="===请选择公寓==="></s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="level"> 楼层 </label>

                        <div class="col-sm-9">
                            <s:select value="%{level}" id="level"
                                      list="#{1:'一层',2:'二层',3:'三层',4:'四层',5:'五层',6:'六层',七:'七层'}" name="level"
                                      cssClass="col-xs-10 col-sm-5" headerKey="" headerValue="===请选择楼层==="></s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="dormitory"> 寝室名称 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入寝室名称，此项必填" name="name" cssClass="col-xs-10 col-sm-5"
                                         id="dormitory"></s:textfield>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="bedNumber"> 床铺数 </label>

                        <div class="col-sm-9">
                            <s:select value="%{beds.size()}" id="bedNumber"
                                      list="#{4:'4床',6:'6床',8:'8床'}" name="bedNumber"
                                      cssClass="col-xs-10 col-sm-5" headerKey="" headerValue="===请选择楼层==="></s:select>
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
<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
        $("#form").validate({
            rules: {
                name: {
                    required:true,
                    jjuDormitoryName:true
                }
            },
            messages: {
                name: {
                    required:"请输入公寓名称"
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
