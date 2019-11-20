

<%@page import="com.ptk.elearning.common.CommonConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" async src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=MML_CHTML"></script>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Ngân hàng câu hỏi</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách câu hỏi</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm">
                            <button id="btn-insert" type="button" class="btn btn-primary" >Thêm mới</button>
                            <button id="delete-all" type="button" class="btn btn-danger" >Xóa</button>
                            <button id="btn-import" type="button" class="btn btn-success" >Import</button>
                        </div>
                    </div>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 70%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="content_search" class="control-label col-md-2">Câu hỏi:</label>
                                <div class="controls col-xs-10">
                                    <input type="text" name="name" id="content_search" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label for="subjectId_search" class="control-label col-md-2">Môn học:</label>
                                <div class="controls col-xs-4">
                                    <select class="custom-select" id="subjectId_search">
                                        <option value="-1">Lựa chọn</option>
                                        <c:forEach var="item" items="${subject}">
                                            <option value="${item.dvsValue}">${item.dvsName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label for="" class="control-label col-md-2">Cấp độ:</label>
                                <div class="controls col-xs-4">
                                    <select class="custom-select" id="levelId_search">
                                        <option value="-1">Lựa chọn</option>
                                        <c:forEach var="item" items="${level}">
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
                    <table id="gridQuestion" style="width: 100%;"></table>
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
                    <h4 class="modal-title">Thêm mới đề thi</h4>
                </div>
                <div class="modal-body" style="overflow: auto;max-height: calc(100vh - 150px);">
                    <form class="form-horizontal" id="tblQuestionForm">
                        <input type="hidden" name="questionId" id="questionId">
                        <div class="form-group">
                            <label for="contentEditor" class="col-sm-2 control-label">Câu hỏi:<span class="required">*</span></label>
                            <div class="col-sm-10">
                                <textarea id="contentEditor" name="contentEditor"></textarea>
                                <input type="hidden" name="content" id="content">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subjectId" class="col-sm-2 control-label">Môn học:<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <select class="custom-select" id="subjectId" name="subjectId">
                                    <option value="-1">Lựa chọn</option>
                                <c:forEach var="item" items="${subject}">
                                    <option value="${item.dvsValue}">${item.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="timeId" class="col-sm-2 control-label">Độ khó:<span class="required">*</span></label>
                        <div class="col-sm-4">
                            <select class="custom-select" id="levelId" name="levelId">
                                <option value="-1">Lựa chọn</option>
                                <c:forEach var="item" items="${level}">
                                    <option value="${item.dvsValue}">${item.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <table id="tblAnswers" class="table table-striped table-bordered table-hover smart-form dataTable no-footer">
                                <thead>
                                    <tr>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled">Câu trả lời</th>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled" style="width: 15%">Đáp án đúng?</th>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled" style="width: 10%">
                                            <a onclick="addInstance();" title="Thêm mới câu trả lời" style="cursor:pointer;"><span class="fa fa-plus center bigger-110 blue fa-lg"></span></a>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody id="Answer_child"></tbody>
                            </table>
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

<div class="modal fade" id="importRegister" role="dialog" tabindex="-1"  data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Import</h4>
            </div>
            <div class="modal-body">
                <form id="frm_import" class="form-horizontal form-margin">
                    <div class="form-group custom-margin" style="padding-left: 10px !important">
                        <label class="btn btn-success">
                            Lựa chọn file import
                            <input accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"  type="file" id="fileImport" accept="" name="files" value="files" style="display: none;">
                        </label>
                    </div>
                    <div class="form-group custom-margin" style="padding-left: 10px !important">
                        <span id="fileUploadName"></span><br />
                        <span style="color: red" id="fileUploadNameError"></span>
                    </div>
                    <div class="form-group custom-margin">
                        <label for="subjectId_search" class="control-label col-md-2">Môn học:</label>
                        <div class="controls col-xs-4">
                            <select class="custom-select" id="subjectId_import">
                                <option value="-1">Lựa chọn</option>
                                <c:forEach var="item" items="${subject}">
                                    <option value="${item.dvsValue}">${item.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group custom-margin" style="padding-left: 10px !important">
                        <span style="color: red" id="subjectImportErr"></span>
                    </div>
                    <div class="form-group custom-margin">
                        <label class="col-md-12">Định dạng file cho phép: .XLS, .XLSX</label>
                    </div>
                    <div class="form-group custom-margin">
                        <label class="col-md-12">Dung lượng tối đa: 20Mb</label>
                    </div>
                    <div class="form-group custom-margin">
                        <label class="col-md-12">File mẫu: <a href="javascript:void(0)" title="Tải xuống với định dạng .doc"  onclick="downloadTemplate(1);">XLS</a> | <a href="javascript:void(0)" title="Tải xuống với định dạng .docx" onclick="downloadTemplate(2);">XLSX</a></label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="button" id="btnImport" value="Import" class="btn btn-warning" />
                <input type="button" id="btnClose" data-dismiss="modal"
                       class="btn btn-primary"
                       value="Đóng" />
            </div>
        </div>

    </div>
</div>
<div class="modal fade" id="dialog-Error" role="dialog" tabindex="-1"  data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Import</h4>
            </div>
            <div class="modal-body">
                <div class="dataTables_info" role="status" aria-live="polite" style="color: green">Số bản ghi thành công<label for="success"></label></div>
                <div class="dataTables_info" role="status" aria-live="polite" style="color: red">Số bản ghi thất bại<label for="error"></label></div>
                <div class="dataTables_info" role="status" aria-live="polite">Tổng số bản ghi<label for="totalRecord"></label></div>
                <div id="tabs-Rescue">
                    <div class="table-responsive" id="table-responsive2">
                        <table id="listDataImportError" class="table table-striped table-bordered table-hover smart-form has-tickbox" width="100%">
                            <thead>
                                <tr>
                                    <th class="thtableresponsive undefined" data-class="expand">STT</th>
                                    <th class="thtableresponsive undefined" data-class="expand">Dòng</th>
                                    <th class="thtableresponsive undefined" data-hide="phone">Lỗi</th>
                                </tr>
                            </thead>
                            <tbody id="listDataImportErrorBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" id="btnClose" data-dismiss="modal"
                       class="btn btn-primary"
                       value="Đóng" />
            </div>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/share/corejs/tbl_questions.js" type="text/javascript"></script>

