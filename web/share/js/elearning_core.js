//ele stand for elearning
//
/*!
 
 * Copyright © 2018 LienPTK. All rights reserved
 * Released under the MIT license
 * http://jquery.org/license
 *
 * Date: 2018-09-11T17:42Z
 */
var arrHide = [0];
var arrHide1 = [0];
var arrHide2 = [0];
var arrSort = [0, 1];
var arrSort2 = [0, 1];
var arrSort1 = [0, 1];
var ele_loading = {
    showIconLoading: function () {
        $('body').addClass("loading-3");
    },
    hideIconLoading: function () {
        $('body').removeClass("loading-3");
    },
    showAlertFail: function (message) {
        $.smallBox({
            title: "Thông báo!",
            content: message,
            color: "#d9534f",
            timeout: 6000,
            icon: "fa fa-bell swing animated"
        });
    },
    showAlertFailWithTitle: function (title, message) {
        $.smallBox({
            title: title,
            content: message,
            color: "#d9534f",
            timeout: 3000,
            icon: "fa fa-warning swing animated"
        });
    },
    showAlertSuccess: function (message) {
        $.smallBox({
            title: "Thông báo!",
            content: message,
            color: "#296191",
            timeout: 6000,
            icon: "fa fa-bell swing animated"
        });
    },
    showAlertInfor: function (message, time) {
        $.smallBox({
            title: "Thông báo!",
            content: message,
            color: "#296191",
            timeout: time,
            icon: "fa fa-bell swing animated"
        });
    },
    showAlertDeviceStatus: function () {
        $("#dialog-deviceStatus").dialog("open");
        $('#dialog-deviceStatus').parent().addClass("dialogSuccess");
        setTimeout("$('#dialog-deviceStatus').dialog('close')", 3000);
    },
    showAlertChooserOne: function () {
        $("#dialog-chooseOne").dialog("open");
        $('#dialog-chooseOne').parent().addClass("dialogSuccess");
        setTimeout('$("#btnAlertSuccessYes").focus()', 100);
    },
    showConfirmMessageDialog: function (message, callback) {
        $('#confirm-message1').text(message);
        $('#dialog-confirm1').data("callback", callback).dialog("open");
        setTimeout('$("#btnConfirmNo1").focus()', 100);
        $('#dialog-confirm1').parent().addClass("dialogConfirm");
    },
    showConfirmDeleteDialog: function (message, callback) {
        $('#confirm-message-delete').text(message);
        $('#dialog-confirmDelete').data("callback", callback).dialog("open");
        setTimeout('$("#btnConfirmNo").focus()', 100);
        $('#dialog-confirmDelete').parent().addClass("dialogConfirm");
    },
    showValidateErrorDialog: function (message) {
        $('#validate-message').text(message);
        $('#dialog-validateError').dialog("open");
        setTimeout('$("#btnValidateErrorYes").focus()', 100);
    }
};

