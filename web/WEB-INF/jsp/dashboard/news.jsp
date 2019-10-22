<%-- 
    Document   : news
    Created on : Oct 22, 2018, 9:48:05 PM
    Author     : phith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
<form action="/Elearning/TblNews/news.html">
    <div class="row" style="display: flex">
        <div class="col-md-4" style="display: flex">
            <label style="padding-top: 8px;white-space:nowrap;">Từ khóa</label>
            <input style="margin-left: 12px;" type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Từ khóa" />
        </div>
        <div class="col-md-6" style="display: flex">
            <label for="fromDate" class="control-label col-md-2">Từ ngày</label>
            <div class="controls col-xs-4">
                <input class="input-md form-control datepicker" id="fromDate" name="fromDate" placeholder="Từ ngày" value="${fromDate}"  type="text" />
            </div>
            <label for="toDate" class="control-label col-md-2">Đến ngày</label>
            <div class="controls col-xs-4">
                <input class="input-md form-control datepicker" id="toDate" name="toDate" placeholder="Đến ngày" value="${toDate}"  type="text" />
            </div>
        </div>
        <div class="col-md-2" style="display: flex">
            <input type="submit" value="Lọc" class="btn btn-warning" />
        </div>
    </div>
</form>
<div class="row">
    <div class="well padding-10">
        <c:choose>
            <c:when test="${lstItem.lstNewsDTOs.size() > 0}">
                <c:forEach items="${lstItem.lstNewsDTOs}" var = "item">
                    <div class="row">
                        <div class="col-md-2">
                            <img src="${pageContext.request.contextPath}/share/image/520540984-612x612.jpg" class="img-responsive" alt="img">
                            <ul class="list-inline padding-10">
                                <li>
                                    <i class="fa fa-calendar"></i>
                                    <a href="/Elearning/TblNews/newsdetails.html?id=${item.newId}" onclick="/Elearning/TblNews / newsdetails.html?id =${item.newId}">${item.dateCreated}</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-10 padding-left-0">
                            <h3 class="margin-top-0"><a href="/Elearning/TblNews/newsdetails.html?id=${item.newId}" onclick="/Elearning/TblNews / newsdetails.html?id =${item.newId}">  ${item.title} </a><br></h3>
                            <p>
                                <i class="fa fa-delicious"></i>
                                <a href="/Elearning/TblNews/newsdetails.html?id=${item.newId}">${item.topicName}</a>
                            </p>
                            <p>
                                <c:choose>
                                    <c:when test="${fn:length(item.content)<300}">
                                        <c:out value="${item.content}"></c:out>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${fn:substring(item.content, 0, 300)}......."></c:out>
                                    </c:otherwise>
                                </c:choose>

                                <br>
                            </p>
                            <a class="btn btn-primary" href="/Elearning/TblNews/newsdetails.html?id=${item.newId}" onclick="/Elearning/TblNews / newsdetails.html?id =${item.newId}" style="color: #fff">Đọc tiếp</a>
                        </div>
                    </div>	
                    <hr/>
                </c:forEach>
                <c:if test="${!empty lstItem.listPaging}">
                    <table class="pagination-table" border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <c:if test="${lstItem.currentPage > 1}">
                                <td><a href="examList.html?page=${lstItem.currentPage - 1}">«</a></td>
                            </c:if>
                            <c:forEach items="${lstItem.listPaging}" var="i">
                                <c:choose>
                                    <c:when test="${lstItem.currentPage eq i}">
                                        <td class="active">${i}</td>
                                    </c:when>
                                    <c:when test="${i eq 0}">
                                        <td class="load">...</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="examList.html?page=${i}">${i}</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${lstItem.currentPage < lstItem.totalPages}">
                                <td><a href="examList.html?page=${lstItem.currentPage + 1}">»</a></td>
                            </c:if>
                        </tr>
                    </table>
                </c:if>
            </c:when>
            <c:otherwise>
                <div class="row" style="margin-left: 10px;">Không có bài viết nào</div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script>
    $('.datepicker').datepicker({
        autoclose: true,
        duration: "fast",
        changeMonth: true,
        changeYear: true,
        format: 'dd/mm/yyyy',
        constrainInput: true,
        maxDate: 0,
        yearRange: "-70:+0"
    });
</script>
<style>
    .well h4, .well p {
        padding: -2px -1!important;
        margin: 0!important;
    }
    .well h4, .well p {
        padding: -2px -1!important;
        margin: 0!important;
    }
    /*Pagination*/
    .pagination-table {
        margin: 0px;
        border: none;
        float: right;
    }
    .pagination-table a {
        color: #3276b1;
        padding: 7px 13px;
    }
    .pagination-table td {
        border-color: #dee2e6;
        text-align:center;
        min-width: 35px;
        padding: 0px;
    }
    .pagination-table td.load {
        color: #3276b1;
    }
    .pagination-table td.active {
        padding: 5px 8px;
    }
    .pagination-table td.active, .pagination-table td:hover{
        color: #fff;
        background-color: #3276b1;
        border-color: #3276b1;
    }
    .pagination-table td:hover a {
        color: #fff;
    }
</style>