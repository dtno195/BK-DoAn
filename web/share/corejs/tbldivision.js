editCellRenderer = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật danh mục" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/edit.png" class="grid-icon"/>'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa danh mục" class="tooltipCus" href="javascript:void(0)" onclick="deleteDivision(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};
$(".sidebar-menu li").removeClass("active");
$("#TblDivision").addClass("active");
var isInsert = 0;
var datafields = [
    {name: 'description', type: 'string'},
//    {name: 'dvsParentName', type: 'string'},
    {name: 'dvsGroup', type: 'string'}
];
var columns = [
//    {text: "Danh mục cha", datafield: 'dvsParentName', width: "40%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Mã danh mục", datafield: 'dvsGroup', width: "40%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Tên danh mục", datafield: 'description', width: "40%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'dvsGroup', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
];
var gridSetting = {
    sortable: false,
    virtualmode: true,
    enableToolbar: true,
    enableDelete: true,
    enableSearch: true,
    enableCheckbox: true
};
var cntChild = 1;
//ele_combobox.buildCombobox("dvsParent", "getAllParent.json", 0, "dvsName", "dvsId", "Lựa chọn");
//ele_datagrid.loadPageAgainRes("#gridStaff1", "Search1.json");
doSearch();
$("#btn-insert").click(function () {
    isInsert = 1;
    $("#dvsGroup").prop("disabled", false);
    $("#modal_default").find(".modal-title").html("Thêm mới danh mục dùng chung");
    $("#modal_default").modal("show");
//    $("#dvsParentCombobox").val($("#dvsParentCombobox option:first").val()).trigger('change');
    $("#dvsGroup,#description").val("");
    $("#Division-child").empty();
    cntChild = 0;
    addInstance();
});

