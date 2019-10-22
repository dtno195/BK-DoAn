<%-- 
    Document   : Dashboard
    Created on : Sep 11, 2018, 2:01:19 PM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="content-header">
    <h1>
        <!--        Dashboard
                <small>Version 2.0</small>-->
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Quản lý chủ đề</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách chủ đề</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm">
                            <button id="btn-insert" type="button" class="btn btn-primary" >Thêm mới</button>
                        </div>

                    </div>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 80%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="username_search" class="control-label col-md-2">Tên chủ đề:</label>
                                <div class="controls col-xs-4">
                                    <input type="text" name="" id="topicName_search" class="form-control">
                                </div>
                                <label for="fullname_search" class="control-label col-md-2">Môn học:</label>
                                <div class="controls col-xs-4">
                                    <select class="custom-select" id="subjectId_search" style="width: 100%">
                                        <option value="-1">Lựa chọn</option>
                                        <c:forEach var="item" items="${subject}">
                                            <option value="${item.dvsValue}">${item.dvsName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-xs-12" style="text-align: center">
                                <button onclick="doSearch();" class="btn btn-success" type="button">Tìm kiếm</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table id="gridTblTopic" style="width: 100%;"></table>
                    <jsp:include page="../layout/tablePaging.jsp"></jsp:include>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
    <div class="modal fade" id="modal_default" data-backdrop="static">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Thêm mới chủ đề</h4>
                </div>
                <div class="modal-body" style="overflow: auto;max-height: calc(100vh - 150px);">
                    <form class="form-horizontal" id="tblTopicForm">
                        <input type="hidden" name="id" id="topicId">
                        <div class="form-group">
                            <label for="timeId" class="col-sm-2 control-label">Tên chủ đề:<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <input type="text" name="name" id="name" class="form-control" placeholder="Tên chủ đề" />
                            </div>
                            <label for="subjectId" class="col-sm-2 control-label">Môn học:<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <select class="custom-select" id="subjectId" name="subjectId">
                                    <option value="-1">Lựa chọn</option>
                                <c:forEach var="item" items="${subject}">
                                    <option value="${item.dvsValue}">${item.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnSave">Lưu lại</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<script src="${pageContext.request.contextPath}/share/corejs/tbltopic.js" type="text/javascript"></script>               