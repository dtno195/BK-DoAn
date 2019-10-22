<%-- 
    Document   : Index
    Created on : Sep 12, 2018, 11:22:59 PM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Quản lý danh mục dùng chung</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách danh mục dùng chung</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm">
                            <button id="btn-insert" type="button" class="btn btn-primary" >Thêm mới</button>
                        </div>
                    </div>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 70%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="dvsGroup_Search" class="control-label col-md-2">Mã danh mục</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control" id="dvsGroup_Search" placeholder="Mã danh mục"  type="text" />
                                </div>
                                <label for="dvsGroupName_Search" class="control-label col-md-2">Tên danh mục</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control" id="dvsGroupName_Search" placeholder="Tên danh mục"  type="text" />
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
                    <table id="gridStaff1" style="width: 100%;"></table>
                    <jsp:include page="../layout/tablePaging.jsp"></jsp:include>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
    <div class="modal fade" id="modal_default" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Thêm mới danh mục dùng chung</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="tblDivisionForm">
                        <!--                        <div class="form-group">
                                                    <label for="dvsParent" class="col-sm-3 control-label">Danh mục cha:</label>
                                                    <div class="col-sm-9">
                                                        <div id="dvsParent"></div>
                                                        <span id="dvsParentCombobox_error" class="note note-error"></span>
                                                    </div>
                                                </div>-->
                        <div class="form-group">
                            <label for="dvsGroup" class="col-sm-3 control-label">Mã nhóm:<span class="required">*</span></label>
                            <div class="col-sm-9">
                                <input type="text" name="dvsGroup" id="dvsGroup" class="form-control" placeholder="Mã nhóm" />
                                <label for="dvsGroup" id="dvsGroup_Error1" class="required" style="display: none"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-3 control-label">Tên nhóm:<span class="required">*</span></label>
                            <div class="col-sm-9">
                                <input type="text" name="description" id="description" class="form-control" placeholder="Tên nhóm" />
                            </div>
                        </div>
                        <div class="form-group">
                            <table id="tblDivision" class="table table-striped table-bordered table-hover smart-form dataTable no-footer">
                                <thead>
                                    <tr>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled">Tên danh mục</th>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled">Giá trị</th>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled">Thứ thự</th>
                                        <th class="thtableresponsive tlb_class_center sorting_disabled" style="width: 15%"></th>
                                    </tr>
                                </thead>
                                <tbody id="Division-child" >
                                    <tr id="dvs-child-1" class="odd">
                                        <td>
                                            <input class="form-control dvsName" name="dvsName" id="dvsName_1" />
                                            <span id="dvsName_1_error" class="note note-error error"></span>
                                        </td>
                                        <td>
                                            <input class="form-control dvsValue" name="dvsValue" id="dvsValue_1"/>
                                            <span id="dvsValue_1_error" class="note note-error error"></span>
                                        </td>
                                        <td>
                                            <input class="form-control dvsOrder" name="dvsOrder" id="dvsOrder_1"/>
                                            <span id="dvsOrder_1_error" class="note note-error"></span>
                                        </td>
                                        <td style="vertical-align: middle">
                                            <a onclick="addInstance();" title="Thêm mới danh mục" style="cursor:pointer;"><span class="fa fa-plus center bigger-110 blue fa-lg"></span></a>
                                            | <a class="remove-child-division" title="Xóa danh mục" style="cursor:pointer;"><span class="fa fa-trash-o  fa-lg"></span></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
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
    <script src="${pageContext.request.contextPath}/share/corejs/tbldivision.js" type="text/javascript"></script>
<style>
    #tblDivision th,#tblDivision td:last-child{
        text-align: center;
    }
</style>