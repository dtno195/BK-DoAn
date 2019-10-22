<%-- 
    Document   : Index
    Created on : Oct 15, 2018, 11:08:21 PM
    Author     : lienptk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .btn-default {
        color: #333 !important;
        background-color: #fff !important;
        border-color: #ccc !important;
    }
    #add-event-form .form-group{
        margin: 0px !important;
        margin-bottom: 5px !important;
        padding-bottom: 0px !important;
    }
    .fc-event-time, .fc-event-title{
        white-space: nowrap;
        width: 100%;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    #content_wrapper{
        padding-left: 0px !important;
        padding-right: 0px !important;
    }
</style>
<div id="content" class="d-flex flex-column"  style="height: 100% !important;background-color: #eef5f9">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-3">
            <!-- new widget -->
            <div class="jarviswidget jarviswidget-color-blueDark">
                <header>
                    <h2> Thêm kế hoạch </h2>
                </header>
                <!-- widget div-->
                <div>
                    <div class="widget-body">
                        <!-- content goes here -->
                        <form id="add-event-form">
                            <fieldset>
                                <div class="form-group">
                                    <label>Lựa chọn icon</label>
                                    <div class="btn-group btn-group-sm btn-group-justified input-icon" data-toggle="buttons">
                                        <label class="btn btn-default">
                                            <input type="radio" name="iconselect" id="icon-1" value="fa-info" checked="">
                                            <i class="fa fa-info text-muted"></i> </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="iconselect" id="icon-2" value="fa-warning">
                                            <i class="fa fa-warning text-muted"></i> </label>
                                        <label class="btn btn-default active">
                                            <input type="radio" name="iconselect" id="icon-3" value="fa-check">
                                            <i class="fa fa-check text-muted"></i> </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="iconselect" id="icon-4" value="fa-user">
                                            <i class="fa fa-user text-muted"></i> </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="iconselect" id="icon-5" value="fa-lock">
                                            <i class="fa fa-lock text-muted"></i> </label>
                                        <label class="btn btn-default">
                                            <input type="radio" name="iconselect" id="icon-6" value="fa-clock-o">
                                            <i class="fa fa-clock-o text-muted"></i> </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label>Tiêu đề</label>
                                    <input class="form-control" id="title" name="title" maxlength="40" type="text" placeholder="Tiêu đề">
                                </div>
                                <div class="form-group">
                                    <label>Mô tả</label>
                                    <textarea class="form-control" placeholder="Nhập mô tả cho sự kiện" rows="3" maxlength="40" id="description"></textarea>
                                    <!--<p class="note">Maxlength is set to 40 characters</p>-->
                                </div>
                                <div class="form-group">
                                    <label>Lựa chọn màu sắc</label>
                                    <div class="btn-group btn-group-justified btn-select-tick" data-toggle="buttons">
                                        <label class="btn bg-color-darken active">
                                            <input type="radio" name="priority" id="option1" value="bg-color-darken txt-color-white" checked="">
                                            <i class="fa fa-check txt-color-white"></i> </label>
                                        <label class="btn bg-color-blue">
                                            <input type="radio" name="priority" id="option2" value="bg-color-blue txt-color-white">
                                            <i class="fa fa-check txt-color-white"></i> </label>
                                        <label class="btn bg-color-orange">
                                            <input type="radio" name="priority" id="option3" value="bg-color-orange txt-color-white">
                                            <i class="fa fa-check txt-color-white"></i> </label>
                                        <label class="btn bg-color-greenLight">
                                            <input type="radio" name="priority" id="option4" value="bg-color-greenLight txt-color-white">
                                            <i class="fa fa-check txt-color-white"></i> </label>
                                        <label class="btn bg-color-blueLight">
                                            <input type="radio" name="priority" id="option5" value="bg-color-blueLight txt-color-white">
                                            <i class="fa fa-check txt-color-white"></i> </label>
                                        <label class="btn bg-color-red">
                                            <input type="radio" name="priority" id="option6" value="bg-color-red txt-color-white">
                                            <i class="fa fa-check txt-color-white"></i> </label>
                                    </div>
                                </div>
                            </fieldset>
                            <div class="form-actions">
                                <div class="row">
                                    <div class="col-md-12">
                                        <button class="btn btn-default" type="button" id="add-event">
                                            Thêm mới
                                        </button>
                                        <input type="hidden" id="planId" value="0" />
                                    </div>
                                </div>
                            </div>
                        </form>

                        <!-- end content -->
                    </div>
                </div>
                <!-- end widget div -->
            </div>
            <!-- end widget -->
            <div class="well well-sm" id="event-container" style="">
                <form>
                    <fieldset>
                        <legend>
                            Lựa chọn sự kiện
                        </legend>
                        <ul id="external-events" class="list-unstyled">

                        </ul>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" id="drop-remove" class="checkbox style-0" checked="checked">
                                <span>Xóa sau khi thêm</span> </label>

                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-9">

            <!-- new widget -->
            <div class="jarviswidget jarviswidget-color-blueDark">
                <header>
                    <span class="widget-icon"> <i class="fa fa-calendar"></i> </span>
                    <h2> Danh sách kế hoạch </h2>
                    <div class="widget-toolbar">
                        <!-- add: non-hidden - to disable auto hide -->
                        <div class="btn-group">
                            <button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown" aria-expanded="false">
                                Hiển thị <i class="fa fa-caret-down"></i>
                            </button>
                            <ul class="dropdown-menu js-status-update pull-right">
                                <li>
                                    <a href="javascript:void(0);" id="mt">Tháng</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="ag">Agenda</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="td">Hôm nay</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </header>

                <!-- widget div-->
                <div>

                    <div class="widget-body no-padding">
                        <!-- content goes here -->
                        <div class="widget-body-toolbar">

                            <div id="calendar-buttons">

                                <div class="btn-group">
                                    <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i class="fa fa-chevron-left"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i class="fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <div id="calendar" class="fc fc-ltr"></div>

                        <!-- end content -->
                    </div>

                </div>
                <!-- end widget div -->
            </div>
            <!-- end widget -->

        </div>

    </div>
