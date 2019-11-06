

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/share/css/examl-core.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/share/css/jquery.countdown.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/share/js/jquery.countdown.js" type="text/javascript"></script>
<style>
    footer{
        display: none
    }
    .digit{
        color: black !important;
        background: #f7cbcb !important;
    }
</style>
<div class="d-flex flex-column h-100">
    <div class="mdk-drawer-layout js-mdk-drawer-layout flex">
        <div class="ele-main ele-light-grey col-sm-12 no-padding ">
            <div id="sidebar" class="column col-sm-3 " style="visibility: visible">
                <div class="sidebar sidebar-right sidebar-light bg-white o-hidden">
                    <div class="sidebar-p-y" data-simplebar="init" data-simplebar-force-enabled="true">
                        <div class="simplebar-scroll-content" style="padding-right: 17px; margin-bottom: -34px;">
                            <div class="simplebar-content" style="padding-bottom: 17px; margin-right: -17px;">
                                <div class="sidebar-heading">Thời gian còn lại:</div>
                                <div class="countdown sidebar-p-x" data-value="4" data-unit="hour"></div>
                                <div class="sidebar-heading">Pending</div>
                                <div class="exam-container">
                                    <center>
                                        <ul class="ul-exam-button">
                                            <li>
                                                <input type="button" id="q_btn_13852" onclick="activeQuestion(13852)" class="btn btnq border6 btnq-answered btnq-active" value="01">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8367" onclick="activeQuestion(8367)"   class="btn btnq border6" value="02">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8607" onclick="activeQuestion(8607)" class="btn btnq border6 btnq-answered" value="03">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_21300" onclick="activeQuestion(21300)" class="btn btnq border6" value="04">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_37847" onclick="activeQuestion(37847)"  class="btn btnq border6" value="05">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_9285" onclick="activeQuestion(9285)" class="btn btnq border6" value="06">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_24779" onclick="activeQuestion(24779)"  class="btn btnq border6" value="07">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_14181" onclick="activeQuestion(14181)"  class="btn btnq border6" value="08">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_41640" onclick="activeQuestion(41640)" class="btn btnq border6" value="09">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_37668" onclick="activeQuestion(37668)" class="btn btnq border6" value="10">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_37615" onclick="activeQuestion(37615)" class="btn btnq border6" value="11">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_22347" onclick="activeQuestion(22347)" class="btn btnq border6" value="12">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_10241" onclick="activeQuestion(10241)" class="btn btnq border6" value="13">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_9267" onclick="activeQuestion(9267)" class="btn btnq border6" value="14">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_41770" onclick="activeQuestion(41770)" class="btn btnq border6" value="15">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_7528" onclick="activeQuestion(7528)" class="btn btnq border6" value="16">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_14017" onclick="activeQuestion(14017)" class="btn btnq border6" value="17">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8586" onclick="activeQuestion(8586)" class="btn btnq border6" value="18">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_41099" onclick="activeQuestion(41099)" class="btn btnq border6" value="19">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_9571" onclick="activeQuestion(9571)" class="btn btnq border6" value="20">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8507" onclick="activeQuestion(8507)"  class="btn btnq border6" value="21">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8309" onclick="activeQuestion(8309)" class="btn btnq border6" value="22">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_10246" onclick="activeQuestion(10246)" class="btn btnq border6" value="23">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_16054" onclick="activeQuestion(16054)"  class="btn btnq border6" value="24">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8144" onclick="activeQuestion(8144)"  class="btn btnq border6" value="25">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8509" onclick="activeQuestion(8509)" class="btn btnq border6" value="26">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_28110" onclick="activeQuestion(28110)" class="btn btnq border6" value="27">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_7921" onclick="activeQuestion(7921)" class="btn btnq border6" value="28">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_9741" onclick="activeQuestion(9741)" class="btn btnq border6" value="29">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_29027" onclick="activeQuestion(29027)" class="btn btnq border6" value="30">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_37777" onclick="activeQuestion(37777)" class="btn btnq border6" value="31">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_29025" onclick="activeQuestion(29025)" class="btn btnq border6" value="32">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_37714" onclick="activeQuestion(37714)" class="btn btnq border6" value="33">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_14121" onclick="activeQuestion(14121)" class="btn btnq border6" value="34">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_41094" onclick="activeQuestion(41094)" class="btn btnq border6" value="35">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_10024" onclick="activeQuestion(10024)" class="btn btnq border6" value="36">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8674" onclick="activeQuestion(8674)"  class="btn btnq border6" value="37">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_8077" onclick="activeQuestion(8077)" class="btn btnq border6" value="38">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_24926" onclick="activeQuestion(24926)" class="btn btnq border6" value="39">
                                            </li>
                                            <li>
                                                <input type="button" id="q_btn_41611" onclick="activeQuestion(41611)" class="btn btnq border6" value="40">
                                            </li>
                                        </ul>
                                        <div id="countdown" class="countdownHolder">
                                            <span class="countDays">
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                            </span>

                                            <span class="countDiv countDiv0"></span>

                                            <span class="countHours">
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                            </span>

                                            <span class="countDiv countDiv1"></span>

                                            <span class="countMinutes">
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                            </span>

                                            <span class="countDiv countDiv2"></span>

                                            <span class="countSeconds">
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                                <span class="position">
                                                    <span class="digit static"></span>
                                                </span>
                                            </span>

                                            <span class="countDiv countDiv3"></span>
                                        </div>
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
                                    <h4 class="mb-0"><strong>25</strong></h4>
                                    <small class="text-muted-light">TOTAL</small>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="text-success mb-0"><strong>3</strong></h4>
                                    <small class="text-muted-light">CORECT</small>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="text-danger mb-0"><strong>5</strong></h4>
                                    <small class="text-muted-light">WRONG</small>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body text-center">
                                    <h4 class="text-primary mb-0"><strong>17</strong></h4>
                                    <small class="text-muted-light">LEFT</small>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="card">
                                <div class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#9</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#9</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#9</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div  class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#30</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div  class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#30</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div  class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#30</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card"  id="cau30">
                                <div  class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#312120</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div  class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#30</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div  class="card-header bg-white">
                                    <div class="media align-items-center">
                                        <div class="media-left">
                                            <h4 class="mb-0"><strong>#30</strong></h4>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="card-title">
                                                Github command to deploy comits?
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git push</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git commit -m "message"</span>
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="custom-control custom-checkbox mb-0">
                                            <input type="checkbox" class="custom-control-input">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description">git pull</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>

            </div>
            <!--            <div class="ele-right-side col-sm-3  no-padding">
                            <div class="pricing-box-item left-center-box text-center">
                                <div class="pricing-terms">
                                    <h6> <span >Thi thử THPT Quốc gia Lớp 12</span></h6>
                                    <h6>Thời gian làm bài: <span>50 phút</span></h6>
                                    <h6>Tổng số câu hỏi: <span>40 câu</span></h6>
                                </div>
                                <div class="pricing-heading class-image">
                                    <h3 class="class-text" style="padding-top:3px;">
                                        <strong>Thời gian</strong><br>
                                        <span id="countDownTimeMunute">48</span> : <span id="countDownTime">58</span>
                                    </h3>
                                </div>
                                <div class="pricing-action">
                                    <input type="button" id="btn2" onclick="finish()" class="btn btn-mediumlg btn-lg btn-theme" value="Nộp bài">
                                </div>
                            </div>
                        </div>-->
        </div>
    </div>