// start obj Common
var objCommon = {
    listIds: [],
    clickCheckbox: function (index, gid) {
        var x = document.getElementsByName("checkbox-inline");
        var buttonCheckAll = document.getElementById("checkAll");
        var isCheck = true;
        for (var i = 0; i < x.length; i++) {
            if (!x[i].checked) {
                isCheck = false;
                break;
            }
        }
        buttonCheckAll.checked = isCheck;
        if (index.checked) {
            objCommon.listIds.push(gid);
        } else {
            objCommon.listIds = $.grep(objCommon.listIds, function (value) {
                return value !== gid;
            });
        }
        $("#btnDeleteAll").attr("disabled", objCommon.listIds.length <= 0);
        $("#btnInActive").attr("disabled", objCommon.listIds.length <= 0);
        $("#btnActive").attr("disabled", objCommon.listIds.length <= 0);
        $("#btnDSVT").attr("disabled", objCommon.listIds.length <= 0);
        if (objCommon.listIds.length <= 0) {
            $("#btnDeleteAll").css("pointer-events", "none");
            $("#btnInActive").css("pointer-events", "none");
            $("#btnActive").css("pointer-events", "none");
            $("#btnDSVT").css("pointer-events", "none");
        } else {
            $("#btnDeleteAll").css("pointer-events", "auto");
            $("#btnInActive").css("pointer-events", "auto");
            $("#btnActive").css("pointer-events", "auto");
            $("#btnDSVT").css("pointer-events", "auto");
        }
    },
    clickCheckboxAll: function (isChecked) {
        var x = document.getElementsByName("checkbox-inline");

        for (var i = 0; i < x.length; i++) {
            x[i].checked = isChecked;
            $(this).iCheck('update');
        }
        if (!isChecked) {
            objCommon.listIds = [];
        } else {
            objCommon.listIds = (new Function("return [" + $("#allValue").val() + "];")());
        }
        $("#btnDeleteAll").attr("disabled", objCommon.listIds.length <= 0);
        $("#btnActive").attr("disabled", objCommon.listIds.length <= 0);
        $("#btnInActive").attr("disabled", objCommon.listIds.length <= 0);
        if (objCommon.listIds.length <= 0) {
            $("#btnDeleteAll").css("pointer-events", "none");
            $("#btnActive").css("pointer-events", "none");
            $("#btnInActive").css("pointer-events", "none");
            $("#btnDSVT").css("pointer-events", "none");
        } else {
            $("#btnDeleteAll").css("pointer-events", "auto");
            $("#btnActive").css("pointer-events", "auto");
            $("#btnInActive").css("pointer-events", "auto");
            $("#btnDSVT").css("pointer-events", "auto");
        }
    },
    clickCheckbox2: function (index, gid, selector) {
        var x = document.getElementsByName("checkbox-inline2");
        var buttonCheckAll = document.getElementById("checkAll2");
        var isCheck = true;
        for (var i = 0; i < x.length; i++) {
            if (!x[i].checked && !x[i].disabled) {
                isCheck = false;
                break;
            }
        }
        buttonCheckAll.checked = isCheck;
        if (selector !== undefined && selector !== null && selector === "#datacontrol") {
            if (!index.checked) {
                objCommon.listIds2.push(gid);
            } else {
                objCommon.listIds2 = $.grep(objCommon.listIds2, function (value) {
                    return value !== gid;
                });
            }
        } else {
            if (index.checked) {
                objCommon.listIds2.push(gid);
            } else {
                objCommon.listIds2 = $.grep(objCommon.listIds2, function (value) {
                    return value != gid;
                });
            }
        }
        $("#btnDeleteAll2").attr("disabled", objCommon.listIds2.length <= 0);
        if (objCommon.listIds2.length <= 0) {
            $("#btnDeleteAll2").css("pointer-events", "none");
        } else {
            $("#btnDeleteAll2").css("pointer-events", "auto");
        }
    },
    clickCheckboxAll2: function (isChecked, selector) {
        var x = document.getElementsByName("checkbox-inline2");
        for (var i = 0; i < x.length; i++) {
            if (!x[i].disabled) {
                x[i].checked = isChecked;
            }
        }
        objCommon.listIds2 = [];
        if (isChecked) {
            if (selector !== undefined && selector !== null && selector === "#datacontrol") {
                for (var i = 0; i < x.length; i++) {
                    if (x[i].disabled) {
                        objCommon.listIds2.push(x[i].id);
                    }
                }
            } else {
                for (var i = 0; i < x.length; i++) {
                    if (!x[i].disabled) {
                        objCommon.listIds2.push(x[i].id);
                    }
                }
            }
        } else if (!isChecked) {
            if (selector !== undefined && selector !== null && selector === "#datacontrol") {
                for (var i = 0; i < x.length; i++) {
                    if (!x[i].checked) {
                        objCommon.listIds2.push(x[i].id);
                    }
                }
            }
        }

        $("#btnDeleteAll2").attr("disabled", objCommon.listIds2.length <= 0);
        if (objCommon.listIds2.length <= 0) {
            $("#btnDeleteAll2").css("pointer-events", "none");
        } else {
            $("#btnDeleteAll2").css("pointer-events", "auto");
        }
    },
    clickCheckbox1: function (index, gid) {
        var x = document.getElementsByName("checkbox-inline1");
        var buttonCheckAll = document.getElementById("checkAll1");
        var isCheck = true;
        for (var i = 0; i < x.length; i++) {
            if (!x[i].checked) {
                isCheck = false;
                break;
            }
        }
        buttonCheckAll.checked = isCheck;
        if (index.checked) {
            objCommon.listIds1.push(gid);
        } else {
            objCommon.listIds1 = $.grep(objCommon.listIds1, function (value) {
                return value != gid;
            });
        }

        $("#btnDeleteAll1").attr("disabled", objCommon.listIds1.length <= 0);
        $("#btnAddNewRoleForUser").attr("disabled", objCommon.listIds1.length <= -1);
        $("#btnUpdateUserSettlement").attr("disabled", objCommon.listIds1.length <= -1);
        $("#btnAddNewObject").attr("disabled", objCommon.listIds1.length <= -1);
        if (objCommon.listIds1.length <= 0) {
            $("#btnDeleteAll1").css("pointer-events", "none");
        } else {
            $("#btnDeleteAll1").css("pointer-events", "auto");
        }
    },
    clickCheckboxAll1: function (isChecked) {
        var x = document.getElementsByName("checkbox-inline1");
        for (var i = 0; i < x.length; i++) {
            x[i].checked = isChecked;
        }
        if (!isChecked) {
            objCommon.listIds1 = [];
        } else {
            objCommon.listIds1 = (new Function("return [" + $("#allValue1").val() + "];")());
        }
        $("#btnAddNewRoleForUser").attr("disabled", objCommon.listIds1.length <= -1);
        $("#btnUpdateUserSettlement").attr("disabled", objCommon.listIds1.length <= -1);
        $("#btnAddNewObject").attr("disabled", objCommon.listIds1.length <= -1);
    },
    setTimeout: function (idInput) {
        setTimeout(function () {
            $('#' + idInput).focus();
        }, 100);
    }
};
//end obj common
// start draw table
var pagenum = 1;
var pagesize = 10;
var pagenum1 = 1;
var pagesize1 = 10;
var pagenum2 = 1;
var pagesize2 = 10;
$("#pageSelectLimit1").change(function () {
    pagesize1 = $("#pageSelectLimit1").val();
    pagenum1 = 1;
    var key = $('#inputSearch1').val();
    if (key !== null) {
        key = key.trim();
        keySearch1 = key;
    }
    ele_datagrid.buildGridCheckBox1Res($("#selectorSelected1").val(), $("#urlSelected1").val(), ele_datagrid.datafields1, ele_datagrid.columns1, gridSetting, {keyWord: key, pagenum: pagenum1, pagesize: pagesize1}, ele_datagrid.toolBarSt);
});
var ele_util = {
    split: function (val) {
        return val.split(/,\s*/);
    },
    split2: function (val, delemiter) {
        return val.split(delemiter);
    },
    extractLast: function (term) {
        return ele_util.split(term).pop();
    },
    load2Tab: function (selector, url, tabIndex) {
        $.get(url, function (data) {
            $(selector).jqxTabs('setContentAt', tabIndex, data);
        });
    },
    load2LastTab: function (selector, url) {
        $.get(url, function (data) {
            var tabIndex = $(selector).jqxTabs('length');
            $(selector).jqxTabs('setContentAt', tabIndex - 1, data);
        });
    },
    addTab2: function (tabs, url) {
        $("a[href='tab-2']").remove();
        $("div[id='tab-2']").remove();
        var id = "tab-2";
        var tabTemplate = "<li><a href='#tab-2'>Tab 2</a></li>";
        var li = $(tabTemplate);
        $.get(url, function (data) {
            tabs.find(".ui-tabs-nav").append(li);
            tabs.append("<div id='" + id + "'>" + data + "</div>");
            tabs.tabs("refresh");
        });
    },
    // Escape a string for HTML interpolation.
    escapeHTML: function (string) {
        var htmlEscapes = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#x27;',
            '/': '&#x2F;'
        };
        // Regex containing the keys listed immediately above.
        var htmlEscaper = /[&<>"'\/]/g;
        return ('' + string).replace(htmlEscaper, function (match) {
            return htmlEscapes[match];
        });
    },
    // Escape a string for HTML interpolation.
    escapeHTMLTime: function (string) {
        var htmlEscapes = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#x27;'
        };
        // Regex containing the keys listed immediately above.
        var htmlEscaper = /[&<>"']/g;
        return ('' + string).replace(htmlEscaper, function (match) {
            return htmlEscapes[match];
        });
    },
    unEscapeHTML: function (string) {
        if (string !== null && string !== "" && string.length > 0) {
            return string
                    .replace(/&amp;/g, '&')
                    .replace(/&lt;/g, '<')
                    .replace(/&gt;/g, '>')
                    .replace(/&quot;/g, '"')
                    .replace(/&#x2F;/g, '/')
                    .replace(/#x27;/g, "'");
        } else {
            return "";
        }

    },
    escapeHTMLSearch: function (string) {
        var htmlEscapes = {
            '&': 'amp;',
            '<': 'lt;',
            '>': 'gt;',
            '"': 'quot;',
            "'": '#x27;',
            '/': '#x2F;'
        };
        // Regex containing the keys listed immediately above.
        var htmlEscaper = /[&<>"'\/]/g;
        return ('' + string).replace(htmlEscaper, function (match) {
            return htmlEscapes[match];
        });
    },
    trimForm: function (formId) {
        var $inputs = $(formId).find("input[type='text']");
        $inputs.each(function () {
            $(this).val($(this).val().trim());
        });
    },
    escapeForm: function (formId) {
        var $inputs = $(formId).find("input[type='text']");
        $inputs.each(function () {
            $(this).val(ele_util.escapeHTML($(this).val()));
        });
    },
    nullToEmpty: function (obj) {
        if (obj === null) {
            return "";
        }
        return obj;
    }
};
var loadingBuildGridCheckBoxRes = false;
var ele_datagrid = {
    hasRadio: null,
    datafields1: null,
    columns1: null,
    datafields2: null,
    columns2: null,
    datafields4: null,
    datafields5: null,
    columns4: null,
    columns5: null,
    toolBarSt: null,
    toolBarSt2: null,
    toolBarSt3: null,
    toolBarSt4: null,
    toolBarSt5: null,
    buildGridCheckBox1Res: function (selector, url, datafields, columns, settings, param, toolBarSt) {
        $("#allValue1").remove();
        if ($('#table-responsive1').find('div.dt-toolbar').length >= 1)
            $('#table-responsive1').find('div').first().replaceWith("<table id='" + (selector + "").replace("#", "") + "' style='width: 100%;'></table>");
        first = 1;
        objCommon.listIds1 = [];
        ele_datagrid.datafields1 = datafields1;
        ele_datagrid.columns1 = columns1;
        ele_datagrid.toolBarSt = toolBarSt;
        $("#selectorSelected1").val(selector);
        $("#urlSelected1").val(url);
        $.ajax({
            async: false,
            data: param,
            url: url,
            success: function (obj) {
                var data = obj.data;
                var html = "";
                if (first == 1) {
                    html = "<table id='" + (selector + "").replace("#", "") + "' class='table table-striped table-bordered table-hover smart-form has-tickbox' width='100%'>";
                    html += "<thead><tr>";
                    html += "<th width='20px' class='css_header'>";
                    html += "<label class='checkbox'>";
                    html += "<input class='minimal1' onclick='objCommon.clickCheckboxAll1(this.checked)' id='checkAll1' type='checkbox' name='checkboxAll'>";
                    html += "<i></i>";
                    html += "</label>";
                    html += "</th>";
                    for (var i = 0; i < columns.length; i++)
                    {
                        var output = JSON.stringify(columns[i]);
                        var json = JSON.parse(output);
                        if (!json["hidden"]) {
                            html += "<th   class='thtableresponsive " + json["clstitle"] + "' " + json["res"] + ">" + ele_util.escapeHTML(json["text"]) + "</th>";
                        } else {
                            html += "<th style='display: none;'></th>";
                            arrHide1.push(i + 1);
                        }
                        if (json["edit"] == 1)
                            arrSort1.push(i + 1);
                    }
                    html += "</tr></thead>";
                }
                var totalChecked = 0;
                var listId = [];
                for (var i = 0; i < data.length; i++) {
                    var output = JSON.stringify(data[i]);
                    var json = JSON.parse(output);
                    var gid;
                    for (var j = 0; j < columns.length; j++) {
                        var outputColumn = JSON.stringify(columns[j]);
                        var jsonColumn = JSON.parse(outputColumn);
                        if (jsonColumn["edit"] != null) {
                            gid = json[jsonColumn["datafield"]];
                            break;
                        }
                    }
                    var valueCheck = "";
                    if (json['flag'] === true) {
                        valueCheck = 'checked';
                        objCommon.listIds1.push(gid);
                        totalChecked++;
                    }
                    html += "<td>";
                    html += "<label class='checkbox'>";
                    html += "<input class='minimal1' " + valueCheck + " id=" + gid + " value=" + gid + "  onclick='objCommon.clickCheckbox1(this," + gid + ");' type='checkbox' name='checkbox-inline1'>";
                    html += "<i></i>";
                    html += "</label>";
                    html += "</td>";
                    if (pagenum1 == null) {
                        pagenum1 = 1;
                    }
                    var numberStart = (pagenum1 - 1) * pagesize1;
                    for (var j = 0; j < columns.length; j++)
                    {
                        var outputColumn = JSON.stringify(columns[j]);
                        var jsonColumn = JSON.parse(outputColumn);
                        if (!jsonColumn["hidden"]) {
                            html += "<td class='" + jsonColumn["styleClass"] + "'>";
                            if (jsonColumn["datafield"] === '') {
                                html += (numberStart + i + 1);
                            } else if (jsonColumn["edit"] != null) {
                                html += editCellRendererVT1(json[jsonColumn["datafield"]]);
                            } else if (jsonColumn["viewControl"] != null) {
                                html += editCellRendererOC(json[jsonColumn["datafield"]], valueCheck);
                            } else {
                                var valueTd = json[jsonColumn["datafield"]];
                                if (valueTd == null) {
                                    valueTd = '';
                                } else if (jsonColumn["datafield"] === "eventDate" || jsonColumn["datafield"] === "dateCreated") {
                                    html += formatDate(new Date(valueTd));
                                } else {
                                    html += valueTd;
                                }
                            }
                            html += "</td>";
                        } else {
                            var column = JSON.parse(outputColumn);
                            var colName = column["datafield"];
                            html += "<td style='display:none'><input type='hidden' name='" + colName + "' value='" + ele_util.escapeHTML(json[jsonColumn["datafield"]]) + "'></td>";
                        }
                        if (jsonColumn["edit"] != null) {
                            listId.push(json[jsonColumn["datafield"]]);
                        }
                    }
                    html += "</tr>";
                }
                if (first == 1) {
                    html += "</table>";
                }
                html += "<input id='allValue1' type='hidden' value='" + listId + "'>";
                if (first == 1) {
                    $(selector).replaceWith(html);
                } else {
                    $(selector + " tbody").html(html);
                }
                if ($(selector + " tbody tr").length == 0) {
                    if ($(selector).find("tbody").length == 0) {
                        $(selector).append("<tbody></tbody>");
                    }
                    $(selector + " tbody").html("<tr><td colspan='" + (columns.length + 1) + "'>Không tìm thấy bản ghi nào</td></tr>");
                }
                if (totalChecked > 0 && totalChecked === data.length) {
                    $("#checkAll1").attr("checked", true);
                }
                var total = obj.totalRecords;
                pagenum1 = obj.curPage;
                ele_datagrid.pagingTable(selector, pagenum1, pagesize1, total, url, '1');
                first += 1;
                ele_datagrid.loadScriptTable1(selector, toolBarSt);
            }, complete: function (jqXHR, textStatus) {
//                $('input[type="checkbox"].minimal1').iCheck({
//                    checkboxClass: 'icheckbox_minimal1-blue',
//                    radioClass: 'iradio_minimal1-blue'
//                });
            }
        });
    },
    loadPageAgain1: function (selector, url) {
        objCommon.listIds1 = [];
        var isSearch = $('#isSearch1').val();
        if (isSearch === '1') {
            var key = $('#inputSearch1').val();
            if (key != null) {
                key = key.trim();
                keySearch1 = key;
            }
            ele_datagrid.buildGridCheckBox1Res(selector, url, datafields1, columns1, gridSetting1, {keyWord: key, pagenum: pagenum1, pagesize: pagesize1}, ele_datagrid.toolBarSt);
        } else {
            ele_datagrid.buildGridCheckBox1Res(selector, url, datafields1, columns1, gridSetting1, {pagenum: pagenum1, pagesize: pagesize1}, ele_datagrid.toolBarSt);
        }
    },
    buildGridCheckBoxRes: function (selector, url, datafields, columns, settings, param, changeOption) {
        if (!loadingBuildGridCheckBoxRes) {
            loadingBuildGridCheckBoxRes = true;
            $("#allValue").remove();
            var toolBarSt = '<div class="DTTT_container" style="float:left;">            <span class="tableHeaderBttonAdd"><div href="#" id="btnAddNew" onclick="onClickBtAdd()" class="btn btn-primary btnAdd">' + $("#tblBtnAdd").val()
                    + '</div></span><span class="tableHeaderBttonDellAll" style="margin-left:1px"><div id="btnDeleteAll" onclick="doDeleteAll()" style="pointer-events: none;" class="btnExport btn btn-danger btnDeleteAll">' + $("#tblBtnDelete").val() + '</div></span>'
                    + '</div>';
            var importToolBarSt = '';
            if (settings.isImport !== undefined) {
                importToolBarSt = '<span style="margin-right: 1px;" class="btnImport"><div href="#" id="btnImport" onclick="onclickBtnImport()" class="btn btn-success txt-color-white ">' + $("#tblBtnImport").val() + '</div></span>';
            }
            if (settings.isExport !== undefined && settings.isExport === true) {
                var exportToolBarSt = '<span id="btnExport" class="toolbar-export-option btnExport"><div class="btn-group"><button class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="true">';
                exportToolBarSt += $("#labelExport").val() + ' <span class="caret"></span></button><ul class="dropdown-menu">'
                exportToolBarSt += '<li><a onclick="exportExcel(\'all\');" href="javascript:exportExcel(\'all\');"><i class="fa fa-file-excel-o"></i>' + $("#eventExportExcel").val() + '</a></li>';
                exportToolBarSt += '<li><a href="javascript:exportExcel(\'one\');"><i class="fa fa-file-excel-o"></i>' + $("#eventExportExcelCurrentPage").val() + '</a></li>';
                exportToolBarSt += '</ul></div></span>';
                if (settings.enableToolbar === undefined) {
                    toolBarSt = '<div class="DTTT_container" style="float:left;"> ' + importToolBarSt + exportToolBarSt + '<span class="tableHeaderBttonAdd"><div href="#" id="btnAddNew" onclick="onClickBtAdd()" class="btn btn-primary btnAdd">' + $("#tblBtnAdd").val()
                            + '</div></span><span class="tableHeaderBttonDellAll" style="margin-left:1px"><div id="btnDeleteAll"  disabled="disabled" onclick="doDeleteAll()" style="pointer-events: none;" class="btnExport btn btn-danger btnDeleteAll">' + $("#tblBtnDelete").val() + '</div></span>'
                            + '</div>';
                } else {
                    toolBarSt = '<div class="DTTT_container" style="float:left;margin-right:71px;"> ' + importToolBarSt + exportToolBarSt + '</div>';
                }
            } else if (settings.isImport !== undefined && settings.isImport === true) {
                if (settings.enableToolbar === undefined) {
                    toolBarSt = '<div class="DTTT_container" style="float:left;"> ' + importToolBarSt + '<span class="tableHeaderBttonAdd"><div href="#" id="btnAddNew" onclick="onClickBtAdd()" class="btn btn-primary btnAdd">' + $("#tblBtnAdd").val()
                            + '</div></span><span class="tableHeaderBttonDellAll" style="margin-left:1px"><div id="btnDeleteAll"  disabled="disabled" onclick="doDeleteAll()" style="pointer-events: none;" class="btnExport btn btn-danger btnDeleteAll">' + $("#tblBtnDelete").val() + '</div></span>'
                            + '</div>';
                } else {
                    toolBarSt = '<div class="DTTT_container" style="float:left;margin-right:71px;"> ' + importToolBarSt + '</div>';
                }
            }
            if (selector == "#datarole")
                toolBarSt = '<div class="DTTT_container">            <span style=""  class="tableHeaderBttonAdd"><div href="#" id="btnAddNew" onclick="onClickBtAdd()" class="btn btn-primary btnAdd">' + $("#tblBtnAdd").val()
                        + '</div></span><span class="tableHeaderBttonDellAll"  style="margin-left:1px"><div id="btnDeleteAll"  disabled="disabled" onclick="doDeleteAll()" style="pointer-events: none;" class="btnExport btn btn-danger btnDeleteAll">' + $("#tblBtnDelete").val()
                        + '</div></span><span style="margin-left: 3px; display: inline;" id="spanActive" ><div id="btnActive" onclick="doActiveUser()" class="btn btn-primary" disabled="disabled" style="pointer-events: none;">' + $("#tblBtnUnLock").val() + '</div></span><span style="margin-left: 3px; display: inline;" id="spanInActive"><div href="#" id="btnInActive" onclick="doInActiveUser()" style="pointer-events: none;" disabled="disabled" class="btn btn-warning">' + $("#tblBtnLock").val() + '</div></span>'
                        + '</div>';
            if ($('#table-responsive').find('div.dt-toolbar').length >= 1) {
                $('#table-responsive').find('div').first().replaceWith("<table id='" + (selector + "").replace("#", "") + "' style='width: 100%;'></table>");
            }
            first = 1;
            $("#btnDeleteAll").attr("disabled", true);
            $("#btnDeleteAll").css("pointer-events", "none");
            $("#btnActive").attr("disabled", true);
            $("#btnActive").css("pointer-events", "none");
            $("#btnInActive").attr("disabled", true);
            $("#btnInActive").css("pointer-events", "none");
            $("#btnDSVT").attr("disabled", true);
            $("#btnDSVT").css("pointer-events", "none");
            $("#selectorSelected").val(selector);
            $("#urlSelected").val(url);
            $.ajax({
                async: false,
                data: param,
                url: url,
                success: function (obj) {
                    var data = obj.data;
                    var html = "";
                    if (first == 1)
                    {
                        html = "<table id='" + (selector + "").replace("#", "") + "' class='table table-striped table-bordered table-hover smart-form has-tickbox' width='100%'>";
                        html += "<thead><tr>";
                        if (settings.enableCheckbox) {
                            html += "<th class='checkbox-td' width='20px' class='css_header'>";
                            html += "<label class=''>";
                            html += "<input onclick='objCommon.clickCheckboxAll(this.checked)' class='minimal1' id='checkAll' type='checkbox' name='checkboxAll'>";
                            html += "<i></i>";
                            html += "</label>";
                            html += "</th>";
                        }
                        for (var i = 0; i < columns.length; i++)
                        {
                            var output = JSON.stringify(columns[i]);
                            var json = JSON.parse(output);
                            if (!json["hidden"]) {
                                html += "<th   class='thtableresponsive " + json["clstitle"] + "' " + json["res"] + ">" + ele_util.escapeHTML(json["text"]) + "</th>";
                            } else {
                                html += "<th style='display: none;'></th>";
                                arrHide.push(i + 1);
                            }
                            if (json["edit"] == 1)
                                arrSort.push(i + 1);
                        }
                        html += "</tr></thead>";
                    }
                    var listId = [];
                    if (data != null) {
                        html += "<tbody>";
                        var length = data.length;
                        for (var i = 0; i < length; i++) {
                            var output = JSON.stringify(data[i]);
                            var json = JSON.parse(output);
                            var gid;
                            var valueCheck = "";
                            if (json['flag'] == true)
                                valueCheck = 'checked';
                            for (var j = 0; j < columns.length; j++) {
                                var outputColumn = JSON.stringify(columns[j]);
                                var jsonColumn = JSON.parse(outputColumn);
                                if (jsonColumn["edit"] != null) {
                                    gid = json[jsonColumn["datafield"]];
                                    break;
                                }
                            }
                            html += "<tr>";
                            if (settings.enableCheckbox) {
                                html += "<td class='checkbox-td'>";
                                html += "<label class=''>";
                                html += "<input class='minimal1' " + valueCheck + " id=" + gid + " value=" + gid + "  onclick='objCommon.clickCheckbox(this," + gid + ");' type='checkbox' name='checkbox-inline'>";
                                html += "<i></i>";
                                html += "</label>";
                                html += "</td>";
                            }
                            var numberStart = (pagenum - 1) * pagesize;
                            var curDate = new Date();
                            curDate.setDate(curDate.getDate() - 1);
                            var isChangeStatus = false;
                            for (var j = 0; j < columns.length; j++)
                            {
                                var isExpired = false;
                                var outputColumn = JSON.stringify(columns[j]);
                                var jsonColumn = JSON.parse(outputColumn);
                                if (!jsonColumn["hidden"]) {
                                    if (jsonColumn.datafield === "toolExpirationDate_1" || jsonColumn.datafield === "expirationDate_1" || jsonColumn.datafield === "expiredDateStr") {
                                        var valueTd = json[jsonColumn["datafield"]];
                                        if (valueTd) {
                                            var expiredDate = $.datepicker.parseDate('dd/mm/yy', valueTd);
                                            if (expiredDate < curDate) {
                                                isExpired = true;
                                                isChangeStatus = true;
                                                html += "<td class='expired-date " + jsonColumn["styleClass"] + "'>";
                                            }
                                        }
                                    }
                                    if (!isExpired) {
                                        html += "<td class='" + jsonColumn["styleClass"] + "'>";
                                    }
                                    if (jsonColumn["datafield"] === '') {
                                        html += (numberStart + i + 1);
                                    } else if (jsonColumn["edit"] != null) {
                                        if (selector == "#datatbldevice" && json["deptId"] != $("#MASTER_DEPT_ID").val() && $("#MASTER_DEPT_ID").val() > 0) {
                                            html += disabledCellRendererVT();
                                        } else if (selector == "#datatbldevice" && json["idSettlements"] != null && json["idSettlements"] > 0) {
                                            html += disabledChangeDeptIdCellRendererVT(json[jsonColumn["datafield"]]);
                                        } else {
                                            if (json["uploadBy"]) {
                                                html += editCellRenderer(json[jsonColumn["datafield"]], json["deptId"]);
                                            } else {
                                                html += editCellRenderer(json[jsonColumn["datafield"]]);
                                            }
                                        }
                                        listId.push(json[jsonColumn["datafield"]]);
                                    } else {
                                        var valueTd = json[jsonColumn["datafield"]];
                                        if (valueTd == null)
                                            valueTd = '';
                                        if (isChangeStatus && jsonColumn["datafield"] === "dvsName") {
                                            valueTd = $("#deviceFixFireExpiredStatus").val();
                                            html += "<font class='required-field'>" + ele_util.escapeHTML(valueTd) + "</font>";
                                        } else if (jsonColumn["datafield"] === "eventDate" || jsonColumn["datafield"] === "dateCreated") {
                                            html += formatDate(new Date(valueTd));
                                        } else if (jsonColumn["datafield"] === "isLock") {
                                            html += valueTd ? "Mở khóa" : "Khóa";
                                        } else {
                                            html += valueTd;
                                        }
                                    }
                                    html += "</td>";
                                } else {
                                    var column = JSON.parse(outputColumn);
                                    var colName = column["datafield"];
                                    html += "<td style='display:none'><input type='hidden' name='" + colName + "' value='" + ele_util.escapeHTML(json[jsonColumn["datafield"]]) + "'></td>";
                                }
                            }
                            html += "</tr>";
                        }
                        html += "</tbody>";
                    }
                    if (first == 1) {
                        html += "</table>";
                    }
                    html += "<input id='allValue' type='hidden' value='" + listId + "'>";
                    if (first == 1) {
                        $(selector).replaceWith(html);
                    } else {
                        $(selector + " tbody").html(html);
                    }
                    if ($(selector + " tbody tr").length == 0) {
                        if ($(selector).find("tbody").length == 0) {
                            $(selector).append("<tbody></tbody>");
                        }
                        $(selector + " tbody").html("<tr><td colspan='" + (columns.length + 1) + "'>Không tìm thấy bản ghi nào</td></tr>");
                    }
                    var total = obj.totalRecords;
                    pagenum = obj.curPage;
                    ele_datagrid.pagingTable(selector, pagenum, pagesize, total, url, changeOption);
                    first += 1;
                    ele_datagrid.loadScriptTable(selector, toolBarSt);
                }, error: function (err) {
                    console.log(err);
                }, complete: function (jqXHR, textStatus) {
//                    $('input[type="checkbox"].minimal1').iCheck({
//                        checkboxClass: 'icheckbox_minimal1-blue',
//                        radioClass: 'iradio_minimal1-blue'
//                    });
                }
            });
        }
    },
    buildGridCheckBox2Res: function (selector, url, datafields, columns, settings, param, toolBarSt) {
        $("#allValue2").remove();
        if ($('#table-responsive2').find('div.dt-toolbar').length >= 1) {
            $('#table-responsive2').find('div').first().replaceWith("<table id='" + (selector + "").replace("#", "") + "' style='width: 100%;'></table>");
        }
        first = 1;
        objCommon.listIds2 = [];
        ele_datagrid.datafields2 = datafields;
        ele_datagrid.columns2 = columns;
        ele_datagrid.toolBarSt2 = toolBarSt;
        $("#selectorSelected2").val(selector);
        $("#urlSelected2").val(url);
        $.ajax({
            async: false,
            data: param,
            url: url,
            success: function (obj) {
                var data = obj.data;
                var html = "";
                if (first == 1) {
                    html = "<table id='" + (selector + "").replace("#", "") + "' class='table table-striped table-bordered table-hover smart-form has-tickbox' width='100%'>";
                    html += "<thead><tr>";
                    html += "<th width='20px' class='css_header'>";

                    if (ele_datagrid.hasRadio != true) {
                        html += "<label class='checkbox'>";
                        html += "<input class='minimal1' onclick='objCommon.clickCheckboxAll2(this.checked, \"" + selector + "\")' id='checkAll2' type='checkbox' name='checkboxAll'>";
                        html += "<i></i>";
                        html += "</label>";
                    }

                    html += "</th>";
                    for (var i = 0; i < columns.length; i++)
                    {
                        var output = JSON.stringify(columns[i]);
                        var json = JSON.parse(output);
                        if (!json["hidden"]) {
                            html += "<th class='thtableresponsive " + json["clstitle"] + "' " + json["res"] + ">" + ele_util.escapeHTML(json["text"]) + "</th>";
                        } else {
                            html += "<th style='display: none;'></th>";
                            arrHide2.push(i + 1);
                        }
                        if (json["edit"] == 1)
                            arrSort2.push(i + 1);
                    }
                    html += "</tr></thead>";
                }
                var totalChecked = 0;
                var totalDisabled = 0;
                var listId = [];
                if (data != null) {
                    for (var i = 0; i < data.length; i++) {
                        var output = JSON.stringify(data[i]);
                        var json = JSON.parse(output);
                        var gid;
                        for (var j = 0; j < columns.length; j++) {
                            var outputColumn = JSON.stringify(columns[j]);
                            var jsonColumn = JSON.parse(outputColumn);
                            if (jsonColumn["edit"] != null) {
                                gid = json[jsonColumn["datafield"]];
                                break;
                            }
                        }
                        var valueCheck = "";
                        if (selector == "#datacontrol") {
                            if (json['flag'] === false) {
                                valueCheck = 'checked';
                                totalChecked++;
                            } else {
                                objCommon.listIds2.push(gid);
                            }
                        } else {
                            if (json['flag'] === true) {
                                valueCheck = 'checked';
                                objCommon.listIds2.push(gid);
                                totalChecked++;
                            }
                        }
                        var valueDisabled = "";
                        if (selector == "#dataDevices") {
                            if (json['flag'] === false && json['deviceStatus'] == '2') {
                                valueDisabled = 'disabled';
                                totalChecked++;
                                totalDisabled++;
                            }
                        }
                        html += "<td>";
                        html += "<label class='checkbox'>";
                        if (ele_datagrid.hasRadio == true) {
                            html += "<input " + valueCheck + " id=" + gid + " value=" + gid + "  type='radio' name='checkbox-inline2' " + valueDisabled + " onclick='objCommon.clickCheckboxCustom(this," + gid + ");'>";
                        } else {
                            html += "<input  class='minimal1' " + valueCheck + " id=" + gid + " value=" + gid + "  onclick='objCommon.clickCheckbox2(this," + gid + ",\"" + selector + "\");' type='checkbox' name='checkbox-inline2' " + valueDisabled + ">";
                        }
                        html += "<i></i>";
                        html += "</label>";
                        html += "</td>";
                        if (pagenum2 == null) {
                            pagenum2 = 1;
                        }
                        var numberStart = (pagenum2 - 1) * pagesize2;
                        for (var j = 0; j < columns.length; j++)
                        {
                            var outputColumn = JSON.stringify(columns[j]);
                            var jsonColumn = JSON.parse(outputColumn);
                            if (jsonColumn["edit"] != null) {
                                listId.push(json[jsonColumn["datafield"]]);
                            }
                            if (!jsonColumn["hidden"]) {
                                html += "<td class='" + jsonColumn["styleClass"] + "'>";
                                if (jsonColumn["datafield"] === '') {
                                    html += (numberStart + i + 1);
                                } else if (jsonColumn["edit"] != null) {
                                    if (selector == "#dataTool") {
                                        html += editCellRendererVTTool(json[jsonColumn["datafield"]]);
                                    } else {
                                        html += editCellRendererVT2(json[jsonColumn["datafield"]]);
                                    }
                                } else {
                                    var valueTd = json[jsonColumn["datafield"]];
                                    if (valueTd == null) {
                                        valueTd = '';
                                    } else if (jsonColumn["datafield"] === "eventDate" || jsonColumn["datafield"] === "dateCreated") {
                                        html += formatDate(new Date(valueTd));
                                    } else if (jsonColumn["datafield"] === "isLock") {
                                        html += valueTd ? "Mở khóa" : "Khóa";
                                    } else {
                                        html += valueTd;
                                    }
                                }
                                html += "</td>";
                            } else {
                                var column = JSON.parse(outputColumn);
                                var colName = column["datafield"];
                                html += "<td style='display:none'><input type='hidden' name='" + colName + "' value='" + ele_util.escapeHTML(json[jsonColumn["datafield"]]) + "'></td>";
                            }
                        }
                        html += "</tr>";
                    }
                }
                if (first == 1) {
                    html += "</table>";
                }
                html += "<input id='allValue2' type='hidden' value='" + listId + "'>";
                if (first == 1) {
                    $(selector).replaceWith(html);
                } else {
                    $(selector + " tbody").html(html);
                }
                if ($(selector + " tbody tr").length == 0) {
                    if ($(selector).find("tbody").length == 0) {
                        $(selector).append("<tbody></tbody>");
                    }
                    $(selector + " tbody").html("<tr><td colspan='" + (columns.length + 1) + "'>Không tìm thấy bản ghi nào</td></tr>");
                }
                if (totalChecked > 0 && totalChecked === data.length && totalDisabled != data.length) {
                    $("#checkAll2").attr("checked", true);
                }
                var total = obj.totalRecords;
                pagenum1 = obj.curPage;
                ele_datagrid.pagingTable(selector, pagenum2, pagesize2, total, url, '2');
                first += 1;
                ele_datagrid.loadScriptTable2(selector, toolBarSt);
            }, complete: function (jqXHR, textStatus) {
//                $('input[type="checkbox"].minimal1').iCheck({
//                    checkboxClass: 'icheckbox_minimal1-blue',
//                    radioClass: 'iradio_minimal1-blue'
//                });
            }
        });
    },
    loadScriptTable: function (selector, toolBarSt) {
        var pagefunction = function () {
            var responsiveHelper_datatable_tabletools = undefined;
            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };
            var html = initPageList("pageSelectLimit", pagesize);
            /* TABLETOOLS */
            $(selector).dataTable().fnDestroy();
            var otable =
                    $(selector).dataTable({
                "sDom": "<'dt-toolbar' <'#abcpageabc'>r <'float-left hidden-xs' C'>   <'col-xs-6 col-sm-4'<'#toolbarSearch'> >r   <'col-sm-5 col-xs-6 float-right'<'dsvaitro' <'#toolbarButton'> r  >>>   " +
                        "t",
                "pageLength": 100,
                "lengthChange": true,
                "oLanguage": {
                    "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
                },
                "oColVis": {
                    "aiExclude": arrHide,
                    "sSize": "auto",
                    //                    "buttonText": '<a href="javascript:void(0);" class="btn btn-warning"><i class="fa fa-gear fa-lg"></i> <i class="fa fa-caret-down"></i></a>',
                    "buttonText": '<a href="javascript:void(0);" class="btn btn-warning"><i class="fa fa-filter"></i></a>',
                    //                    "buttonText": "<div style='margin-left:20px; margin-right:20px;'>" + $("#tblBtnShow").val() + "</div>",
                    "sAlign": "left"
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': arrSort
                    }],
                "order": [[2, "asc"]],
                "oTableTools": {
                    "aButtons": [
                        {
                            "sExtends": "csv",
                            "sButtonText": " thay doi columd  ",
                        },
                        "xls",
                    ],
                    "sSwfPath": $("#pageContextRequestContextPath").val() + "/share/theme/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
                },
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_datatable_tabletools) {
                        responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($(selector), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    responsiveHelper_datatable_tabletools.respond();
                }
            });
            if (keySearch == null)
                keySearch = '';
            var placeholder = $("#placeholder").val();
            if (placeholder == null || placeholder == 'undefined') {
                placeholder == '';
            }
            $("div#toolbarButton").replaceWith(toolBarSt);
            $("div#toolbarSearch").replaceWith('<div id="datatable_tabletools_filter" class="dataTables_filter"><label ><span class="input-group-addon" ><i class="glyphicon glyphicon-search"></i></span><input type="search" id="inputSearch" value="' + ele_util.escapeHTML(keySearch) + '" onchange="doSearchInDB(this.value)" class="lbSearch form-control" title="' + placeholder + '" placeholder="' + placeholder + '" aria-controls="datatable_tabletools"> </label></div>');
            $("div#abcpageabc").replaceWith('<label class="float-left" style="padding:5px 4px">Số dòng</label>' + html);
            if (selector == "#datarole")
                doBuilToolBarRole();
            setTimeout('$("#inputSearch").focus().val($("#inputSearch").val())', 100);
            var strLength = $("#inputSearch").val().length * 2;
            $("#inputSearch").focus();
            $("#inputSearch")[0].setSelectionRange(strLength, strLength);
        };
        loadingBuildGridCheckBoxRes = false;
    },
    loadScriptTable1: function (selector, toolBarSt) {
        var pagefunction = function () {
            var responsiveHelper_datatable_tabletools = undefined;
            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };
            /* TABLETOOLS */
            var html = initPageList("pageSelectLimit1", pagesize1);
            var otable =
                    $(selector).dataTable({
                "sDom": "<'dt-toolbar' <'#abcpageabc'>r <'float-left hidden-xs' C'>   <'col-xs-6 col-sm-5'<'#toolbarSearch1'> >r   <'col-sm-4 col-xs-6 float-right'<'dsvaitro' <'#toolbarButton1'> r  >>>   " +
                        "t",
                "pageLength": 100,
                "lengthChange": true,
                "oLanguage": {
                    "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
                },
                "oColVis": {
                    "aiExclude": arrHide1,
                    "sSize": "auto",
                    //                    "buttonText": '<a href="javascript:void(0);" class="btn btn-warning"><i class="fa fa-gear fa-lg"></i> <i class="fa fa-caret-down"></i></a>',
                    "buttonText": '<a href="javascript:void(0);" class="btn btn-warning"><i class="fa fa-filter"></i></a>',
                    "sAlign": "left"
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': arrSort1
                    }],
                "order": [[2, "asc"]],
                "oTableTools": {
                    "aButtons": [
                        {
                            "sExtends": "csv",
                            "sButtonText": " thay doi columd  ",
                        },
                        "xls",
                    ],
                    "sSwfPath": $("#pageContextRequestContextPath").val() + "/share/theme/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
                },
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_datatable_tabletools) {
                        responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($(selector), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    responsiveHelper_datatable_tabletools.respond();
                }
            });
            if (keySearch1 == null) {
                keySearch1 = '';
            }
            var placeholder1 = $("#placeholder1").val();
            if (placeholder1 == null || placeholder1 == 'undefined') {
                placeholder1 == '';
            }
            if ($(selector).find("tbody tr td").attr("colspan")) {
                var thSize = $(selector).find("thead th").length;
                $(selector).find("tbody tr td").attr("colspan", thSize);
            }
            $("div#toolbarSearch1").replaceWith('<div id="datatable_tabletools_filter" class="dataTables_filter"><label ><span class="input-group-addon" ><i class="glyphicon glyphicon-search"></i></span><input type="search" id="inputSearch1"  value="' + vt_util.escapeHTML(keySearch1) + '" onchange="doSearch1InDB(this.value)" class="lbSearch form-control" placeholder="' + placeholder1 + '" aria-controls="datatable_tabletools"> </label></div>');
            $("div#toolbarButton1").replaceWith(toolBarSt);
            $("div#abcpageabc").replaceWith('<label class="float-left" style="padding:5px 4px">Số dòng</label>' + html);
            permission();
            setTimeout('$("#inputSearch1").focus().val($("#inputSearch1").val())', 100);
            var strLength = $("#inputSearch1").val().length * 2;
            $("#inputSearch1").focus();
            $("#inputSearch1")[0].setSelectionRange(strLength, strLength);
        };
    },
    loadScriptTable2: function (selector, toolBarSt) {
        var pagefunction = function () {
            var responsiveHelper_datatable_tabletools = undefined;
            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };
            var html = initPageList("pageSelectLimit2", pagesize2);
            /* TABLETOOLS */
            var otable =
                    $(selector).dataTable({
                "sDom": "<'dt-toolbar' <'#abcpageabc'>r <'float-left hidden-xs' C'>   <'col-xs-6 col-sm-5'<'#toolbarSearch2'> >r   <'col-sm-4 col-xs-6 float-right'<'dsvaitro' <'#toolbarButton2'> r  >>>   " +
                        "t",
                "pageLength": 100,
                "lengthChange": true,
                "oLanguage": {
                    "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
                },
                "oColVis": {
                    "aiExclude": arrHide2,
                    "sSize": "auto",
                    //                    "buttonText": '<a href="javascript:void(0);" class="btn btn-warning"><i class="fa fa-gear fa-lg"></i> <i class="fa fa-caret-down"></i></a>',
                    "buttonText": '<a href="javascript:void(0);" class="btn btn-warning"><i class="fa fa-filter"></i></a>',
                    "sAlign": "left"
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': arrSort2
                    }],
                "order": [[2, "asc"]],
                "oTableTools": {
                    "aButtons": [
                        {
                            "sExtends": "csv",
                            "sButtonText": " thay doi columd  ",
                        },
                        "xls",
                    ],
                    "sSwfPath": $("#pageContextRequestContextPath").val() + "/share/theme/js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
                },
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_datatable_tabletools) {
                        responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($(selector), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    responsiveHelper_datatable_tabletools.respond();
                }
            });
            if (keySearch2 == null) {
                keySearch2 = '';
            }
            var placeholder2 = $("#placeholder2").val();
            if (placeholder2 == null || placeholder2 == 'undefined') {
                placeholder2 == '';
            }
            if ($(selector).find("tbody tr td").attr("colspan")) {
                var thSize = $(selector).find("thead th").length;
                $(selector).find("tbody tr td").attr("colspan", thSize);
            }
            $("div#toolbarSearch2").replaceWith('<div id="datatable_tabletools_filter" class="dataTables_filter"><label ><span class="input-group-addon" ><i class="glyphicon glyphicon-search"></i></span><input type="search" id="inputSearch2"  value="' + vt_util.escapeHTML(keySearch2) + '" onchange="doSearch2InDB(this.value)" class="lbSearch form-control" placeholder="' + placeholder2 + '" aria-controls="datatable_tabletools"> </label></div>');
            $("div#toolbarButton2").replaceWith(toolBarSt);
            $("div#abcpageabc").replaceWith('<label class="float-left" style="padding:5px 4px">Số dòng</label>' + html);
            setTimeout('$("#inputSearch2").focus().val($("#inputSearch2").val())', 100);
            var strLength = $("#inputSearch2").val().length * 2;
            $("#inputSearch2").focus();
            $("#inputSearch2")[0].setSelectionRange(strLength, strLength);
        };
    },
    pageItemContent: function (pageIndex, selector, url, label, numTableClone)
    {
        return "<a href='#' onclick='return ele_datagrid.loadPage("
                + pageIndex
                + ",\"" + selector
                + "\",\"" + url
                + "\",\"" + numTableClone
                + "\");' aria-controls='dt_basic' data-dt-idx='2' tabindex='0'>" + label + "</a>";
    },
    //load lại trang khi bấm vào các nút
    loadPage: function (pageIndex, selector, url, numTableClone)
    {
        if (numTableClone === '1') {
            pagenum1 = pageIndex;
            ele_datagrid.loadPageAgain1(selector, url);
        } else if (numTableClone === '2') {
            pagenum2 = pageIndex;
            ele_datagrid.loadPageAgain2(selector, url);
        } else {
            // start: dong cac bang con trong man hinh Quan ly vai tro
            $("#listFunction").hide();
            $('#inputSearch1').val('');
            // end: dong cac bang con trong man hinh Quan ly vai tro
            pagenum = pageIndex;
            ele_datagrid.loadPageAgainRes(selector, url);
        }
        return false;
    },
    loadPageAgainRes: function (selector, url) {
        objCommon.listIds = [];
        var isSearch = $('#isSearch').val();
        if (isSearch === '1') {
            var key = $('#inputSearch').val();
            if (key !== null)
            {
                key = key.trim();
                keySearch = key;
            }
            ele_datagrid.buildGridCheckBoxRes(selector, url, datafields, columns, gridSetting, {keyWord: key, pagenum: pagenum, pagesize: pagesize}, '');
        } else {
            ele_datagrid.buildGridCheckBoxRes(selector, url, datafields, columns, gridSetting, {pagenum: pagenum, pagesize: pagesize}, '');
        }
    },
    loadPageAgain2: function (selector, url) {
        objCommon.listIds2 = [];
        var isSearch = $('#isSearch2').val();
        if (isSearch === '1') {
            var key = $('#inputSearch2').val();
            if (key != null) {
                key = key.trim();
                keySearch2 = key;
            }
            ele_datagrid.buildGridCheckBox2Res(selector, url, datafields2, columns2, gridSetting2, {keyWord: key, pagenum: pagenum2, pagesize: pagesize2}, ele_datagrid.toolBarSt2);
        } else {
            ele_datagrid.buildGridCheckBox2Res(selector, url, datafields2, columns2, gridSetting2, {pagenum: pagenum2, pagesize: pagesize2}, ele_datagrid.toolBarSt2);
        }
    },
