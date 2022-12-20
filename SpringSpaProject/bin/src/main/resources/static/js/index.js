/**
 * index.js
 */
/* 최신 방명록 10개 */ 
$('#section>.guestbook').load('/guestbook/guestbook10');	/* 메인화면 들어가자마자 바로 뿌려주게*/ 

 $('.btnBoard').on('click',function(){
	 $('#section').load('/board/board_select');
 })
 
 $('.btnGuestBook').on('click',function(){
	 $('#section').load('/guestbook/guestbook_select');
 })
 
 /* 최근 게시물 10개 */
 $('#section>.board').load('/board/board10');