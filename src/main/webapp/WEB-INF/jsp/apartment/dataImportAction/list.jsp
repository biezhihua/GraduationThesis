<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>欢迎使用宿舍管理系统</title>
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
        <div class="row">
            <div class="col-xs-12">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-blue widget-header-flat">
                                <h4 class="lighter">导入步骤</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main">
                                    <div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
                                        <ul class="wizard-steps">
                                            <li data-target="#step1" class="active">
                                                <span class="step">1</span>
                                                <span class="title">下载模板文件</span>
                                            </li>

                                            <li data-target="#step2">
                                                <span class="step">2</span>
                                                <span class="title">上传文件</span>
                                            </li>

                                            <li data-target="#step3">
                                                <span class="step">3</span>
                                                <span class="title">选择导入的文件</span>
                                            </li>

                                            <li data-target="#step4">
                                                <span class="step">4</span>
                                                <span class="title">进行导入</span>
                                            </li>
                                        </ul>
                                    </div>

                                    <hr/>
                                    <div class="step-content row-fluid position-relative" id="step-container">
                                        <div class="step-pane active" id="step1">
                                            <a href="dataImport_download.action" class="btn btn-app btn-primary">
                                                <i class="icon-cloud-download blank icon-3x"></i>
                                                一览表模板
                                            </a>
                                            <div class="alert alert-info" style="margin-top: 30px">
                                                <button type="button" class="close" data-dismiss="alert">
                                                    <i class="icon-remove"></i>
                                                </button>
                                                <strong>请注意!</strong>
                                                您要导入的文件，必须符合一览表模板的表头,同时，请确认公寓是否已经添加完整，否则可能会造成数据无法导入！
                                                <br>
                                            </div>
                                        </div>
                                        <div class="step-pane" id="step2">
                                            <div id="dropzone">
                                                <form action="" class="dropzone">
                                                    <div class="fallback">
                                                        <input name="file" type="file" multiple=""/>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="step-pane" id="step3">
                                            <table id="sample-table-1"
                                                   class="table table-striped table-bordered table-hover">
                                                <thead>
                                                <tr>
                                                    <th>
                                                    </th>
                                                    <th>文件名</th>
                                                    <th>文件类型</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="step-pane" id="step4">
                                            <div class="center">
                                                <h3 id="progress-label" class="blue lighter">准备中...</h3>

                                                <div id="progressbar"
                                                     class="ui-progressbar ui-widget ui-widget-content ui-corner-all progress progress-striped active"
                                                     role="progressbar" aria-valuemin="0" aria-valuemax="100"
                                                     aria-valuenow="37">
                                                    <div id="progressbar-value"
                                                         class="ui-progressbar-value ui-widget-header ui-corner-left progress-bar progress-bar-success"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row-fluid wizard-actions">
                                        <button class="btn btn-prev">
                                            <i class="icon-arrow-left"></i>
                                            上一步
                                        </button>

                                        <button class="btn btn-success btn-next" data-last="结束 ">
                                            下一步
                                            <i class="icon-arrow-right icon-on-right"></i>
                                        </button>
                                    </div>
                                </div>
                                <!-- /widget-main -->
                            </div>
                            <!-- /widget-body -->
                        </div>
                    </div>
                </div>
                <!-- PAGE CONTENT ENDS -->
            </div>
            <!-- /.col -->
        </div>
    </div>
