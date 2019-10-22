<%-- 
    Document   : tblfiles
    Created on : Oct 2, 2018, 9:17:31 PM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/share/css/imageuploadify.min.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/share/js/imageuploadify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/share/ContextMenu/jquery.contextMenu.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/share/ContextMenu/jquery.contextMenu.css" rel="stylesheet" type="text/css"/>
<section class="content-header">
    <h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
            <li class="active">Thư viện hình ảnh</li>
        </ol>
    </h1>
</section>
<section class="content">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default pb-filemng-navbar">
                <div class="container-fluid"  oncontextmenu="return false;">
                    <!-- Navigation -->
                    <div class="navbar-header">
                        <button type="button" class="pull-left navbar-toggle collapsed treeview-toggle-btn" data-toggle="collapse" data-target="#treeview-toggle" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#options" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="fa fa-gears"></span>
                        </button>

                        <!-- Search button -->
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#pb-filemng-navigation" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="fa fa-share"></span>
                        </button>
                    </div>

                    <ul class="collapse navbar-collapse nav navbar-nav navbar-right" id="options">
                        <li><a title="Thêm mới hình ảnh" href="javascript:void(0)" id="btnCreateFile"><img src="${pageContext.request.contextPath}/share/image/if_18.Pictures-Day_290132.png" alt=""/></a></li>
                        <li><a title="Thêm mới thư mục" href="javascript:void(0)" id="btnCreateFolder"><img src="${pageContext.request.contextPath}/share/image/folder-add-32.png" alt=""/></a></li>
                    </ul>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="pb-filemng-navigation">
                        <ul class="nav navbar-nav">
                            <li><a href="#"><span class="fa fa-chevron-left fa-lg"></span></a></li>
                            <li><a href="#"><span class="fa fa-chevron-right fa-lg"></span></a></li>
                            <li class="pb-filemng-active"><a href="#"><span class="fa fa-file fa-lg"></span></a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->

                </div>
                <!-- /.container-fluid -->
            </nav>
            <div class="panel panel-default">
                <div class="panel-body pb-filemng-panel-body">
                    <div class="row">
                        <div class="col-sm-3 col-md-3 pb-filemng-template-treeview">
                            <div class="collapse navbar-collapse" id="treeview-toggle">
                                <div id="treeview">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-9 col-md-9 pb-filemng-template-body">
                            <div class="">
                                <div class="">
                                    <div class="row" id="image-result">
                                        <div class="col-sm-12 no-padding">
                                            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                                                <a  href="#"  data-title=""
                                                    data-image="https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                    data-target="#image-gallery">
                                                    <img class="img-thumbnail"
                                                         src="https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                         alt="Another alt text">
                                                </a>
                                                <div class="col-sm-12 no-padding image-title">
                                                    <span>Ảnh số 1</span>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                                                <a  href="#"  data-title=""
                                                    data-image="https://images.pexels.com/photos/158971/pexels-photo-158971.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                    data-target="#image-gallery">
                                                    <img class="img-thumbnail"
                                                         src="https://images.pexels.com/photos/158971/pexels-photo-158971.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                         alt="Another alt text">
                                                </a>
                                            </div>
                                            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                                                <a  href="#"  data-title=""
                                                    data-image="https://images.pexels.com/photos/305070/pexels-photo-305070.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                    data-target="#image-gallery">
                                                    <img class="img-thumbnail"
                                                         src="https://images.pexels.com/photos/305070/pexels-photo-305070.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                         alt="Another alt text">
                                                </a>
                                            </div>
                                            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                                                <a  href="#"  data-title="Test1"
                                                    data-image="https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                    data-target="#image-gallery">
                                                    <img class="img-thumbnail"
                                                         src="https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                                                         alt="Another alt text">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="pb-filemng-template">

</div>

