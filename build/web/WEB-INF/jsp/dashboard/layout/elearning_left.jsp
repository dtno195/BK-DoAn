<%-- 
    Document   : elearning_left
    Created on : Sep 22, 2018, 1:58:27 AM
    Author     : David
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside id="app_sidebar-left">
    <div id="logo_wrapper">
        <ul>
            <li class="logo-icon">
                <a href="/Elearning/dashboard.html">
                    <div class="logo">
                        <img src="${pageContext.request.contextPath}/share/image/Graduation.png" alt=""/>
                    </div>
                    <h1 class="brand-text" style="margin-left: 5px;font-weight: bold;margin-top: 20px;">Elearning</h1>
                </a>
            </li>
            <li class="menu-icon">
                <a id="leftSideBar" href="javascript:void(0)" role="button" data-toggle-state="app_sidebar-menu-collapsed" data-key="leftSideBar" class="">
                    <i class="fa fa-bars " style="color: white"></i>
                </a>
            </li>
        </ul>
    </div>
    <div class="wrapper">
        <!-- Sidebar -->
        <nav id="sidebar-left">
            <ul class="list-unstyled components nav nav-pills nav-stacked">
                <li class="active">
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="true" class="dropdown-toggle">
                        <i class="zmdi zmdi-view-dashboard"></i>
                        Chủ đề học tập
                    </a>
                    <ul class="collapse list-unstyled in" id="homeSubmenu" aria-expanded="true">
                        <c:set value="0" var="count" />
                        <c:forEach var="item" items="${leftSide}">
                            <li>
                                <c:choose>
                                    <c:when test="${fn:length(item.lstTopic) > 0}">
                                        <c:set var="count" value="${count+1}" />
                                        <a href="#homeSubmenu${count}" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                                            <i class="zmdi zmdi-view-dashboard"></i>
                                            ${item.subjectName}
                                        </a>
                                        <ul class="collapse list-unstyled" id="homeSubmenu${count}">
                                            <c:forEach var="topic" items="${item.lstTopic}">
                                                <li>
                                                    <a href="/Elearning/tblnews/news.html?topicId=${topic.id}">${topic.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/Elearning/tblnews/news.html?subjectId=${item.subjectId}"> ${item.subjectName}</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <a href="/Elearning/Logout.html">Đăng xuất </a>
                </li>
        </nav>

    </div>
</aside>
<style>
    .wrapper {
        display: flex;
        width: 100%;
        align-items: stretch;
    }
    .wrapper {
        display: flex;
        align-items: stretch;
    }

    /*    #sidebar-left {
            min-width: 250px;
            max-width: 250px;
        }
    
        #sidebar-left.active {
            margin-left: -250px;
        }
        #sidebar-left {
            min-width: 250px;
            max-width: 250px;
            min-height: 100vh;
        }*/
    a[data-toggle="collapse"] {
        position: relative;
    }

    #sidebar-left .dropdown-toggle::after {
        display: block;
        position: absolute;
        top: 50%;
        right: 20px;
        transform: translateY(-50%);
    }
    @media (max-width: 768px) {
        #sidebar-left {
            margin-left: -250px;
        }
        #sidebar-left.active {
            margin-left: 0;
        }
    }


    #sidebar-left {
        /* don't forget to add all the previously mentioned styles here too */
        /*background: #7386D5;*/
        color: #fff;
        transition: all 0.3s;
        width: 100%;
    }

    #sidebar-left .sidebar-header {
        padding: 20px;
        /*background: #6d7fcc;*/
    }

    #sidebar-left ul.components {
        padding: 20px 0;
        /*border-bottom: 1px solid #47748b;*/
    }

    #sidebar-left ul p {
        color: #fff;
        padding: 10px;
    }

    #sidebar-left ul li a {
        padding: 10px;
        font-size: 1.1em;
        display: block;
    }
    #sidebar-left ul li a:hover {
        color: #7386D5;
        background: #fff;
    }

    #sidebar-left ul li.active > a, a[aria-expanded="true"] {
        background-color: #1c2732;
        color: #fff;
        border-left-color: #ec407a;
    }
    #sidebar-left .dropdown-toggle::after {
        display: block;
        position: absolute;
        top: 50%;
        right: 20px;
        transform: translateY(-50%);
    }
    #sidebar-left .dropdown-toggle::after {
        display: inline-block;
        width: 0;
        height: 0;
        margin-left: .255em;
        vertical-align: .255em;
        content: "";
        border-top: .3em solid;
        border-right: .3em solid transparent;
        border-bottom: 0;
        border-left: .3em solid transparent;
    }
    #logo_wrapper > ul > li:first-child{
        width: 75%;
        float: left;
    }
    #leftSideBar{
        text-align: right;
    }
    #footer_wrapper .footer-content p{
        font-size: 1.0125em !important;
    }
</style>
<script>
    $(function () {
        $(".app_menu-open a[data-key=\"leftSideBar\"]").click(function () {
            if ($("body").hasClass("app_sidebar-left-open")) {
                $("body").removeClass("app_sidebar-left-open");
                $("#logo_wrapper ul li .logo img").css("left", "13px");
                $("html").removeClass("backdrop-open");
            } else {
                $("html").addClass("backdrop-open");
                $("body").addClass("app_sidebar-left-open");
                $("#logo_wrapper ul li .logo img").css("left", "3px");
            }
        });
        $("#leftSideBar").click(function () {
            if ($("body").hasClass("app_sidebar-menu-collapsed")) {
                $("body").removeClass("app_sidebar-menu-collapsed");
                $("#logo_wrapper ul li .logo img").css("left", "13px");
            } else {
                $("body").addClass("app_sidebar-menu-collapsed");
                $("#logo_wrapper ul li .logo img").css("left", "3px");
            }
        });
    });
</script>