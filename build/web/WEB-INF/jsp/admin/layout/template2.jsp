<%-- 
    Document   : template2
    Created on : Sep 11, 2018, 1:52:24 PM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <%--<c:set var="title"><tiles:getAsString name="title" ignore="true" /></c:set>--%>
       <!--<title><fmt:message key='${fn:escapeXml(title)}'/></title>-->
        <title><tiles:getAsString name="title" ignore="true" /></title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/Ionicons/css/ionicons.min.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/jvectormap/jquery-jvectormap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/dist/css/skins/_all-skins.min.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/share/image/hoa.ico">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <!-- jQuery 3 -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/dist/js/adminlte.min.js"></script>
        <!-- Sparkline -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
        <!-- jvectormap  -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- SlimScroll -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- ChartJS -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/chart.js/Chart.js"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/dist/js/pages/dashboard2.js"></script>
        <!-- AdminLTE for demo purposes -->
        <!--<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>-->
        <!--<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>-->
        <script src="${pageContext.request.contextPath}/share/AdminLTE/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/share/plugin/notifit/notifit.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/share/plugin/notifit/notifit.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/js/tooltip.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/select2/dist/css/select2.min.css">
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/select2/dist/js/select2.full.min.js"></script>
        <script src="${pageContext.request.contextPath}/share/js/jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/js/bootstrap-3.0.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/js/jquery.confirm.js"></script>
        <link href="${pageContext.request.contextPath}/share/css/jquery.confirm.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/share/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/js/elearning_core.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/select2/dist/js/select2.full.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/plugins/iCheck/all.css">
        <script src="${pageContext.request.contextPath}/share/AdminLTE/plugins/iCheck/icheck.min.js"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    </head>
    <!--<body class="hold-transition skin-blue sidebar-mini">-->
    <body class="hold-transition skin-black-light sidebar-mini">
        <input type="hidden" id="pagingLabelPrevious" value="Trang trước" />
        <input type="hidden" id="pagingLabelNext" value="Trang tiếp" />
        <input type="hidden" id="pagingLabelDisplay" value="Hiển thị từ" />
        <input type="hidden" id="pagingLabelNumberElement" value="bản ghi" />
        <div class="wrapper">
            <header class="main-header">
                <!-- Logo -->
                <a href="index2.html" class="logo">
                    <span class="logo-mini"><b>A</b>LT</span>
                    <span class="logo-lg"><b>Elearning</b></span>
                </a>

                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="${pageContext.request.contextPath}/share/AdminLTE/dist/img/user4-128x128.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">${fn:escapeXml(sessionScope.CURRENT_USER.username)}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="${pageContext.request.contextPath}/share/AdminLTE/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                        <p>
                                            ${fn:escapeXml(sessionScope.CURRENT_USER.fullName)}
                                            <small>${fn:escapeXml(sessionScope.CURRENT_USER.highSchool)}</small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">

                                        <div class="pull-right">
                                            <a href="/Elearning/Logout.html" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <tiles:insertAttribute name="leftside" />

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <!-- Main content -->
                <tiles:insertAttribute name="body" />
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.4.0
                </div>
                <strong>Copyright &copy; 2018 <a href="">THCS Phùng Xá</a>.</strong> All rights
                reserved.
            </footer>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                Create the tabs 
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                    <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                </ul>
                Tab panes 
                <div class="tab-content">
                    <div class="tab-pane" id="control-sidebar-home-tab">
                        <h3 class="control-sidebar-heading">Recent Activity</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                        <p>Will be 23 on April 24th</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-user bg-yellow"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                                        <p>New phone +1(800)555-1234</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                                        <p>nora@example.com</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-file-code-o bg-green"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                                        <p>Execution time 5 seconds</p>
                                    </div>
                                </a>
                            </li>
                        </ul>

                        <h3 class="control-sidebar-heading">Tasks Progress</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Custom Template Design
                                        <span class="label label-danger pull-right">70%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Update Resume
                                        <span class="label label-success pull-right">95%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Laravel Integration
                                        <span class="label label-warning pull-right">50%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Back End Framework
                                        <span class="label label-primary pull-right">68%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                                    </div>
                                </a>
                            </li>
                        </ul>

                    </div>
                    <div class="tab-pane" id="control-sidebar-settings-tab">
                        <form method="post">
                            <h3 class="control-sidebar-heading">General Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Report panel usage
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Some information about this general settings option
                                </p>
                            </div>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Allow mail redirect
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Other sets of options are available
                                </p>
                            </div>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Expose author name in posts
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Allow the user to show his name in blog posts
                                </p>
                            </div>

                            <h3 class="control-sidebar-heading">Chat Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Show me as online
                                    <input type="checkbox" class="pull-right" checked>
                                </label>
                            </div>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Turn off notifications
                                    <input type="checkbox" class="pull-right">
                                </label>
                            </div>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Delete chat history
                                    <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                                </label>
                            </div>
                        </form>
                    </div>
                </div>
            </aside>
            <div class="control-sidebar-bg"></div>

        </div>
        <!-- ./wrapper -->

    </body>
</html>
