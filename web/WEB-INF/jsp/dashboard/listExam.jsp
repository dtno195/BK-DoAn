<%-- 
    Document   : newsDetail
    Created on : Oct 22, 2018, 8:03:32 PM
    Author     : phith
--%>
<%@page import="com.ptk.elearning.util.Converter"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="/Elearning/TblExam/examList.html">
    <div class="row" style="display: flex">
        <div class="col-md-5" style="display: flex">
            <label style="padding-top: 8px;white-space:nowrap;">Từ khóa</label>
            <input style="margin-left: 12px;" type="text" name="keyword" value="${keyword}" class="form-control" />
        </div>
        <div class="col-md-5" style="display: flex">
            <label style="padding-top: 8px;white-space:nowrap;">Môn học</label>
            <select style="margin-left: 12px;" class="form-control" name="subject">
                <c:forEach items="${lstSubject}" var="obj">
                    <option ${subject==obj.dvsValue?"selected":""} value="${obj.dvsValue}">${obj.dvsName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-2" style="display: flex">
            <input type="submit" value="Lọc" class="btn btn-warning" />
        </div>
    </div>
</form>
<div class="row">
    <div class="well padding-10">

        <c:forEach items="${lstItem.lstExamDTOs}" var="item">
            <div class="row">
                <div class="col-md-2">
                    <img src="${pageContext.request.contextPath}/share/image/520540984-612x612.jpg" class="img-responsive"
                         alt="img">
                    <ul class="list-inline padding-10">
                        <li>
                            <i class="fa fa-calendar"></i>
                            <a href="/Elearning/examTest.html?id=${item.examId}">${item.dateCreated}</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-10 padding-left-0">
                    <h3 class="margin-top-0"><a href="javascript:void(0);"> ${item.name} </a><br></h3>
                    <p>
                        <i class="fa fa-clock-o"></i>
                        <a href="/Elearning/examTest.html?id=${item.examId}">${item.timeName}</a>
                    </p>
                    <p>
                        <i class="fa fa-book"></i><a href="/Elearning/examTest.html?id=${item.examId}">${item.subjectName}</a>
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
                    <a class="btn btn-primary" href="/Elearning/examTest.html?id=${item.examId}" onclick="" style="color: #fff">Thi
                        thử</a>
                </div>
            </div>
            <hr />
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
    </div>
</div>
<div class="modal fade" id="importRegister" role="dialog" tabindex="-1" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Đóng góp đề thi</h4>
            </div>
            <div class="modal-body">
                <form id="frm_import" class="form-horizontal form-margin">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Tên đề thi:<span class="required">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" name="name" id="name" class="form-control" placeholder="Tên đề thi" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contentEditor" class="col-sm-2 control-label">Nội dung:<span class="required">*</span></label>
                        <div class="col-sm-10">
                            <textarea id="contentEditor" name="contentEditor">
                            </textarea>
                            <input type="hidden" name="content" id="content">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="subjectId" class="col-sm-2 control-label">Môn học:<span class="required">*</span></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="subject">
                                <c:forEach items="${lstSubject}" var="obj">
                                    <option value="${obj.dvsValue}">${obj.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="timeId" class="col-sm-2 control-label">Thời gian:<span class="required">*</span></label>
                        <div class="col-sm-4">
                            <select class="form-control" name="subject">
                                <c:forEach items="${lstTime}" var="obj">
                                    <option value="${obj.dvsValue}">${obj.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group custom-margin" style="padding-left: 10px !important">
                        <label class="btn btn-success">
                            Lựa chọn file import câu hỏi
                            <input accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                   type="file" id="fileImport" accept="" name="files" value="files" style="display: none;">
                        </label>
                    </div>
                    <div class="form-group custom-margin" style="padding-left: 10px !important">
                        <span id="fileUploadName"></span><br />
                        <span style="color: red" id="fileUploadNameError"></span>
                    </div>
                    <div class="form-group custom-margin">
                        <label class="col-md-12">Định dạng file cho phép: .XLS, .XLSX</label>
                    </div>
                    <div class="form-group custom-margin">
                        <label class="col-md-12">Dung lượng tối đa: 20Mb</label>
                    </div>
                    <div class="form-group custom-margin">
                        <label class="col-md-12">File mẫu: <a href="javascript:void(0)" title="Tải xuống với định dạng xls"
                                                              onclick="downloadTemplate(1);">XLS</a> | <a href="javascript:void(0)" title="Tải xuống với định dạng xlsx"
                                                              onclick="downloadTemplate(2);">XLSX</a></label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="button" id="btnImport" value="Import" class="btn btn-warning" />
                <input type="button" id="btnClose" data-dismiss="modal" class="btn btn-primary" value="Đóng" />
            </div>
        </div>

    </div>
</div>
<style>
    .well h4,
    .well p {
        padding: -2px -1 !important;
        margin: 0 !important;
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
        text-align: center;
        min-width: 35px;
        padding: 0px;
    }

    .pagination-table td.load {
        color: #3276b1;
    }

    .pagination-table td.active {
        padding: 5px 8px;
    }

    .pagination-table td.active,
    .pagination-table td:hover {
        color: #fff;
        background-color: #3276b1;
        border-color: #3276b1;
    }

    .pagination-table td:hover a {
        color: #fff;
    }
</style>