//    loadPageAgain1: function (selector, url) {
//        objCommon.listIds2 = [];
//        var isSearch = $('#isSearch2').val();
//        if (isSearch === '1') {
//            var key = $('#inputSearch2').val();
//            if (key != null) {
//                key = key.trim();
//                keySearch2 = key;
//            }
//            //            key = vt_common.encodeParam(key);
//            ele_datagrid.buildGridCheckBox1Res(selector, url, ele_datagrid.datafields2, ele_datagrid.columns2, gridSetting, {keyWord: key, pagenum: pagenum2, pagesize: pagesize2}, ele_datagrid.toolBarSt2);
//        } else {
//            ele_datagrid.buildGridCheckBox1Res(selector, url, ele_datagrid.datafields2, ele_datagrid.columns2, gridSetting, {pagenum: pagenum2, pagesize: pagesize2}, ele_datagrid.toolBarSt2);
//        }
//    },
//            //load lại trang khi bấm vào các nút
//            onClickDisableButton: function ()
//            {
//                return false;
//            },
    //Hàm phân trang
    pagingTable: function (selector, pageIndexCurrent, limit, total, url, numTableClone) {
        if (!numTableClone) {
            numTableClone = 0;
        }
        var pageNumber = 0;
        if (total <= limit)
            pageNumber = 1;
        else if (total % limit != 0)
        {
            pageNumber = Math.floor(total / limit) + 1;
        } else
            pageNumber = Math.floor(total / limit);
        var html = "";
        if (pageIndexCurrent == 1 || total == 0)
        {
            html += "<li class='paginate_button previous' id='pagePreviousButton'>";
            html += "<a href='javascript:void(0)' aria-controls='dt_basic' data-dt-idx='0' onclick='return ele_datagrid.onClickDisableButton();' tabindex='0'>" + $("#pagingLabelPrevious").val() + "</a>";
        } else
        {
            html += "<li class='paginate_button previous' id='pagePreviousButton'>";
            html += ele_datagrid.pageItemContent((pageIndexCurrent - 1), selector, url, $("#pagingLabelPrevious").val(), numTableClone);
        }
        html += "</li>";
        if (pageNumber <= 7)
        {
            for (var i = 1; i <= pageNumber; i++)
            {
                if (i - parseInt(pageIndexCurrent) == 0)
                {
                    html += "<li class='paginate_button active'>";
                } else
                    html += "<li class='paginate_button'>";
                html += ele_datagrid.pageItemContent(i, selector, url, i, numTableClone);
                html += "</li>";
            }
        } else
        {
            if (pageIndexCurrent <= 4)
            {
                for (var i = 1; i <= 5; i++)
                {
                    if (i == pageIndexCurrent)
                        html += "<li class='paginate_button active'>";
                    else
                        html += "<li class='paginate_button'>";
                    html += ele_datagrid.pageItemContent(i, selector, url, i, numTableClone);
                    html += "</li>";
                }
                html += "<li class='paginate_button disabled' id='dt_basic_ellipsis'><a href='javascript:void(0)' aria-controls='dt_basic' data-dt-idx='6' tabindex='0'>…</a></li>";
                html += "<li class='paginate_button'>";
                html += ele_datagrid.pageItemContent(pageNumber, selector, url, pageNumber, numTableClone);
                html += "</li>";
            } else if (pageIndexCurrent > 4 && pageIndexCurrent <= pageNumber - 3)
            {
                html += "<li class='paginate_button'>";
                html += ele_datagrid.pageItemContent(1, selector, url, 1, numTableClone);
                html += "</li>";
                html += "<li class='paginate_button disabled' id='dt_basic_ellipsis'><a href='javascript:void(0)' aria-controls='dt_basic' data-dt-idx='6' tabindex='0'>…</a></li>";
                for (var i = pageIndexCurrent - 1; i <= pageIndexCurrent + 1; i++)
                {
                    if (i == pageIndexCurrent)
                        html += "<li class='paginate_button active'>";
                    else
                        html += "<li class='paginate_button'>";
                    html += ele_datagrid.pageItemContent(i, selector, url, i, numTableClone);
                    html += "</li>";
                }
                html += "<li class='paginate_button disabled' id='dt_basic_ellipsis'><a href='javascript:void(0)' aria-controls='dt_basic' data-dt-idx='6' tabindex='0'>…</a></li>";
                html += "<li class='paginate_button'>";
                html += ele_datagrid.pageItemContent(pageNumber, selector, url, pageNumber, numTableClone);
                html += "</li>";
            } else
            {
                html += "<li class='paginate_button'>";
                html += ele_datagrid.pageItemContent(1, selector, url, 1, numTableClone);
                html += "</li>";
                html += "<li class='paginate_button disabled' id='dt_basic_ellipsis'><a href='javascript:void(0)' aria-controls='dt_basic' data-dt-idx='6' tabindex='0'>…</a></li>";
                for (var i = pageNumber - 4; i <= pageNumber; i++)
                {
                    if (i == pageIndexCurrent)
                        html += "<li class='paginate_button active'>";
                    else
                        html += "<li class='paginate_button'>";
                    html += ele_datagrid.pageItemContent(i, selector, url, i, numTableClone);
                    html += "</li>";
                }
            }
        }
        if (pageIndexCurrent == pageNumber || total == 0)
        {
            html += "<li class='paginate_button next' id='pageNextButton'>";
            html += "<a href='#' aria-controls='dt_basic' onclick='return ele_datagrid.onClickDisableButton();' data-dt-idx='0' tabindex='0'>" + $("#pagingLabelNext").val() + "</a>";
        } else
        {
            html += "<li class='paginate_button next' id='pageNextButton'>"
            html += ele_datagrid.pageItemContent((pageIndexCurrent + 1), selector, url, $("#pagingLabelNext").val(), numTableClone);
        }
        html += "</li>";
        if (numTableClone === '1') {
            var indexEnd = pageIndexCurrent * pagesize1;
            if (pageIndexCurrent * pagesize1 > total) {
                indexEnd = total;
            }
            var indexStart = (pageIndexCurrent - 1) * pagesize1 + 1;
            if (indexStart > indexEnd) {
                indexStart = indexEnd;
            }
            var infor = $("#pagingLabelDisplay").val() + ' (' + indexStart + '-' + indexEnd + ')/' + total + ' ' + $("#pagingLabelNumberElement").val();
            $("#datatable_tabletools_info1").html(infor);
            $("#pagingtable1").html(html);
            $("#pageSelectLimit1").val(pagesize1);
        } else if (numTableClone === '2') {
            var indexEnd = pageIndexCurrent * pagesize2;
            if (pageIndexCurrent * pagesize2 > total) {
                indexEnd = total;
            }
            var indexStart = (pageIndexCurrent - 1) * pagesize2 + 1;
            if (indexStart > indexEnd) {
                indexStart = indexEnd;
            }
            var infor = $("#pagingLabelDisplay").val() + ' (' + indexStart + '-' + indexEnd + ')/' + total + ' ' + $("#pagingLabelNumberElement").val();
            $("#datatable_tabletools_info2").html(infor);
            $("#pagingtable2").html(html);
            $("#pageSelectLimit2").val(pagesize2);
        } else {
            var indexEnd = pageIndexCurrent * pagesize;
            if (pageIndexCurrent * pagesize > total)
                indexEnd = total;
            var indexStart = (pageIndexCurrent - 1) * pagesize + 1;
            if (indexStart > indexEnd)
                indexStart = indexEnd;
            var infor = $("#pagingLabelDisplay").val() + ' (' + indexStart + '-' + indexEnd + ')/' + total + ' ' + $("#pagingLabelNumberElement").val();
            $("#datatable_tabletools_info").html(infor);
            $("#pagingtable").html(html);
            $("#pageSelectLimit").val(pagesize);
        }
        return false;
    },
    gridOnReady: function (selector, onPageChangedFunc) {
        var paginginformation = $(selector).jqxGrid('getpaginginformation');
        var tiltePrevious = $("#gridLocalizepagerpreviousbuttonstring").val();
        var tilteNext = $("#gridLocalizepagernextbuttonstring").val();
        if (paginginformation.pagenum == 0) {
            $(selector + " div[title='" + tiltePrevious + "']").jqxButton({disabled: true});
        }
        if (paginginformation.pagescount == 1) {
            $(selector + " div[title='" + tilteNext + "']").jqxButton({disabled: true});
        }
        $(selector).on("pagechanged", function (event) {
            var paginginformation = $(selector).jqxGrid('getpaginginformation');
            var pagescount = paginginformation.pagescount;
            var currentPage = event.args.pagenum;
            setTimeout(function () {
                if (currentPage == 0) {
                    $(selector + " div[title='" + tiltePrevious + "']").jqxButton({disabled: true});
                } else {
                    $(selector + " div[title='" + tiltePrevious + "']").jqxButton({disabled: false});
                }
                if (currentPage == (pagescount - 1)) {
                    $(selector + " div[title='" + tilteNext + "']").jqxButton({disabled: true});
                } else {
                    $(selector + " div[title='" + tilteNext + "']").jqxButton({disabled: false});
                }
            }, 500);
            if (onPageChangedFunc != null) {
                setTimeout(function () {
                    onPageChangedFunc.apply();
                }, 1000);
            }
        });
    },
    setGridLocalization: function (selector) {
        var localizationObj = {
            pagergotopagestring: $("#gridLocalizepagergotopagestring").val(),
            pagershowrowsstring: $("#gridLocalizepagershowrowsstring").val(),
            pagerrangestring: $("#gridLocalizepagerrangestring").val(),
            pagernextbuttonstring: $("#gridLocalizepagernextbuttonstring").val(),
            pagerpreviousbuttonstring: $("#gridLocalizepagerpreviousbuttonstring").val(),
            emptydatastring: $("#gridLocalizeemptydatastring").val(),
            loadtext: $("#gridLocalizeloadtext").val()
        };
        $(selector).jqxGrid('localizestrings', localizationObj);
    },
    //Ham lay doi tuong row tra ve duoi dang json dua vao rowIndxPage tinh tu 0 - ..10000   
    getRowByIndx: function (selector, rowIndxPage) {
        return $(selector).jqxGrid('getrowdata', rowIndxPage);
    },
    //Ham lay doi tuong row tra ve duoi dang json dua vao rowIndxPage tinh tu 0- ..10000   
    getRowsSelection: function (selector) {
        var arrRowIndSelected = $(selector).jqxGrid('getselectedrowindexes');
        var result = new Array();
        for (i = 0; i < arrRowIndSelected.length; i++) {
            result.push(ele_datagrid.getRowByIndx(selector, arrRowIndSelected[i]));
        }
        return result;
    },
    //Ham lay doi tuong row tra ve duoi dang json  
    getLastRowSelected: function (selector) {
        var rowIndxPage = $(selector).jqxGrid('getselectedrowindex');
        return ele_datagrid.getRowByIndx(selector, rowIndxPage);
    },
    //Ham kiem tra xem co row nao duoc chon khong, tra ve so luong hang duoc chon.Neu khong co hang nao 
    //duoc chon thi tra ve 0.
    getTotalRowSelected: function (selector) {
        return $(selector).jqxGrid('getselectedrowindexes').length;
    },
    getRowPerPage: function (selector) {
        return $(selector).jqxGrid('pagesize');
    },
    //Ham tra lai tat ca cac input trong form duoi dang param tren URL
    //VD: a=fdas&b=gfsg&...
    getFormAsParamUrl: function (frmSelector) {
        return $(frmSelector).serialize();
    },
    //Ham tra lai tat ca cac input trong form duoi dang param tren URL
    //VD: a=fdas&b=gfsg&...
    getFormAsObj: function (frmSelector) {
        var arrObj = $(frmSelector).serializeArray();
        var param = {};
        for (i = 0; i < arrObj.length; i++) {
            param[arrObj[i].name] = arrObj[i].value;
        }
        return param; //tra lai doi tuong json chua cac input theo key:value
    },
    addConfigGrid: function (gridSelector, configSelector) {
        var container = $(configSelector);
        var table = $('<table/>').addClass('table');
        //
        var thRow = $('<tr></tr>');
        var thLabel = $('<th/>').text('Cột');
        var thChkBoxFrz = $('<th/>').text('Frezee');
        var thchkBoxSho = $('<th/>').text('Ẩn hiện');
        thRow.append(thLabel).append(thChkBoxFrz).append(thchkBoxSho);
        table.append(thRow);
        //
        var cols = $(gridSelector).pqGrid("option", "colModel");
        var chkBoxFrzArr = [];
        for (var i = 0; i < cols.length; i++) {
            var col = cols[i];
            var label = $('<label />', {for : 'col_' + col.dataIndx});
            label.html(col.title);
            var chkBoxFrz = $('<input />', {type: 'checkbox', id: 'frez_' + col.dataIndx, value: i});
            var chkBoxSho = $('<input />', {type: 'checkbox', id: 'show_' + col.dataIndx, value: i});
            chkBoxFrzArr.push(chkBoxFrz);
            chkBoxFrz.click(function () {
                if ($(this).is(":checked"))
                {
                    chkBoxFrzArr.forEach(function (cbk) {
                        cbk.prop('checked', false);
                    });
                    $(this).prop('checked', true);
                    $(gridSelector).pqGrid("option", "freezeCols", $(this).val());
                } else {
                    $(gridSelector).pqGrid("option", "freezeCols", 0);
                }
                $(gridSelector).pqGrid("refresh");
            });
            chkBoxSho.click(function () {
                var colM = $(gridSelector).pqGrid("option", "colModel");
                var idx = $(this).val();
                if ($(this).is(":checked"))
                {
                    // hide col
                    colM[idx].hidden = true;
                } else {
                    // show col
                    colM[idx].hidden = false;
                }
                $(gridSelector).pqGrid("option", "colModel", colM);
            });
            // 
            var row = $('<tr></tr>');
            var tdName = $('<td></td>').append(label);
            var tdChkBoxFrz = $('<td></td>').append(chkBoxFrz);
            var tdChkBoxSho = $('<td></td>').append(chkBoxSho);
            row.append(tdName).append(tdChkBoxFrz).append(tdChkBoxSho);
            table.append(row);
        }
        table.appendTo(container);
        // 
        var config = $(configSelector).dialog({
            autoOpen: false,
            height: 300,
            width: 700,
            modal: true,
            buttons: {
                "OK": function () {
                    config.dialog("close");
                }
            },
            close: function () {
            }
        });
        return config;
    },
    // create button on toolbar
    createButton: function (name) {
        var args = arguments;
        var _name = name;
        var _onClick = function (evt) {

        };
        var _icon = 'ui-icon-document';
        if (args.length >= 2) {
            if (args[1] !== null)
            {
                _onClick = args[1];
            }
            if (args.length >= 3) {
                _icon = args[2];
            }
        }
        var btn = {
            type: 'button',
            label: _name,
            icon: _icon,
            listeners: [{
                    "click": function (evt) {
                        _onClick(evt);
                    }
                }]
        };
        return btn;
    }
};
// end draw table
function initPageList(selector, pageSize) {
    var html = "<select onchange='pageSelectChange(\"" + selector + "\",this)' id='" + selector + "' style='margin-right:5px;padding: 5px 4px;border-radius: 3px;' class='ui-pg-selbox float-left' role='listbox'>";
    switch (pageSize) {
        case "10":
            html += '<option role="option" value="10" selected="selected">10</option>';
            html += '<option role="option" value="20">20</option>';
            html += '<option role="option" value="30">30</option>';
            html += '<option role="option" value="50">50</option>';
            html += '<option role="option" value="100">100</option>';
            break;
        case "20":
            html += '<option role="option" value="10">10</option>';
            html += '<option role="option" value="20" selected="selected">20</option>';
            html += '<option role="option" value="30">30</option>';
            html += '<option role="option" value="50">50</option>';
            html += '<option role="option" value="100">100</option>';
            break;
        case "30":
            html += '<option role="option" value="10">10</option>';
            html += '<option role="option" value="20">20</option>';
            html += '<option role="option" value="30" selected="selected">30</option>';
            html += '<option role="option" value="50">50</option>';
            html += '<option role="option" value="100">100</option>';
            break;
        case "50":
            html += '<option role="option" value="10">10</option>';
            html += '<option role="option" value="20">20</option>';
            html += '<option role="option" value="30">30</option>';
            html += '<option role="option" value="50" selected="selected">50</option>';
            html += '<option role="option" value="100">100</option>';
            break;
        case "100":
            html += '<option role="option" value="10">10</option>';
            html += '<option role="option" value="20">20</option>';
            html += '<option role="option" value="30">30</option>';
            html += '<option role="option" value="50">50</option>';
            html += '<option role="option" value="100" selected="selected">100</option>';
            break;
        default :
            html += '<option role="option" value="10" selected="selected">10</option>';
            html += '<option role="option" value="20">20</option>';
            html += '<option role="option" value="30">30</option>';
            html += '<option role="option" value="50">50</option>';
            html += '<option role="option" value="100">100</option>';
            break;
    }
    html += '</select>';
    return html;
}
var ele_import = {
    loadError: function (result) {
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
//                        arrError.push(item[i].lstError[j].fieldName);
                        htmlChild += "<span class='glyphicon glyphicon-exclamation-sign txt-color-red' aria-hidden='true'></span>            ";
                        htmlChild += ele_util.escapeHTML(item[i].lstError[j].error) + "<br/><br/>";
                    } else {
                        arrError.push(item[i].lstError[j].fieldName);
                    }
                }
                html += '<td>' + htmlChild + '</td>';
            }
            html += '</tr>';
            $("label[for='error']").html(parseInt(result.totalFail));
            $("label[for='success']").html(parseInt(result.totalSuccess));
            $("label[for='totalRecord']").html(parseInt(result.totalSuccess) + parseInt(result.totalFail));
            $("#listDataImportErrorBody").html(html);
        }
    }
};
function dateValidate(txtDate) {
    var currVal = txtDate;
    if (currVal == '')
        return true;
    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
    var dtArray = currVal.match(rxDatePattern); // is format OK?
    if (dtArray == null)
        return false;
    var arrDate = txtDate.split('/');
    var dtDay = arrDate[0];
    var dtMonth = arrDate[1];
    var dtYear = arrDate[2];
    if (dtMonth < 1 || dtMonth > 12)
        return false;
    else if (dtDay < 1 || dtDay > 31)
        return false;
    else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31)
        return false;
    else if (dtMonth == 2)
    {
        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
        if (dtDay > 29 || (dtDay == 29 && !isleap))
            return false;
    }
    return true;
}

