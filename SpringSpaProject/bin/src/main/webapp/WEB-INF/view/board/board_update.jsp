<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPA Mysql</title>
<link rel='stylesheet' href='css/board.css'>
<script src='js/board.js'></script>
</head>
<body>
<div id='board'>
    <h2>게시판</h2>
    
    <form class='frm frm_update' method='post' enctype='multipart/form-data'>
        <label>작성자</label>
        <input type='text' name='id' value='${bVo.id }'/><br/>
       
        <label>제목</label>
        <input type='text' name='subject' class='subject' value='${bVo.subject }'/><br/>
        <label></label>
        <textarea rows="5" cols="50" name='doc' class='doc'>${bVo.doc }</textarea><br/>
        <div class='attZone'>
            <label>첨부파일</label>
            <c:forEach var="att" items='${bVo.attList }'>
                <label class='delFile'>
                    <input type='checkbox' name='delFile' value='${att.sysFile }'>
                    <span>${att.oriFile }</span>
                </label>
            </c:forEach>
        </div>
        <label>첨부</label>
        <input type='file' name='attFile' multiple="multiple"/>
       
        <div class='btnZone'>
            <input type='button' value='취소' class='btnSelect'>
            <input type='button' value='수정' class='btnUpdateR'>
            <input type='text' name='nowPage' value='${pVo.nowPage }'>
            <input type='text' name='findStr' value='${pVo.findStr }'>
            <input type='text' name='sno' value='${bVo.sno }'>
        </div>
    </form>
</div>
</body>
</html>
