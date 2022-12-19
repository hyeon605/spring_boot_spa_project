package com.example.demo.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestbookController {
	
	@Autowired
	GuestbookDao dao;
	
	@RequestMapping("/guestbook/guestbook_select")
	public ModelAndView select(GPageVo gVo) {
		ModelAndView mv = new ModelAndView();	//컨트롤러 처리 결과 후 응답할 view와 view에 전달할 값을 저장 및 전달하는 클래스
		//service or dao
		System.out.println(dao);
		System.out.println("now page : " + gVo.getNowPage());
		System.out.println("tot page : " + gVo.getTotPage());
		
		//검색어에 따른 list 가져옴
		List<GuestbookVo> list = dao.select(gVo);
		
		//list를 mv에 추가
		gVo = dao.getgVo(); //새로 갱신된 페이지 정보
		mv.addObject("gVo", gVo);	//view에 전달할 값 설정
		mv.addObject("list", list);	//위와 동일
		mv.setViewName("guestbook/guestbook_select");	//응답할 view(페이지)이름 설정
		return mv;
	}
	
	@RequestMapping("/guestbook/guestbook10")
	public ModelAndView IndexSelect() {
		ModelAndView mv = new ModelAndView();	//컨트롤러 처리 결과 후 응답할 view와 view에 전달할 값을 저장 및 전달하는 클래스
		//service or dao
		System.out.println(dao);
		
		//검색어에 따른 list 가져옴
		List<GuestbookVo> list = dao.select10();
		System.out.println("list size: "+list.size());
		//list를 mv에 추가
		mv.addObject("list", list);	//위와 동일
		mv.setViewName("guestbook/guestbook_select10");	//응답할 view(페이지)이름 설정
		return mv;
	}
	
	@RequestMapping("/guestbook/guestbook_insert")
	public void insert(GuestbookVo vo, HttpServletResponse resp) {
		boolean b = dao.insert(vo);
		
		try {
			PrintWriter out = resp.getWriter();
			if(!b) {
				out.print("<script>");
				out.print("   alert('저장 중 오류 발생')");
				out.print("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/guestbook/guestbook_delete")
	public void delete(GuestbookVo vo, HttpServletResponse resp) {
		boolean b = dao.delete(vo);
		
		try {
			PrintWriter out = resp.getWriter();
			if(!b) {
				out.print("<script>");
				out.print("   alert('삭제 중 오류 발생')");
				out.print("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/guestbook/guestbook_update")
	public String update(GuestbookVo vo, HttpServletResponse resp) {
		boolean b = dao.update(vo);
		String msg = "";
		try {
			PrintWriter out = resp.getWriter();
			if(!b) {
				msg = "수정 중 오류 발생";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
}
