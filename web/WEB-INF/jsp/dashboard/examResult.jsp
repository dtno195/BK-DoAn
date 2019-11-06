
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>-->
<script src="share/js/jquery-1.9.1.min.js"></script>
<script src="share/js/highcharts.js"></script>
<div class="row">
    <div class="card">
        <header class="card-heading">
            <h2 class="card-title">Biểu đồ điểm sàn</h2>
            <small>Điểm sàn các năm gần đây của học sinh</small>
            <ul class="card-actions icons  right-top">
                <li>
                    <a href="javascript:void(0)" onclick="MaterialWrap.dashboardWebStats()" data-toggle="refresh">
                        <i class="zmdi zmdi-refresh-alt"></i>
                    </a>
                </li>
                <li class="dropdown">
                    <a style="background: #FFFFFF" title="Làm mới" href="javascript:void(0)" data-toggle="dropdown">
                        <i class="zmdi zmdi-more-vert"></i>
                    </a>
                    <ul class="dropdown-menu btn-primary dropdown-menu-right">
                        <c:forEach items="${subjects}" var="item">
                            <li>
                                <a href="javascript:void(0)">${item.dvsName}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
        </header>
        <div class="card-body p-15">
            <input id="chart" hidden="true" value="<c:out value="${jsonData}"></c:out>">
                <form action="/Elearning/examResult.html">
                    <div class="row" style="display: flex">
                        <div class="col-md-3" style="display: flex">
                            <label style="padding-top: 8px;">Năm</label>
                            <select class="form-control" name="year">
                                <option ${year=="2016"?"selected":""} value="2016">2016</option>
                            <option ${year=="2017"?"selected":""} value="2017">2017</option>
                            <option ${year=="2018"?"selected":""} value="2018">2018</option>
                        </select>
                    </div>
                    <div class="col-md-3" style="display: flex">
                        <label style="padding-top: 8px;">Tháng</label>
                        <select class="form-control" name="month">
                            <option ${month=="0"?"selected":""} value="0">- Chọn -</option>
                            <option ${month=="1"?"selected":""} value="1">1</option>
                            <option ${month=="2"?"selected":""} value="2">2</option>
                            <option ${month=="3"?"selected":""} value="3">3</option>
                            <option ${month=="4"?"selected":""} value="4">4</option>
                            <option ${month=="5"?"selected":""} value="5">5</option>
                            <option ${month=="6"?"selected":""} value="6">6</option>
                            <option ${month=="7"?"selected":""} value="7">7</option>
                            <option ${month=="8"?"selected":""} value="8">8</option>
                            <option ${month=="9"?"selected":""} value="9">9</option>
                            <option ${month=="10"?"selected":""} value="10">10</option>
                            <option ${month=="11"?"selected":""} value="11">11</option>
                            <option ${month=="12"?"selected":""} value="12">12</option>
                        </select>
                    </div>
                    <div class="col-md-3" style="display: flex">
                        <label style="padding-top: 8px;">Môn</label>
                        <select class="form-control" name="subject">
                            <c:forEach items="${lstSubject}" var="obj">
                                <option ${subject==obj.dvsValue?"selected":""} value="${obj.dvsValue}">${obj.dvsName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3" style="display: flex">
                        <input type="submit" value="Lọc" class="btn btn-warning"/>
                    </div>
                </div>
            </form>
            <!--<canvas id="myChart" width="100%" height="400"></canvas>-->
            <div id="chartDetail"></div>
        </div>
    </div>
    <table class="table table-bordered" style="background-color: #FFFFFF">
        <thead>
            <tr>
                <th>STT</th>
                <th>Đề thi</th>
                <th>Môn học</th>
                <th>Người thi</th>
                <th>Ngày thi</th>
                <th>Điểm</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lstItem.lstExamResultDTOs}" var = "item" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${item.examName}</td>
                    <td>${item.subjectName}</td>
                    <td>${item.fullName}</td>
                    <td>${item.dateOfTestString}</td>
                    <td>${item.score}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${!empty lstItem.listPaging}">
        <table class="pagination-table" border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:if test="${lstItem.currentPage > 1}">
                    <td><a href="examResult.html?page=${lstItem.currentPage - 1}">«</a></td>
                </c:if>
                <c:forEach items="${lstItem.listPaging}" var="i">
                    <c:choose>
                        <c:when test="${lstItem.currentPage eq i}">
                            <td class="active">${i}</td>
                        </c:when>
                        <c:when test="${i eq 0}">
                            <td class="load">...</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="examResult.html?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${lstItem.currentPage < lstItem.totalPages}">
                    <td><a href="examResult.html?page=${lstItem.currentPage + 1}">»</a></td>
                </c:if>
            </tr>
        </table>
    </c:if>
