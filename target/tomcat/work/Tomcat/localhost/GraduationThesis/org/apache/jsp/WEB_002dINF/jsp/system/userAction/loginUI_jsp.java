/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2014-10-16 04:47:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.system.userAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.*;
import java.util.*;

public final class loginUI_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/jsp/public/commons-scripts.jspf", Long.valueOf(1412952013572L));
    _jspx_dependants.put("/WEB-INF/jsp/public/commons-styles.jspf", Long.valueOf(1412929830053L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fform_0026_005frole_005fnamespace_005faction;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fform_0026_005frole_005fnamespace_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005fform_0026_005frole_005fnamespace_005faction.release();
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"zh-CN\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\"/>\n");
      out.write("    <title>登录页面</title>\n");
      out.write("    <meta name=\"keywords\" content=\"\"/>\n");
      out.write("    <meta name=\"description\" content=\"\"/>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n");
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
      out.write("    <style type=\"text/css\">\n");
      out.write("        body {\n");
      out.write("            padding-top: 40px;\n");
      out.write("            padding-bottom: 40px;\n");
      out.write("            background-color: #f5f5f5;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body class=\"login-layout\">\n");
      out.write("<div class=\"main-container\">\n");
      out.write("    <div class=\"main-content\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-sm-10 col-sm-offset-1\">\n");
      out.write("                <div class=\"center\" style=\"padding-left:60px;padding-bottom: 60px\">\n");
      out.write("                    <h1 style=\"display: inline\">\n");
      out.write("                        <i class=\"icon-leaf green\"></i>\n");
      out.write("                        <span class=\"red\">数字校园-学生宿舍管理系统</span>\n");
      out.write("                    </h1>\n");
      out.write("                    <h5 style=\"display: inline\" class=\"blue\">&copy;2014 别志华</h5>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"login-container\">\n");
      out.write("                    <div class=\"position-relative\">\n");
      out.write("                        <div id=\"login-box\" class=\"panel panel-default\">\n");
      out.write("                            <div class=\"widget-body\">\n");
      out.write("                                <div class=\"widget-main\">\n");
      out.write("                                    <h4 class=\"header blue lighter bigger\">\n");
      out.write("                                        <i class=\"icon-coffee green\"></i>\n");
      out.write("                                        请输入你的登陆信息\n");
      out.write("                                    </h4>\n");
      out.write("\n");
      out.write("                                    <div class=\"space-6\"></div>\n");
      out.write("\n");
      out.write("                                    ");
      if (_jspx_meth_s_005fform_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                                <!-- /widget-main -->\n");
      out.write("                            </div>\n");
      out.write("                            <!-- /widget-body -->\n");
      out.write("                        </div>\n");
      out.write("                        <!-- /login-box -->\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /position-relative -->\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- /.col -->\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
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
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("     if (window.parent != window) {\n");
      out.write("         window.parent.location.reload();\n");
      out.write("     }\n");
      out.write("\n");
      out.write("    function show_box(id) {\n");
      out.write("        jQuery('.widget-box.visible').removeClass('visible');\n");
      out.write("        jQuery('#' + id).addClass('visible');\n");
      out.write("    }\n");
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
    org.apache.struts2.views.jsp.ui.FormTag _jspx_th_s_005fform_005f0 = (org.apache.struts2.views.jsp.ui.FormTag) _005fjspx_005ftagPool_005fs_005fform_0026_005frole_005fnamespace_005faction.get(org.apache.struts2.views.jsp.ui.FormTag.class);
    _jspx_th_s_005fform_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fform_005f0.setParent(null);
    // /WEB-INF/jsp/system/userAction/loginUI.jsp(46,36) null
    _jspx_th_s_005fform_005f0.setDynamicAttribute(null, "role", "form");
    // /WEB-INF/jsp/system/userAction/loginUI.jsp(46,36) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fform_005f0.setAction("user_login");
    // /WEB-INF/jsp/system/userAction/loginUI.jsp(46,36) name = namespace type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fform_005f0.setNamespace("/system");
    int _jspx_eval_s_005fform_005f0 = _jspx_th_s_005fform_005f0.doStartTag();
    if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fform_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fform_005f0.doInitBody();
      }
      do {
        out.write("\n");
        out.write("                                        <fieldset>\n");
        out.write("                                            <label class=\"block clearfix\">\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"block input-icon input-icon-right\">\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input name=\"loginName\" type=\"text\" class=\"form-control\"\n");
        out.write("                                                                   placeholder=\"登录名\"/>\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"icon-user\"></i>\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n");
        out.write("                                            </label>\n");
        out.write("\n");
        out.write("                                            <label class=\"block clearfix\">\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"block input-icon input-icon-right\">\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input name=\"password\" type=\"password\" class=\"form-control\"\n");
        out.write("                                                                   placeholder=\"密码\"/>\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"icon-lock\"></i>\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n");
        out.write("                                            </label>\n");
        out.write("                                                ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginError}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\n");
        out.write("                                            <div class=\"space\"></div>\n");
        out.write("\n");
        out.write("                                            <div class=\"clearfix\">\n");
        out.write("                                                <label class=\"inline\">\n");
        out.write("                                                    <input type=\"checkbox\" class=\"ace\"/>\n");
        out.write("                                                    <span class=\"lbl\"> 记住我的信息</span>\n");
        out.write("                                                </label>\n");
        out.write("\n");
        out.write("                                                <button type=\"submit\"\n");
        out.write("                                                        class=\"width-35 pull-right btn btn-sm btn-primary\">\n");
        out.write("                                                    <i class=\"icon-key\"></i>\n");
        out.write("                                                    登录\n");
        out.write("                                                </button>\n");
        out.write("                                            </div>\n");
        out.write("\n");
        out.write("                                            <div class=\"space-4\"></div>\n");
        out.write("                                        </fieldset>\n");
        out.write("                                    ");
        int evalDoAfterBody = _jspx_th_s_005fform_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fform_0026_005frole_005fnamespace_005faction.reuse(_jspx_th_s_005fform_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fform_0026_005frole_005fnamespace_005faction.reuse(_jspx_th_s_005fform_005f0);
    return false;
  }
}