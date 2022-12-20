/**
 * guestbook.js
 */

 (gb = function(){
	 $('.btnSearch').on('click', function(){
       var frm = $('.frm_gb_search')[0];
       frm.nowPage.value = 1;       
       var param = $(frm).serialize();
       $('#section').load('/guestbook/guestbook_select', param);  
    });
	 
	$('.btnPrev').on('click', function(){	/*클릭한 페이지로 이동 및 그로인한 페이징처리를 위함*/
		 var frm = $('.frm_gb_search')[0];
	     frm.nowPage.value = Number(frm.nowPage.value)-1;
	     var param = $(frm).serialize();
	     $('#section').load('/guestbook/guestbook_select', param);
	});
	
	$('.btnNext').on('click', function(){	/*클릭한 페이지로 이동 및 그로인한 페이징처리를 위함*/
		 var frm = $('.frm_gb_search')[0];
	     frm.nowPage.value = Number(frm.nowPage.value)+1;
	     var param = $(frm).serialize();
	     $('#section').load('/guestbook/guestbook_select', param);	     
	});
	
	$('.btnGuestbookSave').on('click', function(){
		var frm = $('.frm_guestbook_insert')[0];
		var param = $(frm).serialize();
		$.post('/guestbook/guestbook_insert', param, function(){
			frm = $('.frm_gb_search')[0];	/*검색창의 정보를 다시 만들어서 section영역에 다시 select*/
	   	 	param = $(frm).serialize();
	   		$('#section').load('/guestbook/guestbook_select', param);	 
		});
	})
	
	gb.modifyView = function(frm){
		let div = frm.querySelector('.updateZone');
		let bz = frm.querySelector('.btnZone');
		div.style.visibility='visible';
		frm.doc.readOnly = false;
		$(bz).css('display','none');
	}
	
	gb.modifyCancel = function(frm){
		let div = frm.querySelector('.updateZone');
		let bz = frm.querySelector('.btnZone');
		div.style.visibility='hidden';
		frm.doc.readOnly = true;
		$(bz).css('display','block');
	}
	
	/* modal box */
	$('#btnClose').on('click', function(){
		$('#modal').css('display', 'none');
	});
	
	var delForm;
	gb.modalView = function(frm){
		delForm = frm;
		$('#modal').css('display', 'block');
	}
	
	/* 방명록 삭제 */
	$('#btnCheck').on('click', function(){
		delForm.pwd.value = $('#pwd').val();
		
		var frm = delForm;
		var param = $(frm).serialize();
		$.post('/guestbook/guestbook_delete', param, function(){
			frm = $('.frm_gb_search')[0];	/*검색창의 정보를 다시 만들어서 section영역에 다시 select*/
	   	 	param = $(frm).serialize();
	   		$('#section').load('/guestbook/guestbook_select', param);	 
		});
	});
	
	/* 수정 */
	gb.update = function(frm){
		var param = $(frm).serialize();
		$.post('/guestbook/guestbook_update', param, function(msg){
			if(msg != '') alert(msg);
			frm = $('.frm_gb_search')[0];	/*검색창의 정보를 다시 만들어서 section영역에 다시 select*/
	   	 	param = $(frm).serialize();
	   		$('#section').load('/guestbook/guestbook_select', param);	 
		});
	}
 })();
 
 