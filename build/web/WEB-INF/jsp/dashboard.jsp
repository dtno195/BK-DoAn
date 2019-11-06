

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>-->
<script src="share/js/jquery-1.9.1.min.js"></script>
<script src="share/js/highcharts.js"></script>

<div class="row">
    <div class="col-lg-12" style="padding-left: 0px ">
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
                <input id="chart" hidden="true" value="<c:out value=" ${jsonData}"></c:out>">
                <form action="/Elearning/dashboard.html">
                    <div class="row" style="display: flex">
                        <div class="col-md-3" style="display: flex">
                            <label style="padding-top: 8px;">Năm</label>
                            <select class="form-control" name="year">
                                <option ${year=="2016" ?"selected":""} value="2016">2016</option>
                                <option ${year=="2017" ?"selected":""} value="2017">2017</option>
                                <option ${year=="2018" ?"selected":""} value="2018">2018</option>
                                <option ${year=="2019" ?"selected":""} value="2019">2019</option>
                                <option ${year=="2020" ?"selected":""} value="2020">2020</option>
                                <option ${year=="2021" ?"selected":""} value="2021">2021</option>
                            </select>
                        </div>
                        <div class="col-md-3" style="display: flex">
                            <label style="padding-top: 8px;">Tháng</label>
                            <select class="form-control" name="month">
                                <option ${month=="0" ?"selected":""} value="0">- Chọn -</option>
                                <option ${month=="1" ?"selected":""} value="1">1</option>
                                <option ${month=="2" ?"selected":""} value="2">2</option>
                                <option ${month=="3" ?"selected":""} value="3">3</option>
                                <option ${month=="4" ?"selected":""} value="4">4</option>
                                <option ${month=="5" ?"selected":""} value="5">5</option>
                                <option ${month=="6" ?"selected":""} value="6">6</option>
                                <option ${month=="7" ?"selected":""} value="7">7</option>
                                <option ${month=="8" ?"selected":""} value="8">8</option>
                                <option ${month=="9" ?"selected":""} value="9">9</option>
                                <option ${month=="10" ?"selected":""} value="10">10</option>
                                <option ${month=="11" ?"selected":""} value="11">11</option>
                                <option ${month=="12" ?"selected":""} value="12">12</option>
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
                            <input type="submit" value="Lọc" class="btn btn-warning" />
                        </div>
                    </div>
                </form>
                <!--<canvas id="myChart" width="100%" height="400"></canvas>-->
                <div id="chartDetail"></div>
            </div>
        </div>
    </div>
</div>
<div class="row mail-app">
    <div class="col-lg-12" style="padding-left: 0px ">
        <div id="mail-wrapper" class="content-body" style="position:relative; top:0; left:0;" dir="ltr">
            <div class="panel-group expansion" id="expansion" role="tablist" aria-multiselectable="true">
                <div class="block-header">
                    <h5>Danh sách đề thi</h5>
                </div>
                <c:set var="count" value="0" />
                <c:forEach var="item" items="${exams}">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="expansionHeading_${count}">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#expansion" href="#expansion_${count}"
                                    aria-expanded="false" aria-controls="expansion_${count}">
                                    <div class="sender-wrapper" title="${item.name}">
                                        <span class="sender" title="${item.name}">${item.name}</span>
                                    </div>
                                    <div class="info-wrapper">
                                        <span class="info">${item.subjectName} (${item.timeName})</span>
                                    </div>
                                </a>
                            </h4>
                        </div>
                        <div id="expansion_${count}" class="panel-collapse
                             collapse" role="tabpanel"
                            aria-labelledby="expansionHeading_${count}">
                            <div class="panel-body">
                                ${item.content}
                            </div>
                            <div class="info-wrapper" style="text-align: center;">
                                <button type="button" onclick="location.href = '/Elearning/examTest.html?id=${item.examId}'"
                                    class="btn btn-danger" style="border-color: #ef5350; margin-bottom: 20px;">Thi thử
                                    <i class="fa fa-telegram"></i></button>
                            </div>
                        </div>
                    </div>
                    <c:set var="count" value="${count + 1}" />
                </c:forEach>
            </div>
          
        </div>
    </div>
</div>

<style>
    select.form-control {
        border: 1px !important;
    }

    body {
        font-family: "Open Sans", sans-serif !important;
    }

    .list-group .list-group-item,
    .panel-group .panel .panel-title {
        font-size: 0.9875em !important;
    }

    #mail-wrapper .panel-group .panel {
        margin-left: 0 !important;
        margin-right: 0 !important;
    }

    .panel-title a[aria-expanded="true"] {
        color: black !important;
        background-color: #FFFFFF !important;
    }

    .panel-group .panel .panel-body {
        font-size: 0.9725em !important;
    }

    .mail-app #mail-wrapper .panel-group .panel-body {
        padding: 30px !important;
    }
</style>
<script>

    chartDetailPTK($("#chart").val());
    // generateChart();
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