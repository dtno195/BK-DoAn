

<%@page import="com.ptk.elearning.common.CommonConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Quản lý bài viết</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách bài viết</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm">
                            <button id="btn-insert" type="button" class="btn btn-primary" >Thêm mới</button>
                        </div>
                    </div>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 70%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="title_Search" class="control-label col-md-2">Tiêu đề:</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control" id="title_Search" placeholder="Tiêu đề"  type="text" />
                                </div>
                                <label for="fullname_Search" class="control-label col-md-2">Tác giả:</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control" id="fullname_Search" placeholder="Tên tác giả"  type="text" />
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label for="fromDate" class="control-label col-md-2">Từ ngày:</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control datepicker" id="fromDate" placeholder="Từ ngày"  type="text" />
                                </div>
                                <label for="toDate" class="control-label col-md-2">Đến ngày:</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control datepicker" id="toDate" placeholder="Đến ngày"  type="text" />
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label for="topicId_search" class="control-label col-md-2">Chủ đề:</label>
                                <div class="controls col-xs-4">
                                    <div id="topicId_search"></div>
                                </div>
                                <label for="status_search" class="control-label col-md-2">Trạng thái:</label>
                                <div class="controls col-xs-4">
                                    <select id="status_search" class="custom-select" style="width: 100%">
                                        <option value="-1">Tất cả</option>
                                        <option value="<%=CommonConstant.NEWS_PENDING%>"><%=CommonConstant.NEWS_PENDING_TEXT%></option>
                                        <option value="<%=CommonConstant.NEWS_APPROVED%>"><%=CommonConstant.NEWS_APPROVED_TEXT%></option>
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
                    <table id="gridNews" style="width: 100%;"></table>
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
                    <h4 class="modal-title">Thêm mới bài viết</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="tblNewsForm">
                        <input type="hidden" name="newId" id="newId">
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">Tiêu đề:<span class="required">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" name="title" id="title" class="form-control" placeholder="Tiêu đề" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">Nội dung:<span class="required">*</span></label>
                            <div class="col-sm-10">
                                <textarea id="contentEditor" name="contentEditor">
                                </textarea>
                                <input type="hidden" name="content" id="content">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="topicId" class="col-sm-2 control-label">Chủ đề:<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <div id="topicId"></div>
                                <span id="topicIdCombobox_error" class="note note-error"></span>
                            </div>
                            <label for="topicId" class="col-sm-2 control-label">Trạng thái:</label>
                            <div class="col-sm-4">
                                <select id="status" class="custom-select" style="width: 100%">
                                    <option value="<%=CommonConstant.NEWS_PENDING%>"><%=CommonConstant.NEWS_PENDING_TEXT%></option>
                                <option value="<%=CommonConstant.NEWS_APPROVED%>"><%=CommonConstant.NEWS_APPROVED_TEXT%></option>
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
<!-- /.modal-dialog -->
<!-- /.modal -->
<script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/share/corejs/tbl_news.js" type="text/javascript"></script>
<style>
    #tblDivision th,#tblDivision td:last-child{
        text-align: center;
    }
</style>