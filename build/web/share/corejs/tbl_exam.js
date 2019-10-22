editCellRenderer = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    <a data-toggle="tooltip" data-placement="top" title="Kết xuất file word" class="tooltipCus" onclick="onClickBtExport(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/word.png" class="grid-icon"/></a>'
            + '    <a data-toggle="tooltip" data-placement="top" title="Danh sách câu hỏi" class="tooltipCus" onclick="onClickBtQuestion(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/question.png" class="grid-icon"/></a>'
            + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật đề thi" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/edit.png" class="grid-icon"/>'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa đề thi" class="tooltipCus" href="javascript:void(0)" onclick="onClickBtDelete(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};
function doSearch() {
    pagenum = 1;
    var subjectId = $("#subjectId_searchCombobox").val().trim();
    var timeId = $("#timeId_searchCombobox").val().trim();
    var name = $("#name_search").val();
    ele_datagrid.loadPageAgainRes("#gridExam", "getAll.json?subjectId=" + subjectId + "&timeId=" +
            timeId + "&name=" + name);
}
var isInsert = 1;
var datafields = [
    {name: 'examId', type: 'number'},
    {name: 'name', type: 'text'},
    {name: 'content', type: 'number'},
    {name: 'subjectName', type: 'text'},
    {name: 'fullname', type: 'text'},
    {name: 'timeName', type: 'text'},
    {name: 'dateCreated', type: 'date'}
];
var columns = [
    {text: "Tiêu đề", datafield: 'name', width: "35%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Tác giả", datafield: 'fullname', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Môn học", datafield: 'subjectName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Thời gian", datafield: 'timeName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Ngày tạo", styleClass: "text-center", datafield: 'dateCreated', filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'examId', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
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
$("#TblExam").addClass("active");
ele_combobox.buildCombobox("subjectId", "getSubject.json", 0, "dvsName", "dvsValue", "Lựa chọn");
ele_combobox.buildCombobox("subjectId_search", "getSubject.json", 0, "dvsName", "dvsValue", "Lựa chọn");
ele_combobox.buildCombobox("timeId", "getTime.json", 0, "dvsName", "dvsValue", "Lựa chọn");
ele_combobox.buildCombobox("timeId_search", "getTime.json", 0, "dvsName", "dvsValue", "Lựa chọn");
ele_combobox.buildCombobox("levelId", "getLevel.json", 0, "dvsName", "dvsValue", "Lựa chọn");
ele_combobox.buildCombobox("levelSelectedId", "getLevel.json", 0, "dvsName", "dvsValue", "Lựa chọn");
doSearch();
$("#btn-insert").click(function () {
    $("#modal_default").modal("show");
    $("#name").val("");
    $("#timeIdCombobox").val($("#timeIdCombobox option:first").val()).trigger('change');
    $("#subjectIdCombobox").val($("#subjectIdCombobox option:first").val()).trigger('change');
    CKEDITOR.instances["contentEditor"].setData("");
    $("#modal_default .modal-title").html("Thêm mới đề thi");
});
$(".custom-select").select2({width: "100%"});
$(".tooltipCus").tooltip();
CKEDITOR.replace('contentEditor');

$("#btnSave").click(function () {
    $("#content").val(CKEDITOR.instances['contentEditor'].getData());
    if (validate1()) {
        $.ajax({
            type: "POST",
            data: $('#tblExamForm').serialize(),
            url: 'saveExam.html',
            async: false,
            success: function (data) {
                if (data != null && !data.hasError) {
                    $("#modal_default").modal("hide");
                    doSearch();
                    if (isInsert == 1) {
                        showNotif("Thêm mới đề thi thành công", false);
                    } else {
                        showNotif("Cập nhật đề thi thành công", false);
                    }
                } else if (data != null && data.hasError) {
                    notif({
                        type: 'success',
                        position: 'bottom',
                        msg: data.error
                    });
                } else {
                    showNotif(isInsert == 1 ? "Đã có lỗi khi thêm mới đề thi" : "Đã có lỗi khi cập nhật đề thi", true);
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
    validator = $("#tblExamForm").validate({
        ignore: [],
        rules: {
            name: {
                required: true
            },
            contentEditor: {
                required: function () {
                    CKEDITOR.instances.contentEditor.updateElement();
                }
            },
            subjectId: {
                checkSelect2: true
            },
            timeId: {
                checkSelect2: true
            }
        },
        messages: {
            name: {
                required: "Bạn chưa nhập tên đề thi"
            },
            contentEditor: {
                required: "Bạn chưa nhập tên danh mục"
            },
            subjectId: {
                checkSelect2: "Bạn chưa chọn môn học"
            },
            timeId: {
                checkSelect2: "Bạn chưa chọn thời gian thi"
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


function onClickBtEdit(examId) {
    isInsert = 0;
    $.ajax({
        async: false,
        type: 'POST',
        data: {examId: examId},
        url: "findById.json",
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy đề thi cần cập nhật"
                });
            } else {
                $("#examId").val(obj.examId);
                $("#name").val(obj.name);
                $("#timeIdCombobox").val(obj.timeId).trigger('change');
                $("#subjectIdCombobox").val(obj.subjectId).trigger('change');
                CKEDITOR.instances.contentEditor.setData(obj.content);
                $("#modal_default").modal("show");
            }
        }
    });
}
function onClickBtDelete(examId) {
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa đề thi này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    doSearch();
                    $.ajax({
                        type: 'POST',
                        data: {examId: examId},
                        url: "deleteExam.json",
                        success: function (obj) {
                            if (obj.hasError || obj == "") {
                                showNotif(obj.error, true);
                            } else {
                                showNotif("Xóa đề thi thành công", false);
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
var datafields1 = [
    {name: 'questionId', type: 'number'},
    {name: 'content', type: 'text'},
    {name: 'subjectName', type: 'text'},
    {name: 'levelName', type: 'text'},
    {name: 'dateCreated', type: 'date'}
];
var columns1 = [
    {text: "Câu hỏi", datafield: 'content', width: "35%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Môn học", datafield: 'subjectName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Mức độ", datafield: 'levelName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Ngày tạo", styleClass: "text-center", datafield: 'dateCreated', filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'questionId', hidden: true, edit: 1, sortable: false, clstitle: 'tlb_class_center'}
];
var gridSetting1 = {
    sortable: false,
    virtualmode: true,
    enableToolbar: true,
    enableDelete: true,
    enableSearch: true,
    enableCheckbox: true
};
var datafields2 = [
    {name: 'questionId', type: 'number'},
    {name: 'content', type: 'text'},
    {name: 'subjectName', type: 'text'},
    {name: 'levelName', type: 'text'},
    {name: 'examQuestionId', type: 'number'},
    {name: 'dateCreated', type: 'date'}
];
var columns2 = [
    {text: "Câu hỏi", datafield: 'content', width: "35%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Môn học", datafield: 'subjectName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Mức độ", datafield: 'levelName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Ngày tạo", styleClass: "text-center", datafield: 'dateCreated', filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'examQuestionId', edit: 1, hidden: true, sortable: false, clstitle: 'tlb_class_center'}
];
var gridSetting2 = {
    sortable: false,
    virtualmode: true,
    enableToolbar: true,
    enableDelete: true,
    enableSearch: true,
    enableCheckbox: true
};
editCellRendererVT2 = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa câu hỏi" class="tooltipCus" href="javascript:void(0)" onclick="removeQuestion(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};
var examId = 0;
function onClickBtQuestion(id) {
    $.ajax({
        async: false,
        type: 'POST',
        data: {examId: id},
        url: "findById.json",
        dataType: 'json',
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy đề thi"
                });
            } else {
                $("#examNameText").html("<b>Đề thi: </b>" + obj.name);
                $("#examQuesId").val(id);
                examId = id;
                $("#modal_question").modal("show");
                ele_datagrid.loadPageAgain1("#gridQuestion", "getQuestion.json?examId=" + examId);
                ele_datagrid.loadPageAgain2("#gridSelected", "getSelectedQuestion.json?examId=" + examId);
            }
        }
    });

}
$("#btnSearchSelected").click(function () {
    searchSelected();
});
$("#btnSearchQuestion").click(function () {
    searchQuestion();
});
function searchSelected() {
    var content = $("#content_selected").val();
    var levelId = $("#levelSelectedIdCombobox").val();
    ele_datagrid.loadPageAgain2("#gridSelected", "getSelectedQuestion.json?examId=" + examId
            + "&content=" + content + "&levelId=" + levelId);
}
function searchQuestion() {
    var content = $("#content_question").val();
    var levelId = $("#levelIdCombobox").val();
    ele_datagrid.loadPageAgain1("#gridQuestion", "getQuestion.json?examId=" + examId
            + "&content=" + content + "&levelId=" + levelId);
}
function removeQuestion(id) {
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa câu hỏi này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    $.ajax({
                        type: 'POST',
                        data: {id: id},
                        dataType: 'json',
                        url: "deleteExamQuestion.json",
                        success: function (obj) {
                            if (obj.hasError || obj == "") {
                                notifError(obj.error == "" ? "Không thể xóa câu hỏi này" : obj.error);
                            } else {
                                notifSuccess("Xóa câu hỏi thành công");
                            }
                            searchSelected();
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
$(".add-item").click(function () {
    if (objCommon.listIds1.length == 0) {
        notifError("Bạn chưa chọn câu hỏi nào");
    } else {
//        $.confirm({
//            'title': 'Xác nhận xóa',
//            'message': 'Bạn có chắc chắn muốn xóa câu hỏi này?',
//            'buttons': {
//                'Xóa': {
//                    'class': 'btn-confirm-yes btn-success',
//                    'action': function () {
        $.ajax({
            type: "POST",
            data: {examId: examId, questionIds: (objCommon.listIds1 + "")},
            url: 'addQuestion.html',
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data != null && !data.hasError) {
                    searchQuestion();
                    searchSelected();
                    notifSuccess("Thêm câu hỏi thành công");
                } else if (data != null && data.hasError) {
                    notif({
                        type: 'success',
                        position: 'bottom',
                        msg: data.error
                    });
                } else {
                    notifError("Đã có lỗi xảy ra")
                }
            }, error: function (err) {
                console.log(err);
            }
        });
//                    }
//                },
//                'Không xóa': {
//                    'class': 'btn-info',
//                    'action': function () { }
//                }
//            }
//        });

    }
});
$(".remove-item").click(function () {
    if (objCommon.listIds2.length == 0) {
        notifError("Bạn chưa chọn câu hỏi nào");
    } else {
        $.confirm({
            'title': 'Xác nhận xóa',
            'message': 'Bạn có chắc chắn muốn xóa câu hỏi này?',
            'buttons': {
                'Xóa': {
                    'class': 'btn-confirm-yes btn-success',
                    'action': function () {
                        $.ajax({
                            type: "POST",
                            data: {examId: examId, ids: (objCommon.listIds2 + "")},
                            url: 'deleteQuestion.html',
                            async: false,
                            dataType: 'json',
                            success: function (data) {
                                if (data != null && !data.hasError) {
                                    searchQuestion();
                                    searchSelected();
                                    notifSuccess("Loại bỏ câu hỏi thành công");
                                } else if (data != null && data.hasError) {
                                    notif({
                                        type: 'success',
                                        position: 'bottom',
                                        msg: data.error
                                    });
                                } else {
                                    notifError("Đã có lỗi xảy ra")
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
});
function onClickBtExport(id) {
    $("#contentExport").val("");
    $("#changeAnswer").val($("#changeAnswer option:first").val()).trigger('change');
    $("#changeQuestion").val($("#changeQuestion option:first").val()).trigger('change');
    $("#modal_export").modal("show");
    examId = id;
}
$("#btnExport").click(function () {
    var contentExport = $("#contentExport").val();
    var changeQuestion = $("#changeQuestion").val();
    var changeAnswer = $("#changeAnswer").val();
    var examNumber = $("#examNumber").val();
    var contentExport = $("#contentExport").val();
    location.href = "/Elearning/TblExam/exportWord.html?changeQuestion=" + changeQuestion + "&changeAnswer=" + changeAnswer +
            "&examId=" + examId + "&examNumber=" + examNumber + "&contentExport=" + contentExport;
});