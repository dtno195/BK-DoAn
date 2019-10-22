<%-- 
    Document   : resultTest
    Created on : Nov 6, 2018, 3:48:49 AM
    Author     : phith
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="stt" value="1"/>
<div class="container" style="margin-bottom: 50px;max-width: 100%;width: 100%">
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-8" style="float: left;width: 66.666667%;text-align: center;font-size:  30px;font-weight:  bold;">KẾT QUẢ LÀM BÀI THI   </div>
            <div class="col-sm-4" style="text-align: right;float:  left;width: 33.333333%">
                <label class="col-sm-12" style="font-weight: bold">Số điểm : <fmt:formatNumber type = "number" maxIntegerDigits = "2" value = "${totalPointTest}" /></label><span></span>
                <label class="col-sm-12" style="font-weight: bold">Số câu đúng : ${totalQuestionCorrect}/${totalQuestion}</label><span></span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr  style="text-align: center">
                        <th style="width: 20px">
                            STT
                        </th>
                        <th>
                            Câu hỏi
                        </th>
                        <th>
                            Đáp án đúng 
                        </th>
                        <th>
                            Đáp án của bạn
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${lstResultOfExams}">
                        <tr class="<c:if test="${item.flagIsRight != 1}"><c:out value="danger"></c:out></c:if>">
                                    <td>
                                ${stt}
                            </td>
                            <td>
                                ${item.questionContent}
                            </td>
                            <td>
                                ${item.answerCorrectContent}
                            </td>
                            <td>
                                ${item.answerContent}
                            </td>
                        </tr>
                        <c:set var="stt" value="${stt+1}"/>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12" style="text-align: right;">
            <button type="button" id="sendThreadsOnMail" class="btn btn-success">Nhận đề thi và đáp án</button>
        </div>
    </div>
</div>
<div id="sendMailDialog" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Yêu cầu gửi kết quả về hòm thư</h4>
            </div>
            <div class="modal-body">
                <div class="form-group row">
                    <label class="col-md-3 control-label text-align-right">Email</label>
                    <div class="col-md-8">
                        <input class="form-control" placeholder="Email" id="email" name="email" type="text"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="cofirmTheSendMail" class="btn btn-success" data-dismiss="modal">Xác nhận</button>
                <button type="button" id="btnClose" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    $("#sendThreadsOnMail").click(function () {
        $("#sendMailDialog").modal("show");
    });
    $("#cofirmTheSendMail").click(function () {
        $.ajax({
            async: false,
            url: "sendMailToUser.html",
            data: {
                userMail: $("#email").val()
            },
            type: "GET",
            success: function (data) {
                notif({
                    msg: "Gửi vào hòm thư thành công.",
                    type: "success"
                });
            },
            error: function (err) {
                notif({
                    msg: "Gửi mail không thành công. Kiểm ra kết nối của bạn",
                    type: "error"
                });
            }, complete: function (jqXHR, textStatus) {

            }
        });
    });
</script>