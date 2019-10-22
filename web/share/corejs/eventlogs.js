function doSearch() {
    pagenum = 1;
    var fromDate = $("#fromDate").val().trim();
    var toDate = $("#toDate").val().trim();
    ele_datagrid.loadPageAgainRes("#gridEventLogs", "getEventLogs.json?fromDate=" + fromDate + "&toDate=" + toDate);
}
var datafields = [
    {name: 'eventId', type: 'number'},
    {name: 'action', type: 'text'},
    {name: 'status', type: 'number'},
    {name: 'ip', type: 'text'},
    {name: 'userName', type: 'text'},
    {name: 'description', type: 'text'},
    {name: 'eventDate', type: 'date'}
];
var columns = [
    {text: "Người thao tác", datafield: 'fullname', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Thao tác", datafield: 'action', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Mô tả", datafield: 'description', filtertype: 'input', res: "data-hide='phone'"},
    {text: "IP", datafield: 'ip', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Ngày thao tác", styleClass: "text-center", datafield: 'eventDate', filtertype: 'input', res: "data-hide='phone'"},
    {text: "Trạng thái", datafield: 'status', filtertype: 'input', res: "data-hide='phone'"},
//    {text: "", datafield: 'eventId', edit: 1, sortable: false, clstitle: 'tlb_class_center'}
];
var gridSetting = {
    sortable: false,
    virtualmode: true,
    enableToolbar: true,
    enableDelete: true,
    enableSearch: true,
    enableCheckbox: false
};
$(".sidebar-menu li").removeClass("active");
$("#EventLogs").addClass("active");
editCellRenderer = function (gid) {
    return '<div style="text-align: center" id="staffOption_' + gid + '">'
            + '    <a data-toggle="tooltip" data-placement="top" title="Cập nhật danh mục" class="tooltipCus" onclick="onClickBtEdit(\'' + gid + '\');" href="javascript:void(0)">'
            + '        <img src="../share/image/edit.png" class="grid-icon"/>'
            + '    </a><a data-toggle="tooltip" data-placement="top" title="Xóa danh mục" class="tooltipCus" href="javascript:void(0)" onclick="deleteDivision(\'' + gid + '\')">'
            + '        <img src="../share/image/delete_1.png" class="grid-icon"/></a>'
            + '</div>';
};

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