<style>
    .image-title{
        white-space: nowrap; 
        width: 190px; 
        overflow: hidden;
        text-overflow: ellipsis; 
    }
    .pb-filemng-navbar {
        margin-bottom: 0;
    }

    .treeview-toggle-btn {
        margin-left: 15px;
    }

    .pb-filemng-template-btn {
        background-color: Transparent;
        background-repeat:no-repeat;
        border: none;
        cursor:pointer;
        outline:none;
        color: gray;
        padding: 0px 13px 0px 13px;
    }

    .pb-filemng-active {
        border-bottom: 2px solid #6d97db;
        color: #5f6977;
    }

    .pb-filemng-template-btn:hover {
        color: blue;
    }

    .pb-filemng-body-folders > img:hover {
        cursor: pointer;
    }

    .btn-align {
        margin-top: 7px;
    }

    .pb-filemng-template-treeview {
        border-right: 1px solid gray;
    }

    .pb-filemng-folder {
        color: orange;
        padding-bottom: 3px;
    }

    .pb-filemng-paragraphs {
        margin-top: -20px;
        text-align: center;
    }

    .img-responsive {
        margin: 0 auto;
    }

    @media screen and (max-width: 767px) {

        .pb-filemng-template-treeview {
            border-right: none;
        }

        #options {
            text-align: center;
        }

        #options > li {
            display: inline-block;
        }

        #pb-filemng-navigation > ul {
            text-align: center;
        }

        #pb-filemng-navigation > ul > li {
            display: inline-block;
        }
    }
    .btn:focus, .btn:active, button:focus, button:active {
        outline: none !important;
        box-shadow: none !important;
    }

    #image-gallery .modal-footer{
        display: block;
    }
    .thumb{
        margin-top: 5px;
        margin-bottom: 5px;
    }
    .img-thumbnail{
        width: 190px !important;
        height: 125px !important;
    }

</style>
<div class="modal fade" id="modal-folder" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Thêm mới thư mục</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="tblFolder">
                    <input type="hidden" id="isEdit">
                    <input type="hidden" id="oldName">
                    <input type="hidden" id="path">
                    <div class="form-group">
                        <label for="name" class="col-sm-4 control-label">Tên thư mục:<span class="required">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" name="folderName" id="folderName" class="form-control" placeholder="Tên thư mục" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="btnSave" type="button">Lưu lại</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-files" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tải lên hình ảnh</h4>
            </div>
            <div class="modal-body">
                <div>
                    <div class="form-group">
                        <form>
                            <input id="files" type="file" accept="image/*" multiple>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="btnUpload" type="button">Tải lên</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="fullPath">
<!-- you need to include the shieldui css and js assets in order for the charts to work -->
<link href="${pageContext.request.contextPath}/share/css/all.min.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/share/js/shieldui-all.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/share/js/fileManagerData.js" type="text/javascript"></script>

