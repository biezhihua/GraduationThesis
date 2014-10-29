<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>模板添加</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dropzone.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery-ui-1.10.3.full.min.css"/>

    <!-- basic styles -->
    <%@include file="/WEB-INF/jsp/public/commons-styles.jspf" %>
</head>

<body>

<div class="main-container" id="main-container">
    <div class="page-content">
        <div class="page-header">
            <h1 style="display:inline;">模板添加</h1>

            <div style="display:inline;" class="pull-right">
                <a href="javascript:history.go(-1)" class="btn btn-xs btn-info "> <i class="icon-reply icon-only"></i>返回上一级
                </a>
            </div>
        </div>
        <!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div id="dropzone">
                    <form action="" class="dropzone">
                        <div class="fallback">
                            <input name="file" type="file" multiple=""/>
                        </div>
                    </form>
                </div>
                <s:form action="template_add" cssClass="form-horizontal">
                    <div class="pull-left">
                        <button class="btn btn-info" type="submit">
                            <i class="icon-ok bigger-110"></i> 完成上传
                        </button>
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
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/assets/js/dropzone.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    jQuery(function ($) {

        // 拖拽上传
        try {
            $(".dropzone").dropzone({
                url: "template_upload.action",
                paramName: "file", // The name that will be used to transfer the file
                maxFilesize: 30, // MB
                addRemoveLinks: true,
                acceptedFiles: ".xls,.xlsx",
                dictDefaultMessage: '<span class="bigger-150 bolder"><i class="icon-caret-right red"></i> 拖拽文件</span>上传 \
                        <span class="smaller-80 grey">(或者 点击选择文件上传)</span> <br /> \
                        <i class="upload-icon icon-cloud-upload blue icon-3x"></i>',
                dictResponseError: '上传文件失败!',
                previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
                init: function () {
                    this.on("complete", function (info) {
                        if (info.status === "success") {
                            console.log("上传成功!");
                        }
                        if (info.status === "error") {
                            console.log("上传失败!");
                        }
                    });
                }

            });
        } catch (e) {
            alert('Dropzone.js 不支持旧的浏览器!');
        }
    });
</script>
</body>
</html>