</div>
<style>
    html{
        height: 100% !important;
        overflow: hidden !important; 
        position: relative !important;
    }
    body{
        background: #F9F9F9 !important;
        height: 100% !important;
        position: relative !important
    }
    .content-wrapper{
        height: 100vh
    }
</style>
<script src="${pageContext.request.contextPath}/share/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<!--<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">-->

<!--<script>
                            $(function () {
                                $("body").addClass(" bootstrap bd-body-1 bd - homepage bd - pagebackground - 3  bd - margins");
                            });
</script>-->

<script>
                                                    $(function () {

                                                        var note = $('#note'),
                                                                ts = new Date(2012, 0, 1),
                                                                newYear = true;

                                                        if ((new Date()) > ts) {
                                                            // The new year is here! Count towards something else.
                                                            // Notice the *1000 at the end - time must be in milliseconds
                                                            ts = (new Date()).getTime() + 10 * 24 * 60 * 60 * 1000;
                                                            newYear = false;
                                                        }

                                                        $('#countdown').countdown({
                                                            timestamp: ts,
                                                            callback: function (days, hours, minutes, seconds) {

                                                                var message = "";

                                                                message += days + " day" + (days == 1 ? '' : 's') + ", ";
                                                                message += hours + " hour" + (hours == 1 ? '' : 's') + ", ";
                                                                message += minutes + " minute" + (minutes == 1 ? '' : 's') + " and ";
                                                                message += seconds + " second" + (seconds == 1 ? '' : 's') + " <br />";

                                                                if (newYear) {
                                                                    message += "left until the new year!";
                                                                } else {
                                                                    message += "left to 10 days from now!";
                                                                }

                                                                note.html(message);
                                                            }
                                                        });

                                                    });

</script>