/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2014-10-25 07:18:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.allocation.allocationAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.*;
import java.util.*;

public final class manualUI_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/jsp/public/commons-scripts.jspf", Long.valueOf(1412952013572L));
    _jspx_dependants.put("/WEB-INF/jsp/public/commons-styles.jspf", Long.valueOf(1412929830053L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fform_0026_005fid_005fcssClass_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fheaderValue_005fheaderKey_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fheaderValue_005fheaderKey_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fform_0026_005fid_005fcssClass_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fheaderValue_005fheaderKey_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fheaderValue_005fheaderKey_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005fform_0026_005fid_005fcssClass_005faction.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"zh-CN\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\"/>\n");
      out.write("    <title>待分配班级信息管理</title>\n");
      out.write("    <meta name=\"keywords\" content=\"\"/>\n");
      out.write("    <meta name=\"description\" content=\"\"/>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n");
      out.write("\n");
      out.write("    <!-- basic styles -->\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("<!-- base styles -->\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/bootstrap.min.css\" rel=\"stylesheet\"/>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/font-awesome-fa.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/font-awesome-ic.css\"/>\n");
      out.write("\n");
      out.write("<!--[if IE 7]>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/font-awesome-ie7.min.css\" />\n");
      out.write("<![endif]-->\n");
      out.write("\n");
      out.write("<!-- ace styles -->\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace.min.css\"/>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-rtl.min.css\"/>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-skins.min.css\"/>\n");
      out.write("\n");
      out.write("<!--[if lte IE 8]>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-ie.min.css\" />\n");
      out.write("<![endif]-->\n");
      out.write("\n");
      out.write("<!-- ace settings handler -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/ace-extra.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("<!--[if lt IE 9]>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/html5shiv.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/respond.min.js\"></script>\n");
      out.write("<![endif]-->");
      out.write("\n");
      out.write("    <!-- page specific plugin styles -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/jquery-ui-1.10.3.full.min.css\"/>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"main-container\" id=\"main-container\">\n");
      out.write("    <div class=\"page-content\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 style=\"display:inline;\">新生入住手动分配</h1>\n");
      out.write("\n");
      out.write("            <div style=\"display:inline;\" class=\"pull-right\">\n");
      out.write("                <a href=\"javascript:history.go(-1)\" class=\"btn btn-xs btn-info \"> <i class=\"icon-reply icon-only\"></i>返回上一级\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- /.page-header -->\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-xs-12\">\n");
      out.write("                ");
      if (_jspx_meth_s_005fform_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <!-- /.col -->\n");
      out.write("        </div>\n");
      out.write("        <!-- /.row -->\n");
      out.write("        <div id=\"dialog-message\" class=\"modal hide\">\n");
      out.write("            <div class=\"center\">\n");
      out.write("                <h3 id=\"progress-label\" class=\"blue lighter\">准备中...</h3>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- #dialog-message -->\n");
      out.write("    </div>\n");
      out.write("    <!-- /.page-content -->\n");
      out.write("</div>\n");
      out.write("<!-- /.main-container -->\n");
      out.write("\n");
      out.write("<!-- basic scripts -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- basic scripts -->\n");
      out.write("<!--[if !IE]> -->\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\twindow.jQuery || document.write(\"<script src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/jquery-2.0.3.min.js'>\"+\"<\"+\"script>\");\n");
      out.write("</script>\n");
      out.write("<!-- <![endif]-->\n");
      out.write("\n");
      out.write("<!--[if IE]>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write(" window.jQuery || document.write(\"<script src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/jquery-1.10.2.min.js'>\"+\"<\"+\"script>\");\n");
      out.write("</script>\n");
      out.write("<![endif]-->\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\tif(\"ontouchend\" in document) document.write(\"<script src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/jquery.mobile.custom.min.js'>\"+\"<\"+\"script>\");\n");
      out.write("</script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/bootstrap.min.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/typeahead-bs2.min.js\"></script>\n");
      out.write("\n");
      out.write("<!--[if lte IE 8]>\n");
      out.write("  <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/excanvas.min.js\"></script>\n");
      out.write("<![endif]-->\n");
      out.write("\n");
      out.write("<!-- ace scripts -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/ace-elements.min.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/ace.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- Custom JS -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/Utils.js\"></script>");
      out.write("\n");
      out.write("\n");
      out.write("<!-- inline scripts related to this page -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/jquery-ui-1.10.3.full.min.js\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/bootbox.min.js\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    jQuery(function ($) {\n");
      out.write("        //override dialog's title function to allow for HTML titles\n");
      out.write("        $.widget(\"ui.dialog\", $.extend({}, $.ui.dialog.prototype, {\n");
      out.write("            _title: function (title) {\n");
      out.write("                var $title = this.options.title || '&nbsp;'\n");
      out.write("                if ((\"title_html\" in this.options) && this.options.title_html == true)\n");
      out.write("                    title.html($title);\n");
      out.write("                else title.text($title);\n");
      out.write("            }\n");
      out.write("        }));\n");
      out.write("        $(\"#allocation\").click(function () {\n");
      out.write("            $.ajax({\n");
      out.write("                type: \"POST\",\n");
      out.write("                url: \"allocation_manual.action\",\n");
      out.write("                data: $(\"#form\").serialize(),\n");
      out.write("                success: function (result) {\n");
      out.write("                    if (result.status === \"success\") {\n");
      out.write("                        bootbox.dialog({\n");
      out.write("                            message: \"<div class='center'>\" + result.result + \"!</div>\",\n");
      out.write("                            buttons: {\n");
      out.write("                                \"success\": {\n");
      out.write("                                    \"label\": \"确认\",\n");
      out.write("                                    \"className\": \"btn-sm btn-primary\"\n");
      out.write("                                }\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        $(\"#apartment\").change(function () {\n");
      out.write("            var apartmentId = $(this).val();\n");
      out.write("            $.ajax({\n");
      out.write("                url:\"allocation_getReservoirDormitory.action?apartmentId=\"+apartmentId,\n");
      out.write("                success: function (result) {\n");
      out.write("                    console.log(result);\n");
      out.write("                    $(\"#dor\").empty();\n");
      out.write("                    $(\"#dor\").append(\" <option>请选择寝室</option>\");\n");
      out.write("                    $.each(result, function (index, value) {\n");
      out.write("                        $(\"#dor\").append(\"<option value='\"+value.id+\"'>\"+value.name+\"</option>\");\n");
      out.write("                    })\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        $(\"#dor\").change(function () {\n");
      out.write("            var dormitoryId = $(this).val();\n");
      out.write("            $.ajax({\n");
      out.write("                url:\"allocation_getEmptyBed.action?dormitoryId=\"+dormitoryId,\n");
      out.write("                success: function (result) {\n");
      out.write("                    console.log(result);\n");
      out.write("                    $(\"#bed\").empty();\n");
      out.write("                    $(\"#bed\").append(\" <option>请选择床铺</option>\");\n");
      out.write("                    $.each(result, function (index, value) {\n");
      out.write("                        $(\"#bed\").append(\"<option value='\"+value.id+\"'>\"+value.bedNO+\"床</option>\");\n");
      out.write("                    })\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("        });\n");
      out.write("    })\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005fform_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:form
    org.apache.struts2.views.jsp.ui.FormTag _jspx_th_s_005fform_005f0 = (org.apache.struts2.views.jsp.ui.FormTag) _005fjspx_005ftagPool_005fs_005fform_0026_005fid_005fcssClass_005faction.get(org.apache.struts2.views.jsp.ui.FormTag.class);
    _jspx_th_s_005fform_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fform_005f0.setParent(null);
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(32,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fform_005f0.setId("form");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(32,16) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fform_005f0.setAction("");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(32,16) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fform_005f0.setCssClass("form-horizontal");
    int _jspx_eval_s_005fform_005f0 = _jspx_th_s_005fform_005f0.doStartTag();
    if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fform_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fform_005f0.doInitBody();
      }
      do {
        out.write("\n");
        out.write("                    <div class=\"form-group\">\n");
        out.write("                        <label class=\"col-sm-3 control-label no-padding-right\" for> 选择的公寓 </label>\n");
        out.write("\n");
        out.write("                        <div class=\"col-sm-9\">\n");
        out.write("                            ");
        if (_jspx_meth_s_005fselect_005f0(_jspx_th_s_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                            <select id=\"dor\" name=\"dormitoryId\" class=\"col-xs-3\">\n");
        out.write("\n");
        out.write("                            </select>\n");
        out.write("                            <select id=\"bed\" name=\"bedId\" class=\"col-xs-3\">\n");
        out.write("\n");
        out.write("                            </select>\n");
        out.write("                        </div>\n");
        out.write("                    </div>\n");
        out.write("                    <div class=\"space-4\"></div>\n");
        out.write("                    <div class=\"form-group\">\n");
        out.write("                        <label class=\"col-sm-3 control-label no-padding-right\"> 班级 </label>\n");
        out.write("\n");
        out.write("                        <div class=\"col-sm-9\">\n");
        out.write("                            ");
        if (_jspx_meth_s_005fselect_005f1(_jspx_th_s_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        </div>\n");
        out.write("                    </div>\n");
        out.write("                    <div class=\"space-4\"></div>\n");
        out.write("                    <div class=\"form-group\">\n");
        out.write("                        <label class=\"col-sm-3 control-label no-padding-right\" for=\"stuSno\"> 学号 </label>\n");
        out.write("\n");
        out.write("                        <div class=\"col-sm-9\">\n");
        out.write("                            ");
        if (_jspx_meth_s_005ftextfield_005f0(_jspx_th_s_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        </div>\n");
        out.write("                    </div>\n");
        out.write("                    <div class=\"space-4\"></div>\n");
        out.write("                    <div class=\"form-group\">\n");
        out.write("                        <label class=\"col-sm-3 control-label no-padding-right\" for=\"stuName\"> 姓名 </label>\n");
        out.write("\n");
        out.write("                        <div class=\"col-sm-9\">\n");
        out.write("                            ");
        if (_jspx_meth_s_005ftextfield_005f1(_jspx_th_s_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        </div>\n");
        out.write("                    </div>\n");
        out.write("                    <div class=\"space-4\"></div>\n");
        out.write("                    <div class=\"clearfix form-actions\">\n");
        out.write("                        <div class=\"col-md-offset-3 col-md-9\">\n");
        out.write("                            <button class=\"btn btn-info\" type=\"button\" id=\"allocation\">\n");
        out.write("                                <i class=\"icon-ok bigger-110\"></i> 分配\n");
        out.write("                            </button>\n");
        out.write("\n");
        out.write("                            &nbsp; &nbsp; &nbsp;\n");
        out.write("                            <button class=\"btn\" type=\"reset\">\n");
        out.write("                                <i class=\"icon-undo bigger-110\"></i> 重置\n");
        out.write("                            </button>\n");
        out.write("                        </div>\n");
        out.write("                    </div>\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fform_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fform_0026_005fid_005fcssClass_005faction.reuse(_jspx_th_s_005fform_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fform_0026_005fid_005fcssClass_005faction.reuse(_jspx_th_s_005fform_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setId("apartment");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("#apartmentList");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("apartmentId");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setCssClass("col-xs-3");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("id");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("name");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = headerKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setHeaderKey("");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(37,28) name = headerValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setHeaderValue("请选择公寓");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f1 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setList("#claszList");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setName("claszId");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setCssClass("col-xs-10 col-sm-5");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListKey("id");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListValue("name");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = headerKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setHeaderKey("");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(52,28) name = headerValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setHeaderValue("===请选择班级===");
    int _jspx_eval_s_005fselect_005f1 = _jspx_th_s_005fselect_005f1.doStartTag();
    if (_jspx_th_s_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fheaderValue_005fheaderKey_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005ftextfield_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f0 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(61,28) null
    _jspx_th_s_005ftextfield_005f0.setDynamicAttribute(null, "placeholder", "请输入学号");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(61,28) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setName("stuSno");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(61,28) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setCssClass("col-xs-10 col-sm-5");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(61,28) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setId("stuSno");
    int _jspx_eval_s_005ftextfield_005f0 = _jspx_th_s_005ftextfield_005f0.doStartTag();
    if (_jspx_th_s_005ftextfield_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.reuse(_jspx_th_s_005ftextfield_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.reuse(_jspx_th_s_005ftextfield_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005ftextfield_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fform_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f1 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(70,28) null
    _jspx_th_s_005ftextfield_005f1.setDynamicAttribute(null, "placeholder", "请输入姓名");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(70,28) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setName("stuName");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(70,28) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setCssClass("col-xs-10 col-sm-5");
    // /WEB-INF/jsp/allocation/allocationAction/manualUI.jsp(70,28) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setId("stuName");
    int _jspx_eval_s_005ftextfield_005f1 = _jspx_th_s_005ftextfield_005f1.doStartTag();
    if (_jspx_th_s_005ftextfield_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.reuse(_jspx_th_s_005ftextfield_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fplaceholder_005fname_005fid_005fcssClass_005fnobody.reuse(_jspx_th_s_005ftextfield_005f1);
    return false;
  }
}
