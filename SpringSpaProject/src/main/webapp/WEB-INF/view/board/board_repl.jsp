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
    
    <form class='frm frm_insert' method='post' enctype='multipart/form-data'>
        <label>작성자</label>
        <input type='text' name='id' value='hong'/><br/>
       
        <label>제목</label>
        <input type='text' name='subject' class='subject' value='댓글 제목'/><br/>
        <label></label>
        <textarea rows="5" cols="50" name='doc' class='doc'>댓글 내용입니다.</textarea><br/>
        <label>첨부</label>
        <input type='file' name='attFile' multiple="multiple"/>
        <div class='btnZone'>
            <input type='button' value='취소' class='btnSelect'>
            <input type='button' value='댓글저장' class='btnReplR'>
            <input type='text' name='nowPage' value='${pVo.nowPage }'>
            <input type='text' name='findStr' value='${pVo.findStr }'>
            <input type='text' name='grp' value='${bVo.grp }'/>
            <input type='text' name='seq' value='${bVo.seq }'/>
            <input type='text' name='deep' value='${bVo.deep }'/>
           
        </div>
    </form>
</div>
</body>
</html>
