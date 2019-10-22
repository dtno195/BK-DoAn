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
<html dir="ltr">
    <head>

        <script>
            var themeHasJQuery = !!window.jQuery;
        </script>
        <script src="${pageContext.request.contextPath}/share/js/jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/share/js/jquery.countdown.js" type="text/javascript"></script>
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/share/js/jquery.js?1.0.705"></script>-->
        <script>
            window._$ = jQuery.noConflict(themeHasJQuery);
        </script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/css/bootstrap.css?1.0.705" media="screen" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/share/js/bootstrap.min.js?1.0.705"></script>
        <link class="" href='//fonts.googleapis.com/css?family=Open+Sans:300,300italic,regular,italic,600,600italic,700,700italic,800,800italic&subset=latin' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="${pageContext.request.contextPath}/share/js/layout.core.js"></script>
        <script src="${pageContext.request.contextPath}/share/js/CloudZoom.js?1.0.705"></script>
        <%--<c:set var="title"><tiles:getAsString name="title" ignore="true" /></c:set>--%>
        <!--<title><fmt:message key='${fn:escapeXml(title)}'/></title>-->
        <title><tiles:getAsString name="title" ignore="true" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/css/style.css?1.0.705">
        <script src="${pageContext.request.contextPath}/share/js/script.js?1.0.705"></script>
        <meta charset="utf-8">
        <meta name="keywords" content="HTML, CSS, JavaScript">
        <style>a {
                transition: color 250ms linear;
            }
        </style>
    </head>
    <body>     
        <tiles:insertAttribute name="header" />
        <div class="body" id="divBodyContent"  >  
            <tiles:insertAttribute name="body" />
        </div>  
        <tiles:insertAttribute name="footer" /> 
    </body>
</html>
