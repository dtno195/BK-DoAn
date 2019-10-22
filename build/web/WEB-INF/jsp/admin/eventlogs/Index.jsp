<%-- 
    Document   : Index
    Created on : Sep 12, 2018, 11:22:59 PM
    Author     : lienptk
--%>
<%@page import="com.ptk.elearning.common.CommonConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Tra cứu lịch sử</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách lịch sử người dùng</h3>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 70%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="dvsGroup_Search" class="control-label col-md-2">Từ ngày</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control datepicker" id="fromDate" placeholder="Từ ngày" type="text" />
                                </div>
                                <label for="dvsGroupName_Search" class="control-label col-md-2">Đến ngày</label>
                                <div class="controls col-xs-4">
                                    <input class="input-md form-control datepicker" id="toDate" placeholder="Đến ngày" type="text" />
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label for="dvsGroup_Search" class="control-label col-md-2">Thao tác</label>
                                <div class="controls col-xs-4">
                                    <select class="custom-select" id="event_action">
                                        <option value="0">Tất cả</option>
                                        <option value="<%=CommonConstant.ACTION_ADD%>">Thêm mới</option>
                                        <option value="<%=CommonConstant.ACTION_UPDATE%>">Cập nhật</option>
                                        <option value="<%=CommonConstant.ACTION_DELETE%>">Xóa</option>
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
                    <table id="gridEventLogs" style="width: 100%;"></table>
                    <jsp:include page="../layout/tablePaging.jsp"></jsp:include>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
    <script src="${pageContext.request.contextPath}/share/corejs/eventlogs.js" type="text/javascript"></script>