</div>
<!-- basic scripts -->
<%@include file="/WEB-INF/jsp/public/commons-scripts.jspf" %>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/assets/js/fuelux/fuelux.wizard.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dropzone.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
        // 拖拽上传
        try {
            $(".dropzone").dropzone({
                url: "dataImport_upload.action",
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

        // 步骤
        $('#fuelux-wizard').ace_wizard().on('change', function (e, info) {
            // 第二步
            if (info.direction == "next" && (info.step + 1) == 2) {
                console.log("步骤2");
                return true;
            }
            // 第三步
            if (info.direction == "next" && (info.step + 1) == 3) {
                console.log("步骤3");
                // 获取Excel文件列表
                $("tbody").children().remove();
                $.ajax({
                    url: "dataImport_getExcelFiles.action",
                    success: function (data) {
                        console.log(data);
                        $.each(data, function (index, value) {
                            var $tr = $("tbody").append("<tr id=''></tr>").children("tr[id='']").attr("id", value["id"]);
                            var $label = $tr.append("<td></td>").children("td").last().addClass("center")
                                    .append("<label></label>").children("label").first();
                            $label.append("<input type='checkbox'/>").children("input").first().addClass("ace").attr("name", "excelId").attr("id", value["id"]).attr("value", value["id"])
                            $label.append("<span></span>").children("span").first().addClass("lbl");
                            $tr.append("<td></td>").children("td").last().text(value["fileName"]);
                            $tr.append("<td></td>").children("td").last().text(value["contentType"]);
                            $tr.append("<td></td>").children("td").last()
                                    .append("<div></div>").children("div").addClass("visible-md visible-lg hidden-sm hidden-xs btn-group")
                                    .append("<a></a>").children("a").addClass("btn btn-xs btn-danger").attr("href", "#").attr("id", value["id"]) //.attr("onclick", "delExcelFile(this);") //"dataImport_delete.action?id=" + value["id"]
                                    .append("<i></i>").children("i").addClass("icon-trash bigger-120");
                        });

                        // 为a标签添加点击事件
                        $("tbody tr td a").click(function () {
                            $.ajax({
                                url: "dataImport_delete.action?id=" + this.id,
                                success: function (data) {
                                    console.log(data);
                                    if (data && data.status === "success") {
                                        $("tr[id=" + data.deleteId + "]").remove();
                                    }

                                }
                            })
                        });

                        // 为checkbox增加点击事件
                        // 只允许一行被选中
                        $("tbody input:checkbox").change(function () {
                            if (this.checked == true) {
                                $("tbody input:checkbox:not([id='" + this.id + "'])").attr("checked", false);
                            }
                        });
                    }
                });
                return true;
            }
            // 第四步
            if (info.direction == "next" && (info.step + 1) == 4) {
                console.log("步骤4");
                // 获取选择项
                var checked = $("tbody input:checkbox:checked");
                // 如果没有选择文件,则进行提示
                if (checked.length == 0) {
                    bootbox.dialog({
                        message: "请选择一个文件进行导入!",
                        buttons: {
                            "success": {
                                "label": "确认",
                                "className": "btn-sm btn-primary"
                            }
                        }
                    });
                    return false;
                }
                // 发送请求
                $.ajax({
                    url: "dataImport_loadExcelInfo.action?id=" + checked[0].id,
                    success: function (data) {
                        console.log(data);
                        // 准备进度条
                        var progressbar = $("#progressbar");
                        var progressLabel = $("#progress-label");
                        var progressbarValue = $("#progressbar-value");
                        if (data.status === "success") {
                            progressbar.progressbar({
                                value: 0,
                                change: function () {
                                    progressLabel.text(progressbar.progressbar("value") + "%");
                                    progressbarValue.attr("style", "width:" + progressbar.progressbar("value") + "%");
                                },
                                complete: function () {
                                    progressLabel.text("完成!");
                                }
                            });
                            function progress() {
                                var val = progressbar.progressbar("value") || 0;
                                $.ajax({
                                    url: "dataImport_getCurrentProgress.action",
                                    async: false,
                                    success: function (data) {
                                        val = parseInt(data.progress);
                                        progressbar.progressbar("value", val);
                                    }
                                });
                                if (val <= 100) {
                                    setTimeout(progress, 80);
                                }
                            }
                            // 调用方法
                            setTimeout(progress, 1000);
                        } else {
                            progressLabel.text("导入文件出错，请从新导入文件！");
                            return false;
                        }
                    }
                });
                return true;
            }
        }).on('finished', function (e) {
        }).on('stepclick', function (e) {
            console.log("stepclick");
            return false;
        });

    });

</script>
</body>
</html>
