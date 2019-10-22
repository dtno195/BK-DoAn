<%-- 
    Document   : login
    Created on : Sep 14, 2018, 11:14:43 AM
    Author     : lienptk
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/share/image/hoa.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Elearning | Đăng ký thành viên</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/dist/css/AdminLTE.min.css">
        <link href="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
        <!-- iCheck -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/share/AdminLTE/plugins/iCheck/square/blue.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <b>Đăng ký thành viên</b>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <form action="signup.html" method="post">
                    <div class="form-group has-feedback">
                        <input name="userName" id="userName" type="text" class="form-control" placeholder="Tên đăng nhập">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="password" id="password" type="password" class="form-control" placeholder="Mật khẩu">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="fullName" id="fullName" type="text" class="form-control" placeholder="Họ và tên">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="aspiration" id="aspiration" type="text" class="form-control" placeholder="Nguyện vọng">
                        <span class="glyphicon glyphicon-ban-circle form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="dateOfBirth" id="dateOfBirth" type="text" class="form-control" autocomplete="false" placeholder="Ngày sinh">
                        <span class="glyphicon glyphicon-calendar form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="email" id="email" type="email" class="form-control" placeholder="Email">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="highSchool" id="highSchool" type="text" class="form-control" placeholder="Trường học">
                        <span class="glyphicon glyphicon-scale form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Đăng ký</button>
                        </div>
                    </div>
                </form>
                <a href="#">Bạn đã có tài khoản của hệ thống</a><br>
                <a href="Login.html" class="text-center">Đăng nhập</a>

            </div>
        </div>

        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/plugins/iCheck/icheck.min.js"></script>
        <script src="${pageContext.request.contextPath}/share/AdminLTE/bower_components/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
        
        <script>
            $('#dateOfBirth').datepicker({
                autoclose: true,
                duration: "fast",
                changeMonth: true,
                changeYear: true,
                format: 'dd/mm/yyyy',
                constrainInput: true,
                maxDate: 0,
                yearRange: "-70:+0"
            });
        </script>
    </body>
</html>
