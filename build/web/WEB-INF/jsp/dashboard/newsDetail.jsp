<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/share/SmartAdmin/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<!--<link href="${pageContext.request.contextPath}/share/SmartAdmin/css/smartadmin-production.min.css" rel="stylesheet" type="text/css"/>-->
<!--<link href="${pageContext.request.contextPath}/share/SmartAdmin/css/smartadmin-production-plugins.min.css" rel="stylesheet" type="text/css"/>-->
<div class="row">
    <div class="header col-sm-12" style="margin-top:20px">
        <div class="btn-group btn-breadcrumb">
            <a href="/Home/Index" class="btn btn-default"><i class="fa fa-home"></i></a>
            <a style="margin-left:10px;" href="javascript:void(0)" class="btn">${tblNewsDTO.title}</a>
        </div>
    </div>

    <div class="col-sm-12">
        <div id="itemContent">
            <h2 class="title-new" style="font-weight:bold; font-size: 20px !important;">${tblNewsDTO.title}</h2>
            <p style="color: #65666a;font-size: 11px;margin-left: 2px;">${tblNewsDTO.dateCreated}</p>
            <div style="font-size:14px;">
                ${tblNewsDTO.content}
            </div>
            <div class="col-sm-12" style="color: #65666a;font-size: 11px;text-align:right;margin-bottom: 10px;font-weight: initial;">
                Tác giả : Admin
            </div>
        </div>
    </div>
    <div class="timeline-seperator text-center"> <span>Bình luận</span>
        <div class="btn-group pull-right">
            <a href="javascript:void(0);" data-toggle="dropdown" class="btn btn-default btn-xs dropdown-toggle"><span class="caret single"></span></a>
            <ul class="dropdown-menu text-left">
                <li>
                    <a href="javascript:void(0);">Hide this post</a>
                </li>
                <li>
                    <a href="javascript:void(0);">Hide future posts from this user</a>
                </li>
                <li>
                    <a href="javascript:void(0);">Mark as spam</a>
                </li>
            </ul>
        </div> 
    </div>
    <div class="chat-body no-padding profile-message">
        <div class="col-sm-12 row">
            <ul>
                <c:forEach items="${listComment}" var="obj">
                    <li class="message">
                        <img src="${pageContext.request.contextPath}/share/image/sunny.png" class="online" alt="sunny">
                        <span class="message-text"> <a href="javascript:void(0);" class="username">${obj.fullName}</a> ${obj.content}</span>
                        <span style="float: right"><fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value="${obj.dateComment}"/></span>
                    </li>
                </c:forEach>
                <!--            <li class="message message-reply">
                                
                            </li>-->
            </ul>
        </div>
        <div class="col-sm-12">&nbsp;</div>
        <div class="col-sm-12 ">
            <form action="/Elearning/TblNews/newsdetails.html" method="POST">
                <textarea placeholder="Nội dung muốn bình luận" rows="20" 
                          name="content" id="comment_text" cols="40" 
                          class="ui-autocomplete-input" autocomplete="off" role="textbox" 
                          aria-autocomplete="list" aria-haspopup="true" onkeypress="actionComment();">${content}</textarea>                    
                <!--<input name="content" value="${content}" class="form-control input-xs" placeholder="Type and enter" type="text" onkeypress="actionComment();">-->
                <input value="${newId}" name="id" hidden="true">
                <!--                    <input type="submit" id="actionComment" hidden="true">-->
                <a class="btn btn-primary" style="color: white" href="javascript:void(0)" onclick="actionComment();">Post</a>
            </form>
        </div>

    </div>
</div>
<script type="text/javascript">
    function actionComment() {
        $("#actionComment").click();
    }
</script>
<style>

    #comment_text {
        margin-top: 10px;
        width: 100%;
        height: 100px;
        -moz-border-bottom-colors: none;
        -moz-border-left-colors: none;
        -moz-border-right-colors: none;
        -moz-border-top-colors: none;
        background: none repeat scroll 0 0 rgba(0, 0, 0, 0.07);
        border-color: -moz-use-text-color #FFFFFF #FFFFFF -moz-use-text-color;
        border-image: none;
        border-radius: 6px 6px 6px 6px;
        border-style: none solid solid none;
        border-width: medium 1px 1px medium;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.12) inset;
        color: #555555;
        font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
        font-size: 1em;
        line-height: 1.4em;
        padding: 5px 8px;
        transition: background-color 0.2s ease 0s;
    }


    #comment_text:focus {
        background: none repeat scroll 0 0 #FFFFFF;
        outline-width: 0;
        border: 1px solid black;
    }
    #content_wrapper{
        background-color: #fff;
    }
    #itemContent {
        text-align: left;
        margin-left: 15px;
    }

    #itemContent title-new {
        text-align: left;
        font-weight: bold;
        font-size: 22px;
    }
    #itemContent h2 {
        font-size: 15px !important;
        font-weight: bold;
    }

    .btn-breadcrumb .btn:not(:last-child):after {
        content: " ";
        display: block;
        width: 0;
        height: 0;
        border-top: 17px solid transparent;
        border-bottom: 17px solid transparent;
        border-left: 10px solid white;
        position: absolute;
        top: 50%;
        margin-top: -17px;
        left: 100%;
        z-index: 3;
    }

    .btn-breadcrumb .btn:not(:last-child):before {
        content: " ";
        display: block;
        width: 0;
        height: 0;
        border-top: 17px solid transparent;
        border-bottom: 17px solid transparent;
        border-left: 10px solid rgb(173, 173, 173);
        position: absolute;
        top: 50%;
        margin-top: -17px;
        margin-left: 1px;
        left: 100%;
        z-index: 3;
    }

    /** The Spacing **/
    .btn-breadcrumb .btn {
        padding: 6px 12px 6px 24px;
    }

    .btn-breadcrumb .btn:first-child {
        padding: 6px 6px 6px 10px;
    }

    .btn-breadcrumb .btn:last-child {
        padding: 6px 18px 6px 24px;
    }

    /** Default button **/
    .btn-breadcrumb .btn.btn-default:not(:last-child):after {
        border-left: 10px solid #fff;
    }

    .btn-breadcrumb .btn.btn-default:not(:last-child):before {
        border-left: 10px solid #ccc;
    }

    .btn-breadcrumb .btn.btn-default:hover:not(:last-child):after {
        border-left: 10px solid #ebebeb;
    }

    .btn-breadcrumb .btn.btn-default:hover:not(:last-child):before {
        border-left: 10px solid #adadad;
    }

</style>