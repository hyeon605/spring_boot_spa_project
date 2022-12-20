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
    <form class='frm' id='frm_view'>
        <label>아현이들</label>
        <input type='text' name='id' value='${bVo.id }'/>
        <span>${bVo.nal }</span>
        <span>[조회: ${bVo.hit }</span>
        <br/>
        <label>생각</label>
        <input type='text' name='subject' value='${bVo.subject }'/><br/>
    
        <div class='doc'>
            ${bVo.doc }
        </div>
        <div class='attFileZone'>
            <c:forEach var="att" items='${bVo.attList }'>
                <span class='attFile'>
                	<img src = '/upload/${att.sysFile }'/><br/>
                    <a href='/upload/${att.sysFile }' download='${att.oriFile }'>
                        ${att.oriFile }
                    </a>
                </span>
            </c:forEach>
        </div>
        <br/>
        <div class='btnZone'>
            <input type='button' value='아현이목록' class='btnSelect'>
            <input type='button' value='수정' class='btnUpdate'>
            <input type='button' value='삭제' class='btnDeleteR'>
            <input type='button' value='댓글' class='btnRepl'>
           
            <input type='hidden' name='findStr' value='${pVo.findStr }'/>
            <input type='hidden' name='nowPage' value='${pVo.nowPage }'/>
            <input type='hidden' name='sno' value='${bVo.sno }'/>
            <input type='hidden' name='grp' value='${bVo.grp }'/>
            <input type='hidden' name='seq' value='${bVo.seq }'/>
            <input type='hidden' name='deep' value='${bVo.deep }'/>
        </div>
       
    </form>
</div>
</body>
</html>