</div>
<!-- end row -->

<script src="${pageContext.request.contextPath}/share/SmartAdmin/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/share/SmartAdmin/js/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/share/SmartAdmin/js/select2.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/share/SmartAdmin/css/smartadmin-production-plugins.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/share/SmartAdmin/css/smartadmin-production.min.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/share/SmartAdmin/js/moment.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/share/SmartAdmin/js/jquery.fullcalendar.min.js" type="text/javascript"></script>
<div class="modal fade" id="modal_default" data-backdrop="static">
    <div class="modal-dialog modal-default">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Cập nhật sự kiện</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label for="titleText" class="col-sm-2 control-label">Tiêu đề:<span class="required">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" id="titleText" class="form-control" />
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="contentText" class="col-sm-2 control-label">Nội dung:<span class="required">*</span></label>
                        <div class="col-sm-10">
                            <textarea id="contentText" class="form-control" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">Icon:<span class="required">*</span></label>
                        <div class="col-sm-4">
                            <div class="btn-group btn-group-sm btn-group-justified input-icon" data-toggle="buttons">
                                <label class="btn btn-default">
                                    <input type="radio" name="iconselect1" id="icon-1" value="fa-info" checked="">
                                    <i class="fa fa-info text-muted"></i> </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="iconselect1" id="icon-2" value="fa-warning">
                                    <i class="fa fa-warning text-muted"></i> </label>
                                <label class="btn btn-default active">
                                    <input type="radio" name="iconselect1" id="icon-3" value="fa-check">
                                    <i class="fa fa-check text-muted"></i> </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="iconselect1" id="icon-4" value="fa-user">
                                    <i class="fa fa-user text-muted"></i> </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="iconselect1" id="icon-5" value="fa-lock">
                                    <i class="fa fa-lock text-muted"></i> </label>
                                <label class="btn btn-default">
                                    <input type="radio" name="iconselect1" id="icon-6" value="fa-clock-o">
                                    <i class="fa fa-clock-o text-muted"></i> </label>
                            </div>
                        </div>
                        <label for="" class="col-sm-2 control-label">Màu:<span class="required">*</span></label>
                        <div class="col-sm-4">
                            <div class="btn-group btn-group-justified btn-select-tick input-color" data-toggle="buttons">
                                <label class="btn bg-color-darken active">
                                    <input type="radio" name="priority1" id="option1" value="bg-color-darken" checked="">
                                    <i class="fa fa-check txt-color-white bg-color-darken"></i> </label>
                                <label class="btn bg-color-blue">
                                    <input type="radio" name="priority1" id="option2" value="bg-color-blue">
                                    <i class="fa fa-check bg-color-blue txt-color-white"></i> </label>
                                <label class="btn bg-color-orange">
                                    <input type="radio" name="priority1" id="option3" value="bg-color-orange">
                                    <i class="fa fa-check bg-color-orange txt-color-white"></i> </label>
                                <label class="btn bg-color-greenLight">
                                    <input type="radio" name="priority1" id="option4" value="bg-color-greenLight">
                                    <i class="fa fa-check bg-color-greenLight txt-color-white"></i> </label>
                                <label class="btn bg-color-blueLight">
                                    <input type="radio" name="priority1" id="option5" value="bg-color-blueLight">
                                    <i class="fa fa-check bg-color-blueLight txt-color-white"></i> </label>
                                <label class="btn bg-color-red">
                                    <input type="radio" name="priority1" id="option6" value="bg-color-red">
                                    <i class="fa fa-check bg-color-red txt-color-white"></i> </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnSave">Lưu lại</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<!--<script src="${pageContext.request.contextPath}/share/SmartAdmin/js/vi.js" type="text/javascript"></script>-->
