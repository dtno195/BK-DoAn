
editCellRenderer = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật câu hỏi" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/edit.png" class="grid-icon"/>'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa câu hỏi" class="tooltipCus" href="javascript:void(0)" onclick="onClickBtDelete(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};
function doSearch() {
    pagenum = 1;
    var content = $("#content_search").val().trim();
    var examId = $("#exam_search").val();
    var subjectId = $("#subjectId_search").val();
    var levelId = $("#levelId_search").val();
    ele_datagrid.loadPageAgainRes("#gridQuestion", "getAll.json?content=" + content + "&examId=" +
            examId + "&subjectId=" + subjectId + "&levelId=" + levelId);
}
var isInsert = 1;
var datafields = [
    {name: 'questionId', type: 'number'},
    {name: 'content', type: 'text'},
    {name: 'subjectId', type: 'number'},
    {name: 'topicId', type: 'number'},
    {name: 'examId', type: 'number'},
    {name: 'levelId', type: 'number'},
    {name: 'subjectName', type: 'text'},
    {name: 'levelName', type: 'text'},
    {name: 'dateCreated', type: 'date'}
];
var columns = [
    {text: "Câu hỏi", datafield: 'content', width: "35%", filtertype: 'input', res: "data-hide='phone'"},
    {text: "Môn học", datafield: 'subjectName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Mức độ", datafield: 'levelName', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Ngày tạo", styleClass: "text-center", datafield: 'dateCreated', filtertype: 'input', res: "data-hide='phone'"},
    {text: "", datafield: 'questionId', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
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
$("#TblQuestions").addClass("active");
doSearch();
$('.minimal').iCheck({
    checkboxClass: 'icheckbox_minimal-blue',
    radioClass: 'iradio_minimal-blue'
});
$("#btn-insert").click(function () {
    $("#modal_default").modal("show");
    $("#timeIdCombobox").val($("#timeIdCombobox option:first").val()).trigger('change');
    $("#subjectIdCombobox").val($("#subjectIdCombobox option:first").val()).trigger('change');
    CKEDITOR.instances["contentEditor"].setData("");
    $("#modal_default .modal-title").html("Thêm mới câu hỏi");
    addInstance();
});
$(".custom-select").select2({width: "100%"});
$(".tooltipCus").tooltip();
CKEDITOR.replace('contentEditor');
$("#delete-all").click(function () {
    var ids = objCommon.listIds.toString();
      $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa các câu hỏi này không?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    doSearch();
                    $.ajax({
                        type: 'POST',
                        data: {ids: ids},
                        url: "deleteQuestions.json",
                        success: function (obj) {
                            console.log(obj);
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
});
$("#btnSave").click(function () {
    $("#content").val(CKEDITOR.instances['contentEditor'].getData());
    if (validate1()) {
        reloadAnswer();

        $.ajax({
            type: "POST",
            data: $("#tblQuestionForm").serialize(),
            url: 'saveQuestion.html',
            async: false,
            success: function (data) {
                if (data != null && !data.hasError) {
                    $("#modal_default").modal("hide");
                    doSearch();
                    if (isInsert == 1) {
                        showNotif("Thêm mới câu hỏi thành công", false);
                    } else {
                        showNotif("Cập nhật câu hỏi thành công", false);
                    }
                } else if (data != null && data.errorResult != null) {
                    notif({
                        type: 'success',
                        position: 'bottom',
                        msg: data.errorResult.error
                    });
                } else {
                    showNotif(isInsert == 1 ? "Đã có lỗi khi thêm mới câu hỏi" : "Đã có lỗi khi cập nhật câu hỏi", true);
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
    validator = $("#tblQuestionForm").validate({
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
                required: "Bạn chưa nhập tên "
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


function onClickBtEdit(questionId) {
    isInsert = 0;
    $.ajax({
        async: false,
        type: 'POST',
        data: {questionId: questionId},
        url: "findById.json",
        success: function (obj) {
            if (obj == null) {
                notif({
                    type: 'error',
                    position: 'bottom',
                    msg: "Không tìm thấy câu hỏi cần cập nhật"
                });
            } else {
                $("#questionId").val(obj.questionId);
                $("#subjectId").val(obj.subjectId).trigger('change');
                $("#levelId").val(obj.levelId).trigger('change');
                $("#Answer_child").empty();
                CKEDITOR.instances.contentEditor.setData(obj.content);
                var length = 0;
                if (obj.lstAnswer != null) {
                    length = obj.lstAnswer.length;
                    for (var i = 0; i < length; i++) {
                        loadAnswer(obj.lstAnswer[i]);
                    }
                }
                $("#modal_default").modal("show");
            }
        }
    });
}
function onClickBtDelete(examId) {
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa câu hỏi này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    doSearch();
                    $.ajax({
                        type: 'POST',
                        data: {questionId: examId},
                        url: "deleteQuestion.json",
                        success: function (obj) {
                            console.log(obj);
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


var answer = 0;
function addInstance() {
    answer++;
    var html = "<tr>";
    html += "<td><input name='answerId' type='hidden'/><input name='content' id='content_" + answer + "' type='text' class='form-control contenttext' placeholder='Nhập câu trả lời'></td>";
    html += '<td style="text-align: center"><input type="checkbox" class="minimal" name="isTrue" id="is_true_' + answer + '"></td>';
    html += '<td><a class="remove_answer" title="Xóa câu trả lời" style="cursor:pointer;"><span class="fa fa-trash-o  fa-lg"></span></a></td>';
    html += "</tr>";
    $("#Answer_child").append(html);
    $('.minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue'
    });
}
function reloadAnswer() {
    var index = 0;
    $('#Answer_child tr').each(function (i, obj) {
        $(this).find(".contenttext").attr("name", "lstAnswer[" + (index) + "].content");
        $(this).find(".minimal").attr("name", "lstAnswer[" + (index) + "].isTrue");
        $(this).find(".answerCheck").attr("name", "lstAnswer[" + (index) + "].answerId");
        index++;
    });
}
function loadAnswer(obj) {
    var html = "<tr id='answer_child_" + obj.answerId + "'>";
    html += "<td><input class='answerCheck' name='answerId' type='hidden' value='" +obj.answerId + "'/><input name='content' value='" + obj.content + "' type='text' class='form-control contenttext' placeholder='Nhập câu trả lời'></td>";
    html += '<td style="text-align: center"><input ' + (obj.isTrue ? "checked='checked'" : "") + ' type="checkbox" class="minimal" name="isTrue" ></td>';
    html += '<td><a href="javascript:void(0)" onclick="removeAnswer(' + obj.answerId + ')" title="Xóa câu trả lời" style="cursor:pointer;"><span class="fa fa-trash-o  fa-lg"></span></a></td>';
    html += "</tr>";
    $("#Answer_child").append(html);
    $('input[type="checkbox"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue'
    });
}
$(document).on("click", ".remove_answer", function () {
    var dvsName = $(this).closest("tr").find(".contenttext").val().trim();
    var rowCount = $("#Division-child tr").length;
    var this1 = $(this);
    if (dvsName.length > 0) {
        $.confirm({
            'title': 'Xác nhận xóa',
            'message': 'Bạn có chắc chắn muốn xóa câu trả lời này?',
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
function removeAnswer(answerId) {
    $.confirm({
        'title': 'Xác nhận xóa',
        'message': 'Bạn có chắc chắn muốn xóa câu trả lời này?',
        'buttons': {
            'Xóa': {
                'class': 'btn-confirm-yes btn-success',
                'action': function () {
                    doSearch();
                    $.ajax({
                        type: 'POST',
                        data: {answerId: answerId},
                        url: "deleteAnswer.json",
                        success: function (obj) {
                            $("#answer_child_" + answerId).remove();
                            if (obj.hasError || obj == "") {
                                showNotif(obj.error == "" ? "Không thể xóa câu trả lời này" : obj.error, true);
                            } else {
                                showNotif("Xóa câu trả lời thành công", false);
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
$("#btn-import").click(function () {
    $("#fileImport").val(null);
    $("#importRegister").modal("show");
});
function downloadTemplate(type) {
    var url = "/Elearning/TblQuestions/downloadTemplate.html?type=" + type;
    location.href = url;
}
function importData() {
    if ($("#fileImport").val() == null || $("#fileImport").val() == "") {
        $("#fileUploadNameError").html("Bạn chưa chọn file import");
        return false;
    }
    if (!isFileValid("#fileImport", "application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document", 1048576)) {
        $("#fileUploadNameError").html("File không đúng định dạng hoặc quá dung lượng cho phép");
        return false;
    }
    if ($("#subjectId_import").val() < 0) {
        $("#subjectImportErr").html("Bạn chưa chọn môn học");
        return false;
    }
    var oMyForm = new FormData();
      oMyForm.append("files", fileImport.files[0]);
    
    $.ajax({
       url : 'importExcel.html?subjectId=' + $("#subjectId_import").val(),
       type : 'POST',
       data : oMyForm,
       processData: false,  // tell jQuery not to process the data
       contentType: false,  // tell jQuery not to set contentType
       success : function(result) {
            $("#importRegister").modal("hide");
            if (result.totalFail > 0) {
                $("#dialog-Error").modal("show");
                loadError(result);
                if (result.totalSuccess > 0) {
                    notifSuccess("Import dữ liệu câu hỏi thành công");
                    
                } else {
                    notifError("Import dữ liệu câu hỏi không thành công");
                }
            } else {
                notifSuccess("Import tất cả dữ liệu thành công (" + result.totalSuccess + " bản ghi)");
            }
            doSearch();
       }
    });



//    jQuery.ajax({
//        type: "POST",
//        async: false,
//        url: 'importExcel.html',
//        data: oMyForm,
//        processData: false,
//        contentType: false,
//        dataType: 'json',
//        enctype: 'multipart/form-data',
//        success: function (result) {
//            $("#importRegister").modal("hide");
//            if (result.totalFail > 0) {
//                $("#dialog-Error").modal("show");
//                loadError(result);
//                if (result.totalSuccess > 0) {
//                    notifSuccess("Import dữ liệu câu hỏi thành công");
//                    doSearch();
//                } else {
//                    notifError("Import dữ liệu câu hỏi không thành công");
//                }
//            } else {
//                notifSuccess("Import tất cả dữ liệu thành công (" + result.totalSuccess + " bản ghi)");
//            }
//        },
//        error: function (err) {
//            console.log(err);
//        }
//    });
}
function loadError(result) {
    if (result !== null) {
        var html = "";
        var item = JSON.parse(result.rdList);
        for (var i = 0; i < item.length; i++) {
            html += '<tr>';
            html += '<td class="stt">' + (i + 1) + '</td>';
            html += '<td class="stt">' + item[i].row + '</td>';
            var htmlChild = '';
            var arrError = [];
            for (var j = 0; j < item[i].lstError.length; j++) {
                if (arrError.indexOf(item[i].lstError[j].fieldName) < 0) {
                    htmlChild += "<span class='glyphicon glyphicon-exclamation-sign txt-color-red' aria-hidden='true'></span>            ";
                    htmlChild += escapeHTML(item[i].lstError[j].error) + "<br/><br/>";
                } else {
                    arrError.push(item[i].lstError[j].fieldName);
                }
            }
            html += '<td>' + htmlChild + '</td>';
        }
        html += '</tr>';
        var totalFail = parseInt(result.totalFail);
        var totalSuccess = parseInt(result.totalSuccess);
        $("label[for='error']").html(": " + totalFail);
        $("label[for='success']").html(": " + totalSuccess);
        $("label[for='totalRecord']").html(": " + (totalFail + totalSuccess));
        $("#listDataImportErrorBody").html(html);
    }
}
$("#btnImport").button().click(function () {
    importData();
});
$('#fileImport').on('change', function () {
    fileValidation();
});
function fileValidation() {
    var filename = $('input[type=file]').val().split('\\').pop();
    $("#fileUploadNameError").text('');
    $("#fileUploadName").text(filename);
    return true;
}