<script>
    $(document).ready(function () {
        $('#files').imageuploadify();
    });
    $(".sidebar-menu li").removeClass("active");
    $("#TblFiles").addClass("active");
    var datasource = null;
    function getDatasource() {
        $.ajax({
            async: false,
            url: "getDatasources.json",
            success: function (data) {
                datasource = data;
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
        return datasource;
    }
    function reloadTree() {
        $("#treeview-toggle").html("<div id='treeview'></div>");
        $("#treeview").shieldTreeView({
            dataSource: getDatasource(),
            events: {
                select: function (e) {
                    $("#oldName").val(e.item.text);
                    $("#fullPath").val(e.item.fullPath);
                    getFiles(e.item.fullPath);
                }
            }
        });
    }
    function addFolder(name) {
        $.ajax({
            method: "POST",
            async: false,
            url: "saveFolder.json",
            data: {name: name, path: $("#fullPath").val()},
            success: function (data) {
                if (!data.hasError) {
                    $("#modal-folder").modal("hide");
                    notifSuccess(data.error);
                    reloadTree();
                } else {
                    notifError(data.error);
                }
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    function removeFolder() {
        $.ajax({
            method: "POST",
            async: false,
            url: "removeFolder.json",
            data: {path: $("#fullPath").val()},
            success: function (data) {
                if (!data.hasError) {
                    notifSuccess(data.error);
                    reloadTree();
                } else {
                    notifError(data.error);
                }
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    function updateFolder(newName) {
        $.ajax({
            method: "POST",
            async: false,
            url: "updateFolder.json",
            data: {oldName: $("#oldName").val(), newName: newName, path: $("#fullPath").val()},
            success: function (data) {
                if (!data.hasError) {
                    $("#modal-folder").modal("hide");
                    notifSuccess(data.error);
                    reloadTree();
                } else {
                    notifError(data.error);
                }
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    $("#btnSave").click(function () {
        var name = $("#folderName").val();
        if ($("#isEdit").val() == "0") {
            addFolder(name);
        } else {
            updateFolder(name);
        }
    });
    function getFiles(folder) {
        $.ajax({
            async: false,
            url: "getFolder.json",
            data: {folder: folder},
            success: function (data) {
                var size = data.length;
                var html = "";
                if (size > 0) {
                    for (var i = 0; i < size; i++) {
                        html += '<div class="col-lg-3 col-md-4 col-xs-6 thumb">';
                        html += '<a href="javascript:void(0)" data-title="" data-image="" data-target="#image-gallery">';
                        html += '<img title="' + data[i].fileName + '" class="img-thumbnail" src="/Images/' + data[i].filePath + '" alt="Another alt text" draggable="true" data-bukket-ext-bukket-draggable="true">';
                        html += '</a><div class="col-sm-12 no-padding image-title" title="' + data[i].fileName + '">' + data[i].fileName + '</div></div>';
                    }
                } else {
                    html += '<div class="col-sm-12 no-padding">Thư mục rỗng</div>';
                }
                $("#image-result").html(html);
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
        return datasource;
    }

    $("#btnCreateFolder").click(function () {
        $("#folderName").val("");
        $("#modal-folder .modal-title").html("Thêm mới thư mục");
        $("#modal-folder").modal("show");
        $("#isEdit").val("0");
    });

    $("#btnCreateFile").click(function () {
        $("#modal-files").modal("show");
    });


    $.contextMenu({
        selector: '.sui-treeview-item-text',
        callback: function (key, options) {
            switch (key) {
                case "Trash":
                    removeFolder();
                    break;
                case "Edit":
                    $("#folderName").val($("#oldName").val());
                    $("#modal-folder .modal-title").html("Cập nhật thư mục thư mục");
                    $("#modal-folder").modal("show");
                    $("#isEdit").val("1");
                    break;
            }
        },
        items: {
            "plus": {"name": "Thêm thư mục", "icon": "fa-folder-open-o"},
            "share": {"name": "Thêm file", "icon": "fa-share-alt"},
            "Edit": {"name": "Cập nhật", "icon": "fa-edit"},
            "Trash": {"name": "Xóa", "icon": "fa-trash"}
        }
    });
    $.contextMenu({
        selector: '.img-thumbnail',
        callback: function (key, options) {
            switch (key) {
                case "receiver":
                    var $temp = $("<input>");
                    $("body").append($temp);
                    $temp.val(options.$trigger.attr("src")).select();
                    document.execCommand("copy");
                    $temp.remove();
                    break;
                case "Trash":
                    removeFile(options.$trigger.attr("title"));
                    break;
            }
        },
        items: {
            "receiver": {"name": "Copy đường dẫn", "icon": "fa-folder-open-o"},
            "share": {"name": "Phóng to", "icon": "fa-share-alt"},
            "Trash": {"name": "Xóa", "icon": "fa-trash"}
        }
    });
    function uploadFiles() {
        var files = document.getElementById("files").files;
        var data = new FormData();
        for (var i = 0; i < files.length; i++)
        {
            data.append('files', files[i]);
        }
        data.append('path', $("#fullPath").val());
        $.ajax({
            type: "POST",
            async: false,
            processData: false,
            contentType: false,
            dataType: 'json',
            enctype: 'multipart/form-data',
            url: "uploadFiles.json",
            data: data,
            success: function (rs) {
                if (!data.hasError) {
                    $("#modal-files").modal("hide");
                    notifSuccess(rs.error);
                    reloadTree();
                } else {
                    notifError(rs.error);
                }
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    $("#btnUpload").click(function () {
        var files = document.getElementById("files").files;
        if (files.length == 0) {
            notifError("Bạn chưa chọn ảnh nào")
        } else {
            uploadFiles();
        }
    });
    function removeFile(file) {
        $.ajax({
            method: "POST",
            async: false,
            url: "removeFolder.json",
            data: {name: file, path: $("#fullPath").val()},
            success: function (data) {
                if (!data.hasError) {
                    notifSuccess(data.error);
                    reloadTree();
                } else {
                    notifError(data.error);
                }
            }, error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    var treeview = $("#treeview").shieldTreeView({
        dataSource: getDatasource(),
        events: {
            select: function (e) {
                $("#oldName").val(e.item.text);
                $("#fullPath").val(e.item.fullPath);
                getFiles(e.item.fullPath);
            }
        }
    }).swidget();
    treeview.expanded(true, [0]);
    $("#oldName").val(datasource[0].text);
    $("#fullPath").val(datasource[0].fullPath);
    getFiles(datasource[0].fullPath);

</script>
