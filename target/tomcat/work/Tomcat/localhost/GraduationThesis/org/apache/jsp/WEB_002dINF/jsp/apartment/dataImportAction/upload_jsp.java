/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2014-09-23 11:52:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.apartment.dataImportAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.*;

public final class upload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/jsp/public/commons-styles.jspf", Long.valueOf(1409907148000L));
    _jspx_dependants.put("/WEB-INF/jsp/public/commons-scripts.jspf", Long.valueOf(1409907148000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      out.write("    <title>欢迎使用宿舍管理系统</title>\n");
      out.write("    <meta name=\"keywords\" content=\"\"/>\n");
      out.write("    <meta name=\"description\" content=\"\"/>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n");
      out.write("    <!-- page specific plugin styles -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/dropzone.css\"/>\n");
      out.write("\n");
      out.write("    <!-- basic styles -->\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("\t<!-- base styles -->\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/bootstrap.min.css\" rel=\"stylesheet\" />\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/font-awesome.min.css\" />\n");
      out.write("\t\n");
      out.write("\t<!--[if IE 7]>\n");
      out.write("\t  <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/font-awesome-ie7.min.css\" />\n");
      out.write("\t<![endif]-->\n");
      out.write("\t\n");
      out.write("\t<!-- ace styles -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace.min.css\" />\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-rtl.min.css\" />\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-skins.min.css\" />\n");
      out.write("\t\n");
      out.write("\t<!--[if lte IE 8]>\n");
      out.write("\t  <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/ace-ie.min.css\" />\n");
      out.write("\t<![endif]-->\n");
      out.write("\t\n");
      out.write("\t<!-- ace settings handler -->\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/ace-extra.min.js\"></script>\n");
      out.write("\t\n");
      out.write("\t<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("\t<!--[if lt IE 9]>\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/html5shiv.js\"></script>\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/respond.min.js\"></script>\n");
      out.write("\t<![endif]-->");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"main-container\" id=\"main-container\">\n");
      out.write("    <div class=\"page-content\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-xs-12\">\n");
      out.write("                <!-- PAGE CONTENT BEGINS -->\n");
      out.write("\n");
      out.write("                <div class=\"alert alert-info\">\n");
      out.write("                    <i class=\"icon-hand-right\"></i>\n");
      out.write("                    请选择Excel文件上传，从而进行数据的导入。需要符合一定的格式要求。\n");
      out.write("                    <button class=\"close\" data-dismiss=\"alert\">\n");
      out.write("                        <i class=\"icon-remove\"></i>\n");
      out.write("                    </button>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"dropzone\">\n");
      out.write("                    <form action=\"//dummy.html\" class=\"dropzone\">\n");
      out.write("                        <div class=\"fallback\">\n");
      out.write("                            <input name=\"file\" type=\"file\" multiple=\"\"/>\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                <!-- PAGE CONTENT ENDS -->\n");
      out.write("            </div>\n");
      out.write("            <!-- /.col -->\n");
      out.write("        </div>\n");
      out.write("        <!-- /.row -->\n");
      out.write("    </div>\n");
      out.write("    <!-- /.page-content -->\n");
      out.write("</div>\n");
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
      out.write("\n");
      out.write("\n");
      out.write("<!-- page specific plugin scripts -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/dropzone.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- inline scripts related to this page -->\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    jQuery(function ($) {\n");
      out.write("        try {\n");
      out.write("            $(\".dropzone\").dropzone({\n");
      out.write("                url:\"dataImport_upload.action\",\n");
      out.write("                paramName: \"file\", // The name that will be used to transfer the file\n");
      out.write("                maxFilesize: 5, // MB\n");
      out.write("                addRemoveLinks: true,\n");
      out.write("                acceptedFiles:\".xls,.xlsx\",\n");
      out.write("                dictDefaultMessage: '<span class=\"bigger-150 bolder\"><i class=\"icon-caret-right red\"></i> 拖拽文件</span>上传 \\\n");
      out.write("                        <span class=\"smaller-80 grey\">(或者 点击选择文件上传)</span> <br /> \\\n");
      out.write("                        <i class=\"upload-icon icon-cloud-upload blue icon-3x\"></i>',\n");
      out.write("                dictResponseError: '上传文件失败!',\n");
      out.write("                //change the previewTemplate to use Bootstrap progress bars\n");
      out.write("                previewTemplate: \"<div class=\\\"dz-preview dz-file-preview\\\">\\n  <div class=\\\"dz-details\\\">\\n    <div class=\\\"dz-filename\\\"><span data-dz-name></span></div>\\n    <div class=\\\"dz-size\\\" data-dz-size></div>\\n    <img data-dz-thumbnail />\\n  </div>\\n  <div class=\\\"progress progress-small progress-striped active\\\"><div class=\\\"progress-bar progress-bar-success\\\" data-dz-uploadprogress></div></div>\\n  <div class=\\\"dz-success-mark\\\"><span></span></div>\\n  <div class=\\\"dz-error-mark\\\"><span></span></div>\\n  <div class=\\\"dz-error-message\\\"><span data-dz-errormessage></span></div>\\n</div>\"\n");
      out.write("            });\n");
      out.write("        } catch (e) {\n");
      out.write("            alert('Dropzone.js does not support older browsers!');\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    });\n");
      out.write("\n");
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
}
