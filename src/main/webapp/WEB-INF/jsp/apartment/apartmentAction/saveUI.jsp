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

    <!-- page specific plugin styles -->
    <style type="text/css">
        .radio {
            display: inline;
        }

    </style>
</head>

<body>

<div class="main-container" id="main-container">

    <div class="page-content">

        <div class="page-header">
            <h1 style="display:inline;">公寓信息${id == null ? '添加' : '编辑'}</h1>

            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"> </i>返回上一级
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <s:form id="form" action="apartment_%{id == null ? 'add' : 'edit'}" cssClass="form-horizontal">
                    <s:hidden name="id"/>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="apartment"> 公寓名称 </label>

                        <div class="col-sm-9">
                            <s:textfield placeholder="请输入公寓名称，此项必填" name="name" cssClass="col-xs-10 col-sm-5"
                                         id="apartment"/>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"> 公寓等级 </label>

                        <div class="col-sm-9">
                            <div class="control-group">
                                <div class="radio">
                                    <label>
                                        <input name="rank" type="radio" class="ace" value="普通公寓" checked>
                                        <span class="lbl"> 普通公寓 </span>
                                    </label>
                                </div>

                                <div class="radio">
                                    <label>
                                        <input name="rank" type="radio" value="高等公寓" class="ace">
                                        <span class="lbl"> 高等公寓 </span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"> 公寓类型 </label>

                        <div class="col-sm-9">
                            <div class="control-group">
                                <div class="radio">
                                    <label>
                                        <input name="sex" type="radio" class="ace" value="男生公寓" checked>
                                        <span class="lbl"> 男生公寓 </span>
                                    </label>
                                </div>

                                <div class="radio">
                                    <label>
                                        <input name="sex" type="radio" value="女生公寓" class="ace">
                                        <span class="lbl"> 女生公寓 </span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="topFloor"> 最高楼层 </label>

                        <div class="col-sm-9">
                            <s:select id="topFloor" list="#{6:'六层',7:'七层',8:'八层',9:'九层',10:'十层'}" name="topFloor"
                                      value="%{7}" cssClass="col-xs-10 col-sm-5"
                                      headerValue="===请选择楼层==="> </s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="roomNumber"> 层房间数 </label>

                        <div class="col-sm-9">
                            <s:select id="roomNumber" list="#{22:'22间',26:'26间',28:'28间',30:'30间'}" name="roomNumber"
                                      value="%{28}" cssClass="col-xs-10 col-sm-5"
                                      headerValue="===请选择房间数==="> </s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="bedNumber"> 床铺数 </label>

                        <div class="col-sm-9">
                            <s:select id="bedNumber" list="#{4:'4',6:'6',8:'8'}" name="bedNumber"
                                      value="%{6}" cssClass="col-xs-10 col-sm-5"
                                      headerValue="===请选择床铺数==="> </s:select>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="roomNumber"> </label>

                        <div class="col-sm-9">
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                                <strong>
                                    请注意！
                                </strong>
                                <br/>公寓添加后不允许修改。
                                <br/>10栋 共7层 每层30个房间
                                <br/>13栋 共7层 每层26个房间
                                <br/>11栋 共7层 每层30个房间
                                <br/>31栋 共7层 每层28个房间
                                <br/>29栋 共7层 每层28个房间
                            </div>
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
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    $().ready(function () {
        $("#form").validate({
            rules: {
                name: {
                    required:true,
                    jjuApartmentName:true
                }
            },
            messages: {
                name: {
                    required:"请输入公寓名称",
                    jjuApartmentName:"请填写正确楼栋名称，例如:31栋"
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
