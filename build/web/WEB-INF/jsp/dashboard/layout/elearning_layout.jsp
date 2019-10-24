<%-- 
    Document   : elearning
    Created on : Sep 22, 2018, 1:52:51 AM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<!-- saved from url=(0043)http://materialwrap-html.authenticgoods.co/ -->
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" ignore="true" /></title>
        <link href="${pageContext.request.contextPath}/share/Material/css/app.bundle.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/share/Material/css/theme-a.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/share/Material/css/vendor.bundle.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/share/js/jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/js/bootstrap-3.0.2.min.js" type="text/javascript"></script>
        <!--<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>-->
        <link href="${pageContext.request.contextPath}/share/SmartAdmin/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css">
        <script src="${pageContext.request.contextPath}/share/js/jquery.confirm.js"></script>
        <link href="${pageContext.request.contextPath}/share/css/jquery.confirm.css" rel="stylesheet" type="text/css"/>
        <!--<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>-->
    <body class="" style="background-color: #eef5f9"> 
        <div id="app_wrapper">
            <header id="app_topnavbar-wrapper">
                <div role="navigation" class="navbar topnavbar">
                    <div class="nav-wrapper">
                        <ul class="nav navbar-nav pull-left left-menu">
                            <li class="app_menu-open">
                                <a href="javascript:void(0)" data-toggle-state="app_sidebar-left-open" data-key="leftSideBar">
                                    <i class="zmdi zmdi-menu"></i>
                                </a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav left-menu hidden-xs">
                            <li>
                                <a href="/Elearning/TblExam/examList.html" onclick="" class="nav-link">
                                    <span>Đề thi thử</span>
                                </a>
                            </li>
                            <li class="dropdown dropdown-lg app_menu_launcher hidden-xs">
                                <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false">
                                    <span>Quản lý học tập</span>
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-lg-menu dropdown-menu-left btn-primary p-15 text-center">
                                    <li>
                                        <ul>
                                            <li><a href="/Elearning/Plans.html"><i class="zmdi zmdi-email"></i><span>Thời gian biểu</span></a></li>
                                            <li><a href="/Elearning/examResult.html"><i class="zmdi zmdi-accounts-list"></i><span>Kết quả học tập</span></a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </header>
            <tiles:insertAttribute name="leftside" />
            <section id="content_outer_wrapper" style="padding-bottom: 0px">
                <div id="content_wrapper" class="rightnav_v2 toggle-right col-sm-12" style="margin-top: 15px;height: 100% !important;" >
                    <div id="bodyContent" class="container-fluid col-sm-9" style="">
                        <tiles:insertAttribute name="body" />
                    </div>
                    <div id="rightContent" class="col-sm-3">
                        <tiles:insertAttribute name="rightside" />
                    </div>
                </div>
                <footer id="footer_wrapper" style="width: 100%">
                    <div class="footer-content">
                        <div class="row copy-wrapper">
                            <div class="col-xs-8">
                                <p class="copy">© Copyright <time class="year">2019</time> BK ĐứcNT - All Rights Reserved</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </section>

        </div>
        <!--<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>-->
        <!--<script src="${pageContext.request.contextPath}/share/Material/js/vendor.bundle.js"></script>-->
        <!--<script src="${pageContext.request.contextPath}/share/Material/js/app.bundle.js"></script>-->
        <link href="${pageContext.request.contextPath}/share/plugin/notifit/notifit.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/share/plugin/notifit/notifit.js" type="text/javascript"></script>

        <script>
//            var closeOpenState = function closeOpenState() {
//                $('#app_sidebar-left').on('mouseleave', function () {
//                    if ($('body.app_sidebar-menu-collapsed').length > 0) {
//                        $('.nav-dropdown').each(function () {
//                            if ($(this).hasClass('open') && !$(this).hasClass('active')) {
//                                $(this).removeClass('open');
//                                $(this).children('.nav-sub').removeAttr("style");
//                            }
//                        });
//                    }
//                });
//            };
//            var switchMenuState = function switchMenuState() {
//                var $body = $('body');
//                var $html = $('html');
//                if ($(window).width() < 992 && !$html.hasClass('backdrop-open')) {
//                    $body.removeClass('app_sidebar-menu-collapsed');
//                    $('#content_wrapper').removeClass('toggle-left toggle-right');
//                } else if (!$html.hasClass('backdrop-open')) {
//                    $body.removeClass('app_sidebar-left-open');
//                }
//            };
////            closeOpenState();
//            switchMenuState();
            $(function () {
                $("#mouseposition-extension-element-full-container").remove();
                $("#bukket-ext-preview-layer,#bukket-ext-invisible-layer,#bukket-ext-recorder-layer,#bukket-ext-sidebar,#bukket-ext-add-button,#mouseposition-extension-element-full-container").remove();
            });

            function notifSuccess(msg) {
                notif({
                    type: 'success',
                    position: 'bottom',
                    msg: msg
                });
            }
            function notifError(msg) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: msg
                });
            }
        </script>
        <style>
            #homeSubmenu::before{
                border-left:none !important;
            }
            #homeSubmenu li::before{
                border-top: none !important;
            }
            nav>ul>li>a>i {
                margin-right: 0px !important; 
                width: 0px !important;
            }
        </style>
    </body>
</html>
