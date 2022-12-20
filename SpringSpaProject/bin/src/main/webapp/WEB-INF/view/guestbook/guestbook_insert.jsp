<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	<!-- select에서 정적으로 삽입된 페이지이므로 css, js, taglib 등 여기에도 그대로 적용되어 기재하지않아도 된다. -->
<title>guestbook/guestbook_insert.jsp</title>
</head>
<body>
<form name='frm_guestbook_insert' class='frm_guestbook_insert' method='post'>
	<label>작성자</label>
	<input type='text' name='id'/>
	<label>작성일</label>
	<output>
		<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd"/>
 	</output><br/>
	<textarea rows="7" cols="40" name='doc'></textarea>
	<br/>
	<label>암호</label>
	<input type='password' name='pwd'/>
	<input type='button' value='작성' class='btnGuestbookSave'/>
</form>
</body>
</html>