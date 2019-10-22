function doSearch() {
    pagenum = 1;
    var keyWord = $("#topicName_search").val().trim();
    var subjectId = $("#subjectId_search").val();
    ele_datagrid.loadPageAgainRes("#gridTblTopic", "getAllTopic.json?keyWord=" + keyWord + "&subjectId=" + subjectId);
}
var isInsert = 1;
var datafields = [
    {name: 'id', type: 'number'},
    {name: 'name', type: 'text'},
    {name: 'subjectName', type: 'text'}
];
var columns = [
    {text: "Tên chủ đề", datafield: 'name', width: "35%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Môn học", datafield: 'subjectName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'id', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
];
var gridSetting = {
    sortable: false,
    virtualmode: true,
    enableToolbar: true,
    enableDelete: true,
    enableSearch: true,
    enableCheckbox: true
};
$(".sidebar-menu li").removeClass("active");
$("#TblTopic").addClass("active");
ele_combobox.buildCombobox("subjectCombobox", "/TblExam/getSubject.json", 0, "dvsName", "dvsValue", "Lựa chọn");
editCellRenderer = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật tin tức" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/edit.png" class="grid-icon"/>'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa tin tức" class="tooltipCus" href="javascript:void(0)" onclick="onClickBtDelete(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};
doSearch();
$("#btn-insert").click(function () {
    isInsert = 1;
    $("#tblTopicForm .form-control").val("");
    $("topicId").val("0");
    $("#modal_default").modal("show");
    $("#subjectId").val($("#subjectId option:first").val()).trigger('change');
    $("#modal_default .modal-title").html("Thêm mới chủ đề");
});
$(".custom-select").select2({width: "100%"});
$(".tooltipCus").tooltip();
var isInsert = 1;
$("#btnSave").click(function () {
    if (validate1()) {
        $.ajax({
            type: "POST",
            data: $("#tblTopicForm").serialize(),
            url: 'saveTopic.html',
            async: false,
            success: function (data) {
                if (data != null && !data.hasError) {
                    $("#modal_default").modal("hide");
                    doSearch();
                    if (isInsert == 1) {
                        showNotif("Thêm mới chủ đề thành công", false);
                    } else {
                        showNotif("Cập nhật chủ đề thành công", false);
                    }
                } else if (data != null && data.errorResult != null) {
                    notif({
                        type: 'success',
                        position: 'bottom',
                        msg: data.errorResult.error
                    });
                } else {
                    showNotif(isInsert == 1 ? "Đã có lỗi khi thêm mới chủ đề" : "Đã có lỗi khi cập nhật chủ đề", true);
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

function onClickBtEdit(id) {
    isInsert = 0;
    $("#modal_default").modal("show");
    $("#subjectIdCombobox").val($("#subjectIdCombobox option:first").val()).trigger('change');
    $("#modal_default .modal-title").html("Cập nhật thông tin chủ đề");
    $.ajax({
        async: false,
        type: 'POST',
        data: {id: id},
        url: "findById.json",
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy chủ đề cần cập nhật"
                });
            } else {
                $("#id").val(obj.id);
                $("#subjectId").val(obj.subjectId).trigger('change');
                $("#name").val(obj.name);
                $("#modal_default").modal("show");
            }
        }
    });
}
function onClickBtDelete(id) {
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa chủ đề này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    doSearch();
                    $.ajax({
                        type: 'POST',
                        data: {id: id},
                        url: "deleteTopic.json",
                        success: function (obj) {
                            if (obj.hasError || obj == "") {
                                showNotif(obj.error == "" ? "Không thể xóa câu hỏi này" : obj.error, true);
                            } else {
                                showNotif("Xóa câu hỏi thành công", false);
                            }
                            doSearch();
                        }, error: function (err) {
                            console.log(err);
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
$.validator.addMethod("checkSelect2", function (value, element) {
    return parseInt(value.trim()) > 0;
}, "No space please and don't leave it empty");
var validator;
function validate1() {
    validator = $("#tblTopicForm").validate({
        ignore: [],
        rules: {
            name: {
                required: true
            },
            subjectId: {
                checkSelect2: true
            }
        },
        messages: {
            name: {
                required: "Bạn chưa nhập tên chủ đề"
            },
            subjectId: {
                checkSelect2: "Bạn chưa chọn môn học"
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
        },
        errorPlacement: function (error, element) {
            if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if (controls.find(':checkbox,:radio').length > 1)
                    controls.append(error);
                else
                    error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            } else if (element.is('.select2-hidden-accessible')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if (element.attr("id") == "contentEditor") {
                error.insertAfter(element.siblings('[class*="cke_editor_contentEditor"]:eq(0)'));
            } else
                error.insertAfter(element);
        }
    });
    var valid = validator.form();
    return valid;
}
