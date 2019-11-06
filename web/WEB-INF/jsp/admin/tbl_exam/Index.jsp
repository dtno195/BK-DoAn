


<%@page import="com.ptk.elearning.common.CommonConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Quản lý đề thi</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách đề thi</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm">
                            <button id="btn-insert" type="button" class="btn btn-primary" >Thêm mới</button>
                        </div>
                    </div>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 70%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="name" class="control-label col-md-2">Tên đề thi:</label>
                                <div class="controls col-xs-10">
                                    <input type="text" name="name" id="name_search" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label for="subjectId_searchCombobox" class="control-label col-md-2">Môn học:</label>
                                <div class="controls col-xs-4">
                                    <div id="subjectId_search"></div>
                                </div>
                                <label for="timeId_searchCombobox" class="control-label col-md-2">Thời gian:</label>
                                <div class="controls col-xs-4">
                                    <div id="timeId_search"></div>
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
                    <table id="gridExam" style="width: 100%;"></table>
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
                <div class="modal-body">
                    <form class="form-horizontal" id="tblExamForm">
                        <input type="hidden" name="examId" id="examId">
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
                                <div id="subjectId"></div>
                                <span id="subjectIdCombobox_error" class="note note-error"></span>
                            </div>
                            <label for="timeId" class="col-sm-2 control-label">Thời gian:<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <div id="timeId"></div>
                                <span id="timeIdCombobox_error" class="note note-error"></span>
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
    <!-- /.modal-dialog -->
    <!-- /.modal -->

    <div class="modal fade" id="modal_question" data-backdrop="static">
        <div class="modal-dialog modal-lg" style="width: 90%">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Danh sách câu hỏi</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="tblExamQuestionForm">
                        <input type="hidden" name="examId" id="examQuesId">
                        <div class="form-group">
                            <div class="col-sm-12 no-padding">
                                <div class="col-sm-12"  id="examNameText">Đề thi:</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6 no-padding">
                                <label for="name" class="col-sm-2 control-label">Câu hỏi:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="name" id="content_question" class="form-control" placeholder="Tên đề thi" />
                                </div>
                                <label for="name" class="col-sm-2 control-label">Mức độ:</label>
                                <div class="col-sm-4">
                                    <div id="levelId"></div>
                                </div>
                            </div>
                            <div class="col-sm-6 no-padding">
                                <label for="name" class="col-sm-2 control-label">Câu hỏi:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="name" id="content_selected" class="form-control" placeholder="Tên đề thi" />
                                </div>
                                <label for="name" class="col-sm-2 control-label">Mức độ:</label>
                                <div class="col-sm-4">
                                    <div id="levelSelectedId"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6 no-padding" style="text-align: center">
                                <button class="btn btn-success" type="button" id="btnSearchQuestion">Tìm kiếm</button>
                            </div>
                            <div class="col-sm-6 no-padding" style="text-align: center">
                                <button class="btn btn-success" type="button" id="btnSearchSelected">Tìm kiếm</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div style="width: 47% !important" class="col-sm-5 no-padding">
                                <table id="gridQuestion" style="width: 100%;"></table>
                            <jsp:include page="../layout/tablePaging1.jsp"></jsp:include>
                            </div>
                            <div id="draggable-plugins" style="width: 6% !important;text-align: center;height: 200px" class="col-sm-2 no-padding" >
                                <button id="add-plugin" title="Thêm câu hỏi đã chọn" class="add-item" type="button" style="opacity: 0.7;">
                                    <span class="mo-add-ico">add plugin</span>
                                    <span class="add-ico">add plugin</span>
                                </button>
                                <button id="remove-plugin" title="Xóa câu hỏi đã chọn" class="remove-item active" type="button" style="opacity: 1;margin-top: 50%">
                                    <span class="mo-remove-ico">remove plugin</span>
                                    <span class="remove-ico">remove plugin</span>
                                </button>
                            </div>
                            <div style="width: 47% !important" class="col-sm-5 no-padding">
                                <table id="gridSelected" style="width: 100%;"></table>
                            <jsp:include page="../layout/tablePaging2.jsp"></jsp:include>
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



    <div class="modal fade" id="modal_export" data-backdrop="static">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Kết xuất word</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="tblExportForm">
                        <input type="hidden" name="examId" id="examExportId">
                        <div class="form-group">
                            <label for="changeQuestion" class="col-sm-2 control-label">Tráo câu hỏi:<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <select class="custom-select" id="changeQuestion" name="changeQuestion">
                                    <option value="<%=CommonConstant.ACCEPT_CHANGE%>">Có</option>
                                <option value="<%=CommonConstant.UNACCEPT_CHANGE%>">Không</option>
                            </select>
                        </div>
                        <label for="changeAnswer" class="col-sm-2 control-label">Tráo câu trả lời:<span class="required">*</span></label>
                        <div class="col-sm-4">
                            <select class="custom-select" id="changeAnswer" name="changeAnswer">
                                <option value="<%=CommonConstant.ACCEPT_CHANGE%>">Có</option>
                                <option value="<%=CommonConstant.UNACCEPT_CHANGE%>">Không</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="examNumber" class="col-sm-2 control-label">Số đề thi:</label>
                        <div class="col-sm-4">
                            <input type="text" name="examNumber" id="examNumber" class="form-control">
                        </div>
                        <label for="contentExport" class="col-sm-2 control-label">Ghi chú:</label>
                        <div class="col-sm-4">
                            <input type="text" name="contentExport" id="contentExport" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnExport">Kết xuất</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/share/corejs/tbl_exam.js" type="text/javascript"></script>
<style>
    #tblDivision th,#tblDivision td:last-child{
        text-align: center;
    }
</style>