</div>
<style>

    select.form-control {
        border: 1px !important;
    }
    body{
        font-family: "Open Sans",sans-serif !important;
    }
    .list-group .list-group-item,.panel-group .panel .panel-title {
        font-size: 0.9875em !important;
    }
    #mail-wrapper .panel-group .panel{
        margin-left:  0 !important;
        margin-right:   0 !important;
    }
    .panel-title a[aria-expanded="true"]{
        color: black !important;
        background-color: #FFFFFF !important;
    }
    .panel-group .panel .panel-body{
        font-size:0.9725em !important;
    }
    .mail-app #mail-wrapper .panel-group .panel-body{
        padding: 30px !important;
    }
      .well h4, .well p {
        padding: -2px -1!important;
        margin: 0!important;
    }
    /*Pagination*/
    .pagination-table {
        margin: 0px;
        border: none;
        float: right;
    }
    .pagination-table a {
        color: #3276b1;
        padding: 7px 13px;
    }
    .pagination-table td {
        border-color: #dee2e6;
        text-align:center;
        min-width: 35px;
        padding: 0px;
    }
    .pagination-table td.load {
        color: #3276b1;
    }
    .pagination-table td.active {
        padding: 5px 8px;
    }
    .pagination-table td.active, .pagination-table td:hover{
        color: #fff;
        background-color: #3276b1;
        border-color: #3276b1;
    }
    .pagination-table td:hover a {
        color: #fff;
    }
</style>
<script>
    function notifyMe() {
        if (!("Notification" in window)) {
            alert("This browser does not support desktop notification");
        } else if (Notification.permission === "granted") {
            var notification = new Notification('Thông báo sắp đến giờ làm bài thi!', {
                body: 'Simple Desktop Notification by Amar Prakash Pandey!"',
                icon: 'share/image/Graduation.png'
            });
            notification.onclick = function (event) {
                event.preventDefault();
                window.open('http://www.mozilla.org', '_blank');
            };
        } else if (Notification.permission !== 'denied') {
            Notification.requestPermission(function (permission) {
                if (permission === "granted") {
                    var notification = new Notification("Hi there!");
                }
            });
        }
    }
    notifyMe();

    chartDetailPTK($("#chart").val());
    function chartDetailPTK(json) {
        $(function () {
            var obj = JSON.parse(json);
            var len = obj.chartDetailPTK.length;
            i = 0;
            var options = {
                chart: {
                    renderTo: 'chartDetail',
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: '',
                    x: -20
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                xAxis: {
                    categories: []
                },
                series: []
            }
            for (i; i < len; i++) {
                if (i === 0) {
                    var dat = obj.chartDetailPTK[i].category, lenJ = dat.length, j = 0, tmp;
                    for (j; j < lenJ; j++) {
                        options.xAxis.categories.push(dat[j]);
                    }
                } else {
                    options.series.push(obj.chartDetailPTK[i]);
                }
            }
            var placeholder = new Highcharts.Chart(options);
        });
    }

//    function generateChart() {
//        chartType = "chartDetailPTK";
//        $("#chartDetail").text("");
//        $.ajax({
//            type : "GET",
//            url : "http://localhost:8084/Elearning/chart.html?chart=" + chartType,
//            jsonpCallback : chartType,
//            error : function() {
//            }
//        });
//    }
</script>