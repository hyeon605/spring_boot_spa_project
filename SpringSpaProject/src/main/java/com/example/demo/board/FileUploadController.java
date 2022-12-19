package com.example.demo.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class FileUploadController {
	
	static String path = "C:\\Users\\junah\\eclipse-workspace\\SpringSpaProject\\src\\main\\resources\\static\\upload\\";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized RedirectView insertR(@RequestParam("attFile") List<MultipartFile> mul, 	//insert페이지의 attFile태그를 매개변수로 받음
						 @ModelAttribute BoardVo vo) {	//insert페이지의 그외 나머지 태그들을 매개변수로 받음
		List<AttVo> attList = new ArrayList<AttVo>();
		RedirectView rView = new RedirectView();
		try {
			System.out.println("id: "+vo.getId());
			System.out.println("subject: "+vo.getSubject());
			
			//본문 저장
			attList = fileupload(mul);
			vo.setAttList(attList);
			boolean flag = service.insertR(vo);
						
		}catch(Exception e) {
			
		}
		rView.setUrl("/board/board_select");
		return rView;	//업로드하고 나서 다시 select로!
	}
	
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile") List<MultipartFile> mul, 	//insert페이지의 attFile태그를 매개변수로 받음
			 @ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo,
			 @RequestParam(name="delFile", required = false) String[] delFile) {
		RedirectView rView = new RedirectView();
		String msg = "";
		try {
				List<AttVo> attList = fileupload(mul);
				bVo.setAttList(attList);							
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		boolean flag = service.updateR(bVo, delFile);
		
		if(!flag) msg = "수정 중 오류 발생";
		
		rView.setUrl("/board/board_select");
		return msg;	//업로드하고 나서 다시 select로!
	}
	
	@RequestMapping("/board/board_replR")
	public synchronized RedirectView replR(@RequestParam("attFile") List<MultipartFile> mul, 	//insert페이지의 attFile태그를 매개변수로 받음
						 @ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo) {	//insert페이지의 그외 나머지 태그들을 매개변수로 받음
		
		List<AttVo> attList = new ArrayList<AttVo>();
		RedirectView rView = new RedirectView();
		try {
			//파일 업로드
			attList = fileupload(mul);
			bVo.setAttList(attList);
			
			//본문 저장
			boolean flag = service.replR(bVo);
			
			service.insertAttList(attList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		rView.setUrl("/board/board_select");
		return rView;	//업로드하고 나서 다시 select로!
	}
	
	//file upload 공통부분(insertR, updateR, replR)
	public List<AttVo> fileupload(List<MultipartFile> mul) throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		
		for(MultipartFile m : mul) {
			if(m.isEmpty()) continue;
			
			//업로드파일 이름 재지정
			UUID uuid = UUID.randomUUID();	//랜덤 번호 생성
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			File temp = new File(path + oriFile);
			m.transferTo(temp);
			sysFile =(uuid.getLeastSignificantBits()*-1)+ "-" + oriFile;
			File f = new File(path + sysFile);
			temp.renameTo(f);
			
			AttVo attVo = new AttVo();
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			
			attList.add(attVo);
			
			System.out.println(m.getOriginalFilename());
			System.out.println("uuid: "+uuid.getLeastSignificantBits());
			
		}
		
		return attList;
	}
}