function dateFormatValid(txtDate) {
    var currVal = txtDate;
    if (currVal == '')
        return true;
    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
    var dtArray = currVal.match(rxDatePattern); // is format OK?
    if (dtArray == null)
        return false;
    return true;
}
var ele_form = {
    clearTextInput: function (selector) {
        $.each($(selector).find('input[type=text],input[type=password],textarea'), function (index, item) {
            $(item).val('');
        });
    },
    clearError: function () {
        $('.note-error').empty();
    },
    reset: function (selector) {
        selector.each(function () {
            this.reset();
        });
    },
    validate1: function (selector, alerter, rule) {
        rule["errorPlacement"] = function (error, element) {

        };
        rule["invalidHandler"] = function (event, validator) {
            if (validator.numberOfInvalids() > 0) {
                var id = $(validator.errorList[0].element).attr('id');
                $('#' + id).focus();
                for (var i = 0; i < validator.errorList.length; i++) {
                    $("#" + $(validator.errorList[i].element).attr('id') + "_error").text(validator.errorList[i].message);
                }
            }
        };
        $(selector).validate(rule);
        return $(selector).valid();
    },
    validate: function (selector, rule) {
        rule["errorPlacement"] = function (error, element) {
            // show message
            vt_alert.showAlert(error.text());
            // focus on error element
            element.focus();
        };
        $(selector).validate(rule);
        return $(selector).valid();
    },
    ajax: function (type, url) {
        var _type, /*type = POST, GET, DELETE */
                _url, /* */
                _param, /*data param use for req*/
                _formId,
                _area,
                _onDone, _onFail, _onAlway, _async;
        var args = arguments;
        _param = null;
        _formId = null;
        _type = args[0];
        _url = args[1];
        _onAlway = function () {};
        _onFail = function (jqXHR, textStatus, errorThrown) {

        };
        var _onDefault;
        if (args.length >= 3) {
            _param = args[2];
            if (args.length >= 4) {
                _formId = args[3];
                if (args.length >= 5) {
                    _area = args[4];
                }
                if (args.length >= 6) {
                    _onDone = args[5];
                    if (args.length >= 7) {
                        _onFail = args[6];
                        if (args.length >= 8) {
                            _onAlway = args[7];
                            if (args.length >= 9) {
                                _async = args[8];
                            }
                        }
                    }
                }
            }
        }
        if (_formId) {
            var $inputs = $(_formId).find("input, select, button, textarea");
            $inputs.each(function () {
                if ($(this).val() != null) {
                    this.value = $(this).val().trim();
                }
            });
            _param = $(_formId).serialize();
            $inputs.prop("disabled", true);
            _onDefault = function () {
                try {
                    var $inputs = $(_formId).find("input, select, button, textarea");
                    $inputs.prop("disabled", false);
                } catch (e)
                {
                }
            }
        }

        $.ajax({
            traditional: true,
            url: "token.json",
            dataType: "text",
            type: "GET"
        }).success(function (result) {
            $.ajax({
                traditional: true,
                url: url,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("X-XSRF-TOKEN", result);
                },
                data: _param,
                dataType: "text",
                type: type
            }).success(function (result) {
                var jsonData = null;
                try {
                    jsonData = jQuery.parseJSON(result);
                } catch (e) {
                    console.log('Error when parsing ajax request to json. result: ' + result);
                }

                if (jsonData != null) {
                    if (jsonData.sessionTimeout) {
                        location.reload();
                    } else if (jsonData.noPermission) {
                        vt_alert.showAlert($("#msgNoPermission").val());
                    } else {
                        _onDone(jsonData);
                    }
                } else {
                    _onDone(jsonData);
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                // show error message
                _onFail(jqXHR, textStatus, errorThrown);
            }).always(function () {
                _onAlway();
                if (_formId) {
                    _onDefault();
                }
            });
        }).fail(function (jqXHR, textStatus, errorThrown) {
            // show error message
            _onFail(jqXHR, textStatus, errorThrown);
        }).always(function () {
            _onAlway();
            if (_formId) {
                _onDefault();
            }
        });
    },
    get: function (url, param, onDone) {
        $.ajax({
            traditional: true,
            url: url,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("X-XSRF-TOKEN", $('input[name=_csrf]').val());
            },
            data: param,
            dataType: "json",
            success: function (result) {
                onDone(result);
            },
            fail: function (jqXHR, textStatus, errorThrown) {
            }
        });
    },
    updateArea: function (selector, url, data, callback) {
        var _data = {};
        if (arguments.length > 2 && data !== null) {
            _data = data;
        }
        $.ajax({
            traditional: true,
            url: url,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("X-XSRF-TOKEN", $('input[name=_csrf]').val());
            },
            data: _data,
            dataType: "html",
            success: function (result) {
                $(selector).html(result);
                if (arguments.length > 3) {
                    callback(result);
                }
            },
            fail: function (jqXHR, textStatus, errorThrown) {
            }
        });
    },
    convertList: function (arr, dest, parent_key)
    {
        try {
            for (var i = 0; i < arr.length; i++) {
                var obj = arr[i];
                var att = parent_key + '[' + i + '].';
                for (var key in obj) {
                    var att = parent_key + '[' + i + '].' + key;
                    if (jQuery.isArray(obj[key])) {
                        dest = vt_form.convertList(obj[key], dest, att);
                    } else {
                        dest[att] = obj[key];
                    }
                }
            }
        } catch (e)
        {
        }
        return dest;
    },
    convertObjToPostForm: function (sourc) {
        var dest = {};
        for (var key in sourc) {
            if (jQuery.isArray(sourc[key])) {
                dest = vt_form.convertList(sourc[key], dest, key);
            } else {
                dest[key] = sourc[key];
            }
        }
        return $.param(dest);
    }
};
function formatDate(date) {
//    var hours = date.getHours();
//    var minutes = date.getMinutes();
//    var ampm = hours >= 12 ? 'pm' : 'am';
//    hours = hours % 12;
//    hours = hours ? hours : 12; // the hour '0' should be '12'
//    minutes = minutes < 10 ? '0' + minutes : minutes;
//    var strTime = hours + ':' + minutes + ' ' + ampm;
    var month = date.getMonth() + 1;
    var dateStr = "";
    var day = date.getDate();
    if (day < 10) {
        dateStr = "0" + day;
    } else {
        dateStr = day + "";
    }
    if (month < 10) {
        dateStr += "/0" + month;
    } else {
        dateStr += "/" + month;
    }
    return dateStr + "/" + date.getFullYear();
}

