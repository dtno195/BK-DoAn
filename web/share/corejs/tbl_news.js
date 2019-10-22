function doSearch() {
    pagenum = 1;
    var fromDate = $("#fromDate").val().trim();
    var toDate = $("#toDate").val().trim();
    var title = $("#title_Search").val().trim();
    var fullname = $("#fullname_Search").val().trim();
    var topicId = $("#topicId_searchCombobox").val();
    ele_datagrid.loadPageAgainRes("#gridNews", "getAll.json?fromDate=" + fromDate + "&toDate=" +
            toDate + "&title=" + title + "&fullname=" + fullname + "&topicId=" + topicId);
}
var isInsert = 1;
var datafields = [
    {name: 'newId', type: 'number'},
    {name: 'title', type: 'text'},
    {name: 'content', type: 'number'},
    {name: 'topicName', type: 'text'},
    {name: 'fullname', type: 'text'},
    {name: 'status', type: 'text'},
    {name: 'dateCreated', type: 'date'}
];
var columns = [
    {text: "Tiêu đề", datafield: 'title', width: "35%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Tác giả", datafield: 'fullname', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Chủ đề", datafield: 'topicName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Ngày viết", styleClass: "text-center", datafield: 'dateCreated', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Trạng thái", styleClass: "text-center", datafield: 'status', filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'newId', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
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
$("#TblNews").addClass("active");
editCellRenderer = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật tin tức" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/edit.png" class="grid-icon"/>'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa tin tức" class="tooltipCus" href="javascript:void(0)" onclick="onClickBtDelete(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};
ele_combobox.buildCombobox("topicId", "getDataByDvsGroup.json", 0, "name", "id", "Lựa chọn");
ele_combobox.buildCombobox("topicId_search", "getDataByDvsGroup.json", 0, "name", "id", "Lựa chọn");
doSearch();
$('.datepicker').datepicker({
    autoclose: true,
    duration: "fast",
    changeMonth: true,
    changeYear: true,
    format: 'dd/mm/yyyy',
    constrainInput: true,
    maxDate: 0,
    yearRange: "-70:+0"
});
$(".custom-select").select2({width: "100%"});
$(".tooltipCus").tooltip();
$("#btn-insert").click(function () {
    ele_form.clearError();
    isInsert = 1;
    $("#newId").val("");
    $("#title").val("");
    $("#topicIdCombobox").val($("#topicIdCombobox option:first").val()).trigger('change');
    $("#status").val($("#status option:first").val()).trigger('change');
    CKEDITOR.instances["contentEditor"].setData("");
    $("#modal_default").modal("show");
    $("#modal_default .modal-title").html("Thêm mới bài viết");
});
CKEDITOR.replace('contentEditor');
$("#btnSave").click(function () {
    $("#content").val(CKEDITOR.instances['contentEditor'].getData());
    if (validate1()) {
        $.ajax({
            type: "POST",
            data: $('#tblNewsForm').serialize(),
            url: 'saveNews.html',
            async: false,
            success: function (data) {
                if (data != null && !data.hasError) {
                    $("#modal_default").modal("hide");
                    doSearch();
                    if (isInsert == 1) {
                        showNotif("Thêm mới bài viết thành công", false);
                    } else {
                        showNotif("Cập nhật bài viết thành công", false);
                    }
                } else if (data != null && data.errorResult != null) {
                    notif({
                        type: 'success',
                        position: 'bottom',
                        msg: data.errorResult.error
                    });
                } else {
                    showNotif(isInsert == 1 ? "Đã có lỗi khi thêm mới bài viết" : "Đã có lỗi khi cập nhật bài viết", true);
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
$.validator.addMethod("checkSelect2", function (value, element) {
    return parseInt(value.trim()) > 0;
}, "No space please and don't leave it empty");
var validator;
function validate1() {
    validator = $("#tblNewsForm").validate({
        ignore: [],
        rules: {
            title: {
                required: true
            },
            contentEditor: {
                required: function () {
                    CKEDITOR.instances.contentEditor.updateElement();
                }
            },
            topicId: {
                checkSelect2: true
            }
        },
        messages: {
            title: {
                required: "Bạn chưa nhập tiêu đề bài viết"
            },
            contentEditor: {
                required: "Bạn chưa nhập tên danh mục"
            },
            topicId: {
                checkSelect2: "Bạn chưa chọn chủ đề"
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
function onClickBtEdit(newId) {
    isInsert = 0;
    $("#title").val("");
    $("#topicIdCombobox").val($("#topicIdCombobox option:first").val()).trigger('change');
    $("#status").val($("#status option:first").val()).trigger('change');
    $("#modal_default").find(".modal-title").html("Cập nhật bài viết");
    $.ajax({
        async: false,
        type: 'POST',
        data: {newId: newId},
        url: "findById.json",
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy bài viết cần cập nhật"
                });
            } else {
                $("#newId").val(obj.newId);
                $("#title").val(obj.title);
                $("#topicIdCombobox").val(obj.topicId).trigger('change');
                $("#status").val(obj.status).trigger('change');
                CKEDITOR.instances.contentEditor.setData(obj.content);
                $("#modal_default").modal("show");
            }
        }
    });
}
function onClickBtDelete(newId) {
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa bài viết này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    doSearch();
                    $.ajax({
                        type: 'POST',
                        data: {newId: newId},
                        url: "deleteNews.json",
                        success: function (obj) {
                            console.log(obj);
                            if (obj.hasError) {
                                showNotif(obj.error, true);
                            }
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
