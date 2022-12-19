<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
<h3>최신 방명록</h3>
	<c:forEach var='vo' items="${list}" varStatus='status'>
		<div class='item'>
			[${vo.nal }] <b>[${vo.id }]</b> <span>${vo.doc }</span>
		</div>
	</c:forEach>
</div>