var ele_combobox = {
    buildCombobox: function (selector, url, selectedValue, displayMember, valueMember, firstValue) {
        $.ajax({
            async: false,
            url: url,
            success: function (data) {
                var html = "<div id='" + selector + "'>";
                html += "<select id='" + selector + "Combobox' name='" + selector + "'>";
                if (firstValue != null) {
                    html += "<option value='-1'>" + firstValue + "</option>";
                }
                if ($.isArray(data)) {
                    for (var i = 0; i < data.length; i++) {
                        var output = JSON.stringify(data[i]);
                        var json = JSON.parse(output);
                        if (selectedValue == json[valueMember]) {
                            html += "<option  selected value='" + json[valueMember] + "'>";
                        } else {
                            html += "<option value='" + json[valueMember] + "'>";
                        }
                        html += json[displayMember];
                        html += "</option>";
                    }
                }
                html += "</select>";
                html += "</div>";
                $("#" + selector).replaceWith(html);
                $('#' + selector + 'Combobox').select2({width: "100%"});
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    },
    buildComboboxMultiple: function (selector, url, selectedValue, displayMember, valueMember, firstValue, deptId) {
        $.ajax({
            async: false,
            data: {deptid: deptId},
            url: url,
            success: function (data) {
                var html = "<div id='" + selector + "'>";
                html += "<select id='" + selector + "Combobox' multiple>";
                if (firstValue != null) {
                    html += "<option value='-1'>" + firstValue + "</option>";
                }
                if ($.isArray(data)) {
                    for (var i = 0; i < data.length; i++) {
                        var output = JSON.stringify(data[i]);
                        var json = JSON.parse(output);
                        if (selectedValue == json[valueMember]) {
                            html += "<option  selected value='" + json[valueMember] + "'>";
                        } else {
                            html += "<option value='" + json[valueMember] + "'>";
                        }
                        html += json[displayMember];
                        html += "</option>";
                    }
                }
                html += "</select>";
                html += "</div>";
                $("#" + selector).replaceWith(html);
                $('#' + selector + 'Combobox').select2({width: "100%"});
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
};
function showNotif(msg, isError) {
    if (isError) {
        notif({
            type: 'error',
            position: 'bottom',
            msg: msg
        });
    } else {
        notif({
            type: 'success',
            position: 'bottom',
            msg: msg
        });
    }
}
function isFileValid(selector, extension, maxSize) {
    try {
        var fsize = $(selector)[0].files[0].size;
        var ftype = $(selector)[0].files[0].type;
        if (fsize <= maxSize && extension.indexOf(ftype) >= 0) {
            return true;
        }
        return false;
    } catch (err) {
        return false;
    }
}
function notifSuccess(msg) {
    notif({
        type: 'success',
        position: 'bottom',
        msg: msg
    });
}
function notifError(msg) {
    notif({
        type: 'error',
        position: 'bottom',
        msg: msg
    });
}