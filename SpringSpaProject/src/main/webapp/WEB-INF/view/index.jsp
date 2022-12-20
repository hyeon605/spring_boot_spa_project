<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='css/index.css'/>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src='js/index.js'></script>
</head>
<body>
<div id='main'>
    <header>
        <nav>
            <a href="/">서연이의 디자인 감각</a>
            <a href="#" class='btnGuestBook'>방명록이다 자식들아 너네가 뭘 알아~</a>
            <a href="#" class='btnBoard'>게시판에 들어가서 글 좀 써라 이것들아 !!</a>
        </nav>
    </header>
    
    <div id='section'>
	    <div class='guestbook'>
		    <h3>최신 방명록</h3>
	    </div>
	    <div class='board'>
	        <h3>최신 게시물</h3>
	    </div>
    </div>
    
    <footer>
        <font size='5' color='#00f'>S P A</font><br/>
        servlet,jstl,jquery,mysql로 만들어진 Single Page Application  ㅜㅜㅜ
    </footer>
</div>
</body>
</html>