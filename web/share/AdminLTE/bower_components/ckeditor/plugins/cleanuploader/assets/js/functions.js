﻿$("#uploadButtonContainer").hide();var numberOfFilesSelected=0;
function addNewImage(a,c,b){$("#list").append($('\x3ctr id\x3d"'+a+'"\x3e\x3ctd\x3e\x3cimg src\x3d"uploads/thumb/'+a+"."+b+'" onClick\x3d"useImage(this.src);" id\x3d"addImg" data-toggle\x3d"tooltip" data-placement\x3d"right" data-original-title\x3d"Add image"/\x3e\x3c/td\x3e\x3ctd\x3e\x3cp\x3e'+a+"."+b+"\x3c/p\x3e\x3c/td\x3e\x3ctd\x3e\x3cp\x3e"+c+'kb\x3c/p\x3e\x3c/td\x3e\x3ctd\x3e\x3cbutton type\x3d"button" class\x3d"btn btn-danger pull-right" onClick\x3d"deleteImage(\''+a+"','"+b+'\');"\x3e\x3ci class\x3d"glyphicon glyphicon-trash"\x3e \x3c/i\x3e Delete\x3c/button\x3e\x3c/td\x3e\x3c/tr\x3e').hide().fadeIn(500))}
function deleteImage(a,c){$.post("delete.php",{name:a+"."+c}).done(function(b){JSON.parse(b)&&$("#"+a).fadeOut(250,"linear")})}function controlOfFileExtension(a){for(var c=["jpg","png","gif","jpeg"],b=a.length,d=0;d<b;d++){var e=a[d].name.split(".").pop().toLowerCase();0<=jQuery.inArray(e,c)&&numberOfFilesSelected++}0<numberOfFilesSelected&&$("#uploadButtonContainer").show()}function checkNumberOfFiles(){0>=numberOfFilesSelected&&$("#uploadButtonContainer").hide()}
function startUpload(){downloadObj.startUpload()}function useImage(a){newsrc=a.replace("thumb/","");a=window.location.search.match(/(?:[?&]|&)CKEditorFuncNum=([^&]+)/i);window.opener.CKEDITOR.tools.callFunction(a&&1<a.length?a[1]:null,newsrc);window.close()}$(window).load(function(){$("#loginModal").modal("show")});$(function(){$('[data-toggle\x3d"tooltip"]').tooltip()});
function checkPassword(){$("#admin_password").val()!=$("#admin_password_again").val()?($("#admin_password_again").attr("class","form-control notEqualTextfield"),$("#admin_password").attr("class","form-control notEqualTextfield")):($("#admin_password_again").attr("class","form-control equalTextField"),$("#admin_password").attr("class","form-control equalTextField"))};