<script>

    $(document).ready(function () {
        var allDayStatus = true;
        $("#rightContent").remove();
        $("#bodyContent").removeClass("col-sm-9");
        $("#bodyContent").addClass("col-sm-12 no-padding");
        "use strict";
        var selectedEvent = null;
        var hdr = {
            left: 'title',
            center: 'month,agendaWeek,agendaDay',
            right: 'prev,today,next'
        };

        var initDrag = function (e) {
            var eventObject = {
                title: $.trim(e.children().text()),
                description: $.trim(e.children('span').attr('data-description')),
                icon: $.trim(e.children('span').attr('data-icon')),
                duration: '03:00',
                className: $.trim(e.children('span').attr('class')),
            };
            e.data('eventObject', eventObject);
            e.draggable({
                zIndex: 999,
                revert: true,
                revertDuration: 0
            });
        };

        var addEvent = function (title, priority, description, icon) {
            title = title.length === 0 ? "Untitled Event" : title;
            description = description.length === 0 ? "No Description" : description;
            icon = icon.length === 0 ? " " : icon;
            priority = priority.length === 0 ? "label label-default" : priority;
            var html = $('<li><span  class="' + priority + '" data-description="' + description + '" data-icon="' +
                    icon + '">' + title + '</span></li>').prependTo('ul#external-events').hide().fadeIn();
            $("#event-container").effect("highlight", 800);
            initDrag(html);

        };
        function getDatasource(month, year) {
            var datasource = new Array();
            $.ajax({
                async: false,
                data: {month: month, year: year},
                url: "getPlans.json",
                success: function (data) {
                    if (data != null) {
                        var size = data.length;
                        for (var i = 0; i < size; i++) {
                            data[i].start = new Date(data[i].start);
                            data[i].end = data[i].end != null ? new Date(data[i].end) : null;
                            datasource.push(data[i]);
                        }
                    }
                }, error: function (jqXHR, textStatus, errorThrown) {
                }
            });
            return datasource;
        }
        $('#calendar').fullCalendar('removeEvents');
        $('#external-events > li').each(function () {
            initDrag($(this));
        });
        $('#add-event').click(function () {
            var title = $('#title').val(),
                    priority = $('input:radio[name=priority]:checked').val(),
                    description = $('#description').val(),
                    icon = $('input:radio[name=iconselect]:checked').val();
            addEvent(title, priority, description, icon);
        });
        $('#calendar').fullCalendar({
            header: hdr,
            buttonText: {
                prev: '<i class="fa fa-chevron-left"></i>',
                next: '<i class="fa fa-chevron-right"></i>'
            },
            editable: true,
            droppable: true,
            drop: function (date, allDay) {
                var originalEventObject = $(this).data('eventObject');
                var copiedEventObject = $.extend({}, originalEventObject);
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDayStatus;
                var planId = savePlans(copiedEventObject.title, copiedEventObject.className, copiedEventObject.description,
                        copiedEventObject.icon, date.format(), date.format(), 0);
                copiedEventObject.id = planId;
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
                if ($('#drop-remove').is(':checked')) {
                    $(this).remove();
                }
            },
            select: function (start, end, allDay) {
                var title = prompt('Event Title:');
                if (title) {
                    $("#calendar").fullCalendar('renderEvent', {
                        title: title,
                        start: start,
                        end: end,
                        allDay: allDay
                    }, true);
                }
                $("#calendar").fullCalendar('unselect');
            },
            events:
                    getDatasource("", "")
            ,
            eventRender: function (event, element, icon) {
                if (!event.description == "") {
                    element.find('.fc-event-title').append("<br/><span title='" + event.description + "' class='ultra-light'>" + event.description +
                            "</span>");
                }
                element.attr("title", event.title);
                if (!event.icon == "") {
                    element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +
                            " '></i>");
                }
                if (selectedEvent != null && selectedEvent.id == event.id) {
                    savePlans(event.title, event.className, event.description,
                            event.icon, event.start.format(), event.end.format(), event.id);
                }
            },
            viewRender: function (view, element) {
                $("#calendar").fullCalendar('refresh');
            },
            windowResize: function (event, ui) {
                $('#calendar').fullCalendar('render');
            },
            eventResize: function (event, delta, revertFunc) {
                savePlans(event.title, event.className, event.description,
                        event.icon, event.start.format(), event.end.format(), event.id);
            },
            eventMouseover: function (event, domEvent, view) {
                var el = $(this);
                var layer = '<div id="events-layer" class="fc-transparent"><span id="delbut' + event.id + '" class="btn btn-default trash btn-xs">Xóa</span></div>';
                el.append(layer);
                el.find(".fc-bg").css("pointer-events", "none");
                $("#delbut" + event.id).click(function () {
                    $.confirm({
                        'title': 'Xác nhận xóa',
                        'message': 'Bạn có chắc chắn muốn xóa sự kiện này?',
                        'buttons': {
                            'Xóa': {
                                'class': 'btn-confirm-yes btn-success',
                                'action': function () {
                                    $.ajax({
                                        type: 'POST',
                                        data: {planId: event.id},
                                        url: "deletePlans.json",
                                        success: function (obj) {
                                            if (obj.hasError || obj == "") {
                                                notifError(obj.error);
                                            } else {
                                                $('#calendar').fullCalendar('removeEvents', event._id);
                                                notifSuccess("Xóa sự kiện thành công");
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
                });
            },
            eventMouseout: function (event) {
                $("#events-layer").remove();
            },
            eventDrop: function (event, delta, revertFunc) {
                selectedEvent = event;
            },
            eventClick: function (calEvent, jsEvent, view) {
                if ("btn btn-default trash btn-xs" != jsEvent.target.className) {
                    $("#titleText").val(calEvent.title);
                    $("#contentText").val(calEvent.description);
                    //for icon
                    $("#modal_default ." + calEvent.icon).closest(".btn-group-justified").find(".btn-default").removeClass("active");
                    $("#modal_default ." + calEvent.icon).closest(".btn-default").addClass("active");
                    //for color
                    if (calEvent.className.length > 0) {
                        $("#modal_default i." + calEvent.className[0]).closest(".btn-group-justified").find(".btn").removeClass("active");
                        $("#modal_default i." + calEvent.className[0]).closest(".btn").addClass("active");
                    }
                    selectedEvent = calEvent;
                    $("#modal_default").modal("show");
                }
            }
        });
        $('.fc-header-right, .fc-header-center').hide();
        $('#calendar-buttons #btn-prev').click(function () {
            $('.fc-button-prev').click();
            var moment = $('#calendar').fullCalendar('getDate');
            $("#demo-calendar").fullCalendar('removeEvents');
            $("#demo-calendar").fullCalendar('addEventSource', getDatasource(moment.month() + 1, moment.year()));
            return false;
        });

        $('#calendar-buttons #btn-next').click(function () {
            $('.fc-button-next').click();
            var moment = $('#calendar').fullCalendar('getDate');
            $("#demo-calendar").fullCalendar('removeEvents');
            $("#demo-calendar").fullCalendar('addEventSource', getDatasource(moment.month() + 1, moment.year()));
            return false;
        });

        $('#calendar-buttons #btn-today').click(function () {
            $('.fc-button-today').click();
            return false;
        });

        $('#mt').click(function () {
            allDayStatus = true;
            $('#calendar').fullCalendar('changeView', 'month');
        });

        $('#ag').click(function () {
            $('#calendar').fullCalendar('changeView', 'agendaWeek');
        });

        $('#td').click(function () {
            allDayStatus = false;
            $('#calendar').fullCalendar('changeView', 'agendaDay');
        });
        $("#btnSave").click(function () {
            var obj = [];
            obj.push("event");
            selectedEvent.title = $("#titleText").val();
            selectedEvent.description = $("#contentText").val();
            selectedEvent.icon = $("#modal_default .input-icon .btn-default.active").find("input[name='iconselect1']").val();
            obj.push($("#modal_default .input-color .btn.active").find("input[name='priority1']").val());
            selectedEvent.className = obj;
            $("#calendar").fullCalendar('updateEvent', selectedEvent);
            savePlans(selectedEvent.title, selectedEvent.className, selectedEvent.description,
                    selectedEvent.icon, selectedEvent.start.format(), selectedEvent.end.format(), selectedEvent._id);
            $("#modal_default").modal("hide");
        });
    });
    function savePlans(title, priority, description, icon, fromDate, toDate, planId1) {
        var planId = 0;
        $.ajax({
            type: "POST",
            data: {
                content: description,
                title: title,
                type: icon,
                fromDate: moment(fromDate).format("YYYY-MM-DD HH:mm:ss"),
                toDate: moment(toDate).format("YYYY-MM-DD HH:mm:ss"),
                color: priority,
                planId: planId1
            },
            url: 'savePlans.json',
            async: false,
            success: function (data) {
                if (data != null && !data.hasError) {
                    if (planId1 > 0) {
                        notifSuccess("Cập nhật kế hoạch thành công");
                    } else {
                        notifSuccess("Thêm mới kế hoạch thành công");
                    }
                    planId = data.id;
                } else {
                    planId = 0;
                    notifError(data.error);
                }
            }, error: function (err) {
                console.log(err);
            }
        });
        return planId;
    }
</script>