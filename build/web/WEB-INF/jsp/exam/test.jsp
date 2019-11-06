

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/share/css/examl-core.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/share/css/jquery.countdown.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" async src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=MML_CHTML"></script>

<style>
    .digit{
        color: black !important;
        background: #f7cbcb !important;
    }
</style>
<div class="d-flex flex-column h-100" style="height: 100% !important;">
    <div class="mdk-drawer-layout js-mdk-drawer-layout flex">
        <div class="ele-main ele-light-grey col-sm-12 no-padding ">
            <div id="sidebar" class="column col-sm-3 " style="visibility: visible">
                <div class="sidebar sidebar-right sidebar-light bg-white o-hidden">
                    <div class="sidebar-p-y" data-simplebar="init" data-simplebar-force-enabled="true">
                        <div class="simplebar-scroll-content" style="padding-right: 17px; margin-bottom: -34px;">
                            <div class="simplebar-content" style="padding-bottom: 17px; margin-right: -17px;">
                                <div class="sidebar-heading">
                                    <div id="countdown"></div>
                                </div>
                                <div class="exam-container">
                                    <center>
                                        <ul class="ul-exam-button">
                                            <c:set var="count" value="0" />
                                            <c:forEach var="item" items="${questions}">
                                                <c:set var="count" value="${count+1}" />
                                                <li>
                                                    <input id="${count}" type="button" onclick="activeQuestion(${item.questionId})"
                                                        class="btn btnq border6 btnq-answered btnq-active" value="${count}">
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <div id="countdown" class="countdownHolder"></div>
                                    </center>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="ele-main-content" class="column col-sm-9 ">
                <div class=" mdk-drawer-layout__content mdk-drawer-layout__content--scrollable mdk-drawer-content">
                    <div class="container-fluid">
                        <div class="card-group">
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="mb-0 txt-left"><strong>Đề thi:</strong>${exam.name}</h4>
                                    <div class="txt-left"><strong>Thời gian làm bài: </strong>${exam.timeName}</div>
                                    <div class="txt-left"><strong>Giáo viên ra đề: </strong>${exam.fullname}</div>
                                </div>
                            </div>
                        </div>
                        <input id="examId" value="${exam.examId}" type="hidden" />
                        <div class="card-group">
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="mb-0"><strong id="totalQuestion">${fn:length(questions)}</strong></h4>
                                    <small class="text-muted-light">Tổng số</small>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="text-success mb-0"><strong id="answered-question">0</strong></h4>
                                    <small class="text-muted-light">Đã làm</small>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="text-danger mb-0"><strong id="unanswer-question">${fn:length(questions)}</strong></h4>
                                    <small class="text-muted-light">Còn lại</small>
                                </div>
                            </div>
                        </div>
                        <div>
                            <c:set var="count" value="0" />
                            <c:forEach var="item" items="${questions}">
                                <c:set var="count" value="${count+1}" />
                                <div class="card card-exam">
                                    <div data-id="${item.questionId}" class="card-header bg-white">
                                        <div class="media align-items-center">
                                            <div class="media-left">
                                                <h4 class="mb-0"><strong>#${count}</strong></h4>
                                            </div>
                                            <div class="media-body">
                                                <h4 class="card-title">
                                                    ${item.content}
                                                </h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body" data-id="${count}">
                                        <c:forEach var="ele" items="${item.lstAnswer}">
                                            <div class="">
                                                <label class="custom-control custom-checkbox mb-0">
                                                    <input type="radio" name="${item.questionId}" value="${ele.answerId}" class="custom-control-input answer-id">
                                                    <span class="custom-control-indicator"></span>
                                                    <span class="custom-control-description">${ele.content}</span>
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="footer exam-footer">
                                <button type="button" id="btn-submit" class="btn btn-success">Nộp bài</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<style>
    body {
        background: #F9F9F9 !important;
        height: 100% !important;
        position: relative !important
    }

    .content-wrapper {
        height: 100vh
    }

    .content_wrapper .h-100 {
        height: 100%;
        float: left
    }

    .selected-question {
        background-color: green !important;
        color: #FFFFFF;
    }

    .unselected-question {
        background-color: red !important;
        color: #FFFFFF;
    }

    .txt-left {
        text-align: left !important;
    }
</style>
<link href="share/Material/css/flipclock.css" rel="stylesheet" type="text/css" />
<link href="share/Material/css/material-design-kit.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/share/js/jquery.countdown.js" type="text/javascript"></script>
<script>
    function activeQuestion(ques) {
        $('html, body').animate({
            'scrollTop': $('#question-' + ques).offset().top
        }, parseInt('${exam.timeValue}') * 60);
    }
    $(".custom-control-input").click(function () {
        var id = $(this).closest(".card-body").attr("data-id");
        var selected = $(this).closest(".card-body").find('input.custom-control-input:checked').length;
        if (selected > 0) {
            $("#" + id).removeClass("unselected-question");
            $("#" + id).addClass("selected-question");
        } else {
            $("#" + id).addClass("unselected-question");
        }
        $("#answered-question").html($(".ul-exam-button .selected-question").length);
        $("#unanswer-question").html($(".ul-exam-button li").length - $(".ul-exam-button .selected-question").length);
    });
    $(function () {
        var ts = parseInt('${exam.timeValue}') * 60;
        $('#countdown').countdown({
            hours: ts > 60 ? ts % 60 : 0,
            minutes: ts < 60 ? ts : Math.floor(ts / 60),
            seconds: 00,
            callback: function (days, hours, minutes, seconds) {
                if (hours === 0 && minutes === 0 && seconds === 1) {
                    $(".custom-control-input").attr("disabled", true);
                }
            }
        });

    });
    $(document).on("click", "#btn-submit", function () {
        var result = "";
        $(".card-exam").each(function () {
            var quesId = $(this).find(".card-header").attr("data-id");
            var chkArray = [];
            $(this).find(".answer-id:checked").each(function () {
                chkArray.push($(this).val());
            });
            var selected;
            selected = chkArray.join(',');
            result += quesId + ":" + selected;
            result += ";";
        });
        $.ajax({
            async: false,
            url: "completeTest.html",
            data: {
                result: result,
                examId: $("#examId").val()
            },
            type: "POST",
            dataType: 'json',
            success: function (data) {
                if (data == "success") {
                    location.href = "/Elearning/markTest.html";
                }
            },
            error: function (err) {
                console.log(err);
            }, complete: function (jqXHR, textStatus) {

            }
        });
    });
</script>