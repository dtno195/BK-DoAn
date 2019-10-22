<%-- 
    Document   : elearning_right
    Created on : Sep 23, 2018, 12:17:50 AM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
    .no-padding{
        padding: 0px !important;
    }
</style>
<div class="">
    <div class="col-lg-12 no-padding">
        <div class="card type--profile">
            <ul class="list-group p-0">
                <c:forEach  var="item" begin="0" end="10" items="${rightSide}">
                    <li class="list-group-item ">
                        <span class="pull-left">
                            <i class="fa fa-book"></i>
                        </span>
                        <div class="list-group-item-body">
                            <div class="list-group-item-heading"><a href="/Elearning/TblNews/newsdetails.html?id=${item.newId}">${item.title}</a></div>
                            <div class="list-group-item-text">[${item.dateCreated}] 
                                 <c:choose>
                                    <c:when test="${fn:length(item.content)<100}">
                                        <c:out value="${item.content}"></c:out>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${fn:substring(item.content, 0, 100)}......."></c:out>
                                    </c:otherwise>
                                </c:choose></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="card-footer">
                <ul class="more">
                    <li>
                        <a href="/Elearning/tblnews/news.html">Xem thêm</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<style>
    span.pull-left{
        margin-right: 5px;
    }
</style>