function addInstance() {
    cntChild++;
    var html = '<tr id="dvs-child-' + cntChild + '" class="odd">';
    html += '<td><input class="form-control dvsName" name="dvsName" id="dvsName_' + cntChild + '" /><span id="dvsName_' + cntChild + '_error" class="note note-error error"></span></td>';
    html += '<td><input class="form-control dvsValue" name="dvsValue" id="dvsValue_' + cntChild + '"/><span id="dvsValue_' + cntChild + '_error" class="note note-error error"></span></td>';
    html += '<td><input class="form-control dvsOrder" name="dvsOrder" id="dvsOrder_' + cntChild + '"/><span id="dvsOrder_' + cntChild + '_error" class="note note-error"></span></td>';
    html += '<td style="vertical-align: middle"><a onclick="addInstance();" title="Thêm mới danh mục" style="cursor:pointer;"><span class="fa fa-plus center bigger-110 blue fa-lg"></span></a>|';
    html += '<a class="remove-child-division" title="Xóa danh mục" style="cursor:pointer;"><span class="fa fa-trash-o  fa-lg"></span></a><input type="hidden" value="0" class="form - control dvsId" name="dvsId" /></td>';
    $("#Division-child").append(html);
}
$(document).on("click", ".remove-child-division", function () {
    var dvsName = $(this).closest("tr").find(".dvsName").val().trim();
    var dvsValue = $(this).closest("tr").find(".dvsValue").val().trim();
    var dvsOrder = $(this).closest("tr").find(".dvsOrder").val().trim();
    var rowCount = $("#Division-child tr").length;
    var this1 = $(this);
    if (dvsName.length > 0 || dvsValue.length > 0 || dvsOrder.length > 0) {
        $.confirm({
            'title': 'Xác nhận xóa',
            'message': 'Bạn có chắc chắn muốn xóa danh mục này?',
            'buttons': {
                'Xóa': {
                    'class': 'btn-confirm-yes btn-success',
                    'action': function () {
                        $(this1).closest("tr").remove();
                        if (rowCount == 1) {
                            addInstance();
                        }
                    }
                },
                'Không xóa': {
                    'class': 'btn-info',
                    'action': function () { }
                }
            }
        });
    } else {
        $(this).closest("tr").remove();
        if (rowCount == 1) {
            addInstance();
        }
    }
});
function loadListChild(dvsGroup) {
    $.ajax({
        type: 'GET',
        data: {dvsGroup: dvsGroup},
        url: "getChildById.json",
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy danh mục dùng chung cần sửa"
                });
            } else {
                var length = obj.length;
                var html = "";
                for (var i = 0; i < length; i++) {
                    html += '<tr id="dvs-child-' + obj[i].dvsId + '" class="odd">';
                    html += '<td><input value="' + obj[i].dvsName + '" class="form-control dvsName" name="dvsName" id="dvsName_' + obj[i].dvsId + '" /><span id="dvsName_' + obj[i].dvsId + '_error" class="note note-error error"></span></td>';
                    html += '<td><input value="' + obj[i].dvsValue + '" class="form-control dvsValue" name="dvsValue" id="dvsValue_' + obj[i].dvsId + '"/><span id="dvsValue_' + obj[i].dvsId + '_error" class="note note-error error"></span></td>';
                    html += '<td><input value="' + obj[i].dvsOrder + '" class="form-control dvsOrder" name="dvsOrder" id="dvsOrder_' + obj[i].dvsId + '"/><span id="dvsOrder_' + obj[i].dvsId + '_error" class="note note-error"></span></td>';
                    html += '<td style="vertical-align: middle"><a onclick="addInstance();" title="Thêm mới danh mục" style="cursor:pointer;"><span class="fa fa-plus center bigger-110 blue fa-lg"></span></a>|';
                    html += '<a href="javascript:void(0)" class="remove-child" data-id="' + obj[i].dvsId + '"  title="Xóa danh mục" style="cursor:pointer;"><span class="fa fa-trash-o  fa-lg"></span></a>';
                    html += '<input type="hidden" value="' + obj[i].dvsId + '" class="form - control dvsId" name="dvsId" /></td>';
                }
                $("#Division-child").append(html);
            }
        }
    });
}
$(document).on("click", ".remove-child", function () {
    var rowCount = $("#Division-child tr").length;
    var dvsId = $(this).attr("data-id");
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa danh mục này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    $("#dvs-child-" + dvsId).remove();
                    if (rowCount == 1) {
                        addInstance();
                    }
                    $.ajax({
                        type: 'DELETE',
                        data: {dvsId: dvsId},
                        url: "deleteChild.json?dvsId="+dvsId,
                        success: function (obj) {
                            if (obj.hasError) {
                                showNotif(obj.error, true);
                            }
                        }
                    });
                }
            },
            'Không xóa': {
                'class': 'btn-info',
                'action': function () { }
            }
        }
    });
});
function removeChild(dvsId) {
    console.log("fuck");

}
$("#btnSave").click(function () {
    var isExisted = checkCode($("#dvsGroup").val().trim());
    if (validate1() && validateForm("#tblDivisionForm") && !isExisted) {
        var myform = $('#tblDivisionForm');
        var disabled = myform.find(':input:disabled').removeAttr('disabled');
        var serialized = myform.serialize();
        disabled.attr('disabled', 'disabled');
        $.ajax({
            type: "POST",
            data: serialized,
            url: 'addDivision.html',
            async: false,
            success: function (data) {
                if (data != null && !data.hasError) {
                    $("#modal_default").modal("hide");
                    doSearch();
                    if (isInsert == 1) {
                        showNotif("Thêm mới danh mục dùng chung thành công", false);
                    } else {
                        showNotif("Cập nhật danh mục dùng chung thành công", false);
                    }
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
                        msg: (isInsert == 1 ? 'Đã có lỗi khi thêm mới danh mục dùng chung' : 'Đã có lỗi khi cập nhật danh mục dùng chung')
                    });
                }
            }, error: function (err) {
                console.log(err);
            }
        });
    } else {
        notif({
            type: 'error',
            position: 'bottom',
            msg: 'Vui vòng nhập các thông tin bắt buộc'
        });
    }
});
$(".tooltipCus").tooltip();
function onClickBtEdit(dvsGroup) {
    $("#dvsGroup").prop("disabled", true);
    isInsert = 0;
    $("#modal_default").find(".modal-title").html("Cập nhật danh mục dùng chung");
    $("#Division-child").empty();
    $.ajax({
        async: false,
        type: 'GET',
        data: {dvsGroup: dvsGroup},
        url: "findByDvsGroup.json",
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy danh mục dùng chung cần cập nhật"
                });
            } else {
                loadListChild(dvsGroup);
                $("#dvsGroup").val(obj.dvsGroup);
                $("#description").val(obj.description);
//                $("#dvsParentCombobox").val(obj.dvsParent).trigger('change');
                $("#modal_default").modal("show");
            }
        }
    });
}
function checkCode(dvsGroup) {
    var isExisted = false;
    $.ajax({
        async: false,
        type: 'GET',
        data: {dvsGroup: dvsGroup},
        url: "findByDvsGroup.json",
        success: function (obj) {
            if ((obj != null && obj.dvsId != null) && (isInsert == 1)) {
                isExisted = true;
                if ($("#dvsGroup_Error1").length == 0) {
                    $("<label for='dvsGroup' id='dvsGroup_Error1' class='required'></label>").insertAfter("#dvsGroup");
                }
                $("#dvsGroup").closest(".form-group").addClass("has-error");
                $("#dvsGroup_Error1").html("Mã danh mục đã tồn tại");
                $("#dvsGroup_Error1").show();
            } else {
                $("#dvsGroup").closest(".form-group").removeClass("has-error");
                $("#dvsGroup_Error1").remove();
            }
        }
    });
    return isExisted;
}
function validateForm(selector) {
    var isValid = true;
    $(selector + ' [name*="dvsName"]').each(function () {
        if ($(this).val().trim().length === 0) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Bạn chưa nhập tên danh mục");
        } else if ($(this).val().trim().length > 500) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Tên danh mục không được nhập quá 500 ký tự");
        }
    });
    $(selector + ' [name*="dvsValue"]').each(function () {
        if ($(this).val().trim().length === 0) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Bạn chưa nhập giá trị");
        } else if (!/^\d+$/.test($(this).val().trim())) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Giá trị chỉ được nhập số");
        } else if (!/^[0-9]{1,15}$/.test($(this).val().trim())) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Giá trị không đúng định dạng");
        }
    });
    $(selector + ' [name*="dvsOrder"]').each(function () {
        if ($(this).val().trim().length === 0) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Bạn chưa nhập số thứ tự");
        } else if (!/^\d+$/.test($(this).val().trim())) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Số thứ thự chỉ được nhập số");
        } else if (!/^\d{1,15}$/.test($(this).val().trim())) {
            isValid = false;
            $("#" + $(this).attr('id') + "_error").text("Số thứ tự không đúng dịnh dạng");
        }
    });
    return isValid;
}
function deleteDivision(dvsGroup) {
    var rowCount = $("#Division-child tr").length;
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa danh mục này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    if (rowCount == 1) {
                        addInstance();
                    }
                    $.ajax({
                        async: false,
                        type: 'POST',
                        data: {dvsGroup: dvsGroup},
                        url: "deleteDivision.json",
                        success: function (data) {
                            console.log(data);
                            if (data != null && !data.hasError) {
                                doSearch();
//                                ele_datagrid.loadPageAgainRes("#gridStaff1", "Search1.json");
                                notif({
                                    type: 'success',
                                    position: 'bottom',
                                    msg: 'Xóa danh mục dùng chung thành công'
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
                                    msg: 'Đã có lỗi khi xóa danh mục dùng chung'
                                });
                            }
                        }
                    });
                }
            },
            'Không xóa': {
                'class': 'btn-info',
                'action': function () { }
            }
        }
    });

}
$.validator.addMethod("noSpace", function (value, element) {
    return value.trim().length > 0;
}, "No space please and don't leave it empty");
$.validator.addMethod("maxLengthCustom", function (value, element, param) {
    return value.trim().length <= param;
}, "No space please and don't leave it empty");
$.validator.addMethod("dvsGroupInvalid", function (value, element) {
    var code = /^[0-9a-zA-Z_]+$/i;
    return code.test(value.trim()) === true;
}, "Mã danh mục dùng chung chỉ gồm chữ cái, số và dấu _");
$.validator.addMethod("checkCodeExisted", function (value, element) {
    return checkCode(value.trim());
}, "Mã danh mục dùng chung chỉ gồm chữ cái, số và dấu _");
var validator;
function validate1() {
    validator = $("#tblDivisionForm").validate({
        rules: {
            dvsGroup: {
                noSpace: true,
                maxLengthCustom: 500,
                dvsGroupInvalid: true
            },
            description: {
                noSpace: true
            }
        },
        messages: {
            dvsGroup: {
                noSpace: "Bạn chưa nhập mã danh mục",
                maxLengthCustom: "Mã danh mục không được nhập quá 500 ký tự",
                dvsGroupInvalid: "Mã danh mục chỉ được nhập chữ, số và dấu _",
            },
            description: {
                noSpace: "Bạn chưa nhập tên danh mục"
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
    var valid = $("#tblDivisionForm").validate().form();
    return valid;
}
function doSearch() {
    pagenum = 1;
    var dvsGroup = $("#dvsGroup_Search").val().trim();
    var description = $("#dvsGroupName_Search").val().trim();
    ele_datagrid.loadPageAgainRes("#gridStaff1", "Search1.json?dvsGroup=" + encodeURIComponent(dvsGroup) + "&description=" + encodeURIComponent(description));
}