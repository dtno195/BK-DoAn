<%-- 
    Document   : Dashboard
    Created on : Sep 11, 2018, 2:01:19 PM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Quản lý người dùng</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <div class="box-header">
                    <h3 class="box-title">Danh sách người dùng</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm">
                            <button id="btn-insert" type="button" class="btn btn-primary" >Thêm mới</button>
                        </div>

                    </div>
                    <div class="bd-example" style="height:100% !important;padding: 11px 0px 11px 0px;width: 80%;float: none !important;margin: 0 auto">
                        <div class="bd-example">
                            <div class="form-group col-xs-12">
                                <label for="username_search" class="control-label col-md-2">Tên đăng nhập:</label>
                                <div class="controls col-xs-4">
                                    <input type="text" name="" id="username_search" class="form-control">
                                </div>
                                <label for="fullname_search" class="control-label col-md-2">Họ và tên:</label>
                                <div class="controls col-xs-4">
                                    <input type="text" name="fullname_search" id="fullname_search" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label for="subjectId_searchCombobox" class="control-label col-md-2">Vai trò:</label>
                                <div class="controls col-xs-4">
                                    <select name="role" id="role_search" style="width: 100%" class="custom-select">
                                        <option value="-1">Lựa chọn</option>
                                        <c:forEach var="item" items="${role}">
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
                    <table id="gridStaff" style="width: 100%;"></table>
                    <jsp:include page="../layout/tablePaging.jsp"></jsp:include>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
    <div class="modal fade" id="modal-default" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Thêm mới người dùng</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="tblUsersForm">
                        <input type="hidden" name="userId" id="userId">
                        <div class="form-group">
                            <label for="userName" class="col-sm-3 control-label">Tên đăng nhập</label>
                            <div class="col-sm-9">
                                <input type="text" autocomplete="off" class="form-control" name="userName" id="userName" placeholder="Tên đăng nhập">
                            </div>
                        </div>
                        <div class="form-group password">
                            <label for="password" class="col-sm-3 control-label">Mật khẩu</label>
                            <div class="col-sm-9">
                                <input type="password" autocomplete="off" class="form-control" name="password" id="password" placeholder="Mật khẩu">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fullName" class="col-sm-3 control-label">Họ và tên</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Họ và tên">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="highSchool" class="col-sm-3 control-label">Trường học</label>
                            <div class="col-sm-9">
                                <input type="text" name="highSchool" class="form-control" id="highSchool" placeholder="Trường học" /> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="aspiration" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-9">
                                <input type="text" name="email" class="form-control" id="email" placeholder="Email" /> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="aspiration" class="col-sm-3 control-label">Nguyện vọng</label>
                            <div class="col-sm-9">
                                <input type="text" name="aspiration" class="form-control" id="aspiration" placeholder="Nguyện vọng" /> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dateOfBirth" class="col-sm-3 control-label">Ngày sinh</label>
                            <div class="col-sm-9">
                                <input type="text" name="dateOfBirth" class="form-control" id="dateOfBirth" placeholder="Ngày sinh" /> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="role" class="col-sm-3 control-label">Vai trò</label>
                            <div class="col-sm-3">
                                <select name="role" id="role" style="width: 100%" class="custom-select">
                                    <option value="-1">Lựa chọn</option>
                                <c:forEach var="item" items="${role}">
                                    <option value="${item.dvsValue}">${item.dvsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="role" class="col-sm-3 control-label">Trạng thái</label>
                        <div class="col-sm-3">
                            <select name="isLock" id="isLock" style="width: 100%" class="custom-select">
                                <option value="true">Mở khóa</option>
                                <option value="false">Khóa</option>
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
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
                    
<!-- /.modal -->
<script>
    $(".sidebar-menu li").removeClass("active");
    $("#TblUser").addClass("active");
    $("#btn-insert").click(function () {
        $(".password").show();
        $("#userId").val("0");
        $("#tblUsersForm .form-control").val("");
        $("#role").val($("#role option:first").val()).trigger('change');
        $("#isLock").val($("#isLock option:first").val()).trigger('change');
        $("#modal-default").modal("show");
    });
    $(".custom-select").select2();
    editCellRenderer = function (gid) {
        return '<div style="text-align: center" id="staffOption_' + gid + '">'
                + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật người dùng" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
                + '        <img src="../share/image/edit.png" class="grid-icon"/>'
                + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa người dùng" class="tooltipCus" href="javascript:void(0)" onclick="deleteUser(\'' + gid + '\')">'
                + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
                + '</div>';
    };
    function deleteUser(userId) {
        $.confirm({
            'title': 'Xác nhận xóa',
            'message': 'Bạn có chắc chắn muốn xóa người dùng này?',
            'buttons': {
                'Xóa': {
                    'class': 'btn-confirm-yes btn-success',
                    'action': function () {
                        $.ajax({
                            url: 'removeUser.html',
                            type: 'post',
                            cache: false,
                            data: {userId: userId},
                            success: function (data) {
                                if (data != null && !data.hasError) {
                                    ele_datagrid.loadPageAgainRes("#gridStaff", "Search1.json");
                                    notif({
                                        type: 'success',
                                        position: 'bottom',
                                        msg: 'Xóa người dùng thành công'
                                    });

                                } else if (data != null && data.errorResult != null) {
                                    notif({
                                        type: 'success',
                                        position: 'bottom',
                                        msg: data.errorResult.error
                                    });
                                } else {
                                    notif({
                                        type: 'success',
                                        position: 'bottom',
                                        msg: 'Đã có lỗi khi xóa người dùng'
                                    });
                                }
                            },
                            error: function (err) {
                                CommonJS.alert(err.responseText);
                            }
                        });
                    }
                },
                'Không xóa': {
                    'class': 'btn-info',
                    'action': function () { } // Nothing to do in this case. You can as well omit the action property.
                }
            }
        });
    }

    $("#btnSave").click(function () {
        if (validate1()) {
            $.ajax({
                type: "POST",
                data: $("#tblUsersForm").serialize(),
                url: 'addUser.html',
                async: false,
                success: function (data) {
                    if (data != null && !data.hasError) {
                        $("#modal-default").modal("hide");
                        ele_datagrid.loadPageAgainRes("#gridStaff", "Search1.json");
                        notif({
                            type: 'success',
                            position: 'bottom',
                            msg: 'Thêm mới người dùng thành công'
                        });
                    } else if (data != null && data.errorResult != null) {
                        notif({
                            type: 'success',
                            position: 'bottom',
                            msg: data.errorResult.error
                        });
                    } else {
                        notif({
                            type: 'success',
                            position: 'bottom',
                            msg: 'Đã có lỗi khi thêm mới người dùng',
                        });
                    }
                }, error: function (err) {
                    console.log(err);
                }
            });
        }
    });
    var validator;
    function validate1() {
        validator = $("#tblUsersForm").validate({
            rules: {
                userName: {
                    required: true,
                    userNameValid: true
                },
                password: {
                    required: true,
                    minlength: 8
                },
                email: {
                    required: true,
                    email: true
                },
                fullName: {
                    required: true
                },
                highSchool: {
                    required: true
                },
                aspiration: {
                    required: true
                },
                dateOfBirth: {
                    required: true,
                    dateValidate: true,
                    dateFormatValid: true,
                    birthdayValid: true
                }
            },
            messages: {
                userName: {
                    required: "Bạn chưa nhập tên đăng nhập",
                    userNameValid: "Tên đăng nhập chỉ được nhập chữ, số và dấu _"
                },
                password: {
                    required: "Bạn chưa nhập mật khẩu",
                    minlength: "Mật khẩu phải có độ dài lớn hơn 8 ký tự"
                },
                fullName: {
                    required: "Bạn chưa nhập họ và tên"
                },
                highSchool: {
                    required: "Bạn chưa nhập trường học"
                },
                aspiration: {
                    required: "Bạn chưa nhập nguyện vọng"
                },
                dateOfBirth: {
                    required: "Bạn chưa chọn ngày sinh"
                }
            },
            highlight: function (element) {
                jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
                jQuery(element).addClass('element-error');
            },
            success: function (element) {
                jQuery(element).closest('.form-group').removeClass('has-error');
                jQuery(element).removeClass('element-error');
                jQuery(element).remove();
            }
        });
        var valid = $("#tblUsersForm").validate().form();
        return valid;
    }
    var datafields = [
        {name: 'userId', type: 'number'},
        {name: 'userName', type: 'string'},
        {name: 'fullName', type: 'string'},
        {name: 'password', type: 'string'},
        {name: 'salt', type: 'string'},
        {name: 'highSchool', type: 'string'},
        {name: 'roleName', type: 'string'},
        {name: 'role', type: 'number'},
        {name: 'isLock', type: 'string'},
        {name: 'isLogin', type: 'number'}
    ];
    var columns = [
        {text: "Tên đăng nhập", datafield: 'userName'},
        {text: "Họ và tên", datafield: 'fullName'},
        {text: "Trường học", datafield: 'highSchool', width: "12%", filtertype: 'input', res: "data-hide='phone'"},
        {text: "Vai trò", datafield: 'roleName', width: "12%", filtertype: 'input', res: "data-hide='phone'"},
        {text: "Trạng thái", datafield: 'isLock', width: "12%", clstitle: 'tlb_class_center', filtertype: 'input', res: "data-hide='phone'"},
        {text: "userId", datafield: 'userId', sortable: false, clstitle: 'tlb_class_center', hidden: true},
        {text: "", datafield: 'userId', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
    ];
    var gridSetting = {
        sortable: false,
        virtualmode: true,
        enableToolbar: true,
        enableDelete: true,
        enableSearch: true,
        enableCheckbox: true
    };
    ele_datagrid.loadPageAgainRes("#gridStaff", "Search1.json");
    $('#dateOfBirth').datepicker({
        autoclose: true,
        duration: "fast",
        changeMonth: true,
        changeYear: true,
        format: 'dd/mm/yyyy',
        constrainInput: true,
        maxDate: 0,
        yearRange: "-70:+0"
    });
    $.validator.addMethod("userNameValid", function (value, element) {
        var code = /^[0-9a-zA-Z_]+$/i;
        return code.test(value.trim()) === true;
    }, "Tên đăng nhập chỉ được chứa chữ, số, dấu - và đấu _");
    $.validator.addMethod("dateValidate", function (value, element) {
        return dateValidate(value);
    }, "Ngày sinh không tồn tại");
    $.validator.addMethod("dateFormatValid", function (value, element) {
        return dateFormatValid(value);
    }, "Vui lòng nhập ngày sinh đúng định dạng");
    $.validator.addMethod("birthdayValid", function (value, element) {
        var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime();
        var selected = new Date($('#dateOfBirth').datepicker('getDate')).getTime();
        return today > selected;
    }, "Ngày sinh không được sau ngày hiện tại");

    function onClickBtEdit(userId) {
        ele_form.clearError();
        $.ajax({
            async: false,
            type: 'GET',
            data: {userId: userId},
            url: "findById.json",
            success: function (obj) {
                if (obj == null) {
                    notif({
                        type: 'error',
                        position: 'bottom',
                        msg: "Không tìm thấy người dùng cần sửa"
                    });
                } else {
                    $(".password").hide();
                    $("#userName").val(obj.userName);
                    $("#fullName").val(obj.fullName);
                    $("#highSchool").val(obj.highSchool);
                    $("#aspiration").val(obj.aspiration);
                    $("#email").val(obj.email);
                    if (obj.dateOfBirth != null) {
                        $("#dateOfBirth").val(formatDate(new Date(obj.dateOfBirth)));
                    }
                    $("#userId").val(obj.userId);
                    $("#role").val(obj.role).trigger('change');
                    $("#isLock").val(obj.isLock).trigger('change');
                    $("#modal-default").modal("show");
                }
            }
        });
    }
    $(".tooltipCus").tooltip();
    function doSearch() {
        var username = $("#username_search").val();
        var fullname = $("#fullname_search").val();
        var role = $("#role_search").val();
        ele_datagrid.loadPageAgainRes("#gridStaff", "Search1.json?role=" + role + "&username=" + username + "&fullname=" + fullname);
    }
</script>