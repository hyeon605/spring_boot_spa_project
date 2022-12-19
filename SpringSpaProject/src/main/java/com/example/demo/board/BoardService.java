package com.example.demo.board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.mybatis.BoardMapper;

@Service
@Transactional
public class BoardService {
	PageVo pVo;
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	Object savePoint;

	@Autowired	//동일한 클래스명을 가져옴.
	BoardMapper mapper;
	
	public List<BoardVo> select(PageVo pVo){ 
		//totSize 계산
		int totSize = mapper.totList(pVo);
		pVo.setTotSize(totSize);
		this.pVo = pVo;
		
		List<BoardVo> list = mapper.select(pVo); 
		return list; 
	}

	public List<BoardVo> board10(){
		List<BoardVo> list = mapper.board10();
		return list;
	}
	
	public BoardVo view(int sno, String up) {
		BoardVo bVo = null;
		if(up != null && up.equals("up")) {
			mapper.hitUp(sno);
		}
		bVo = mapper.view(sno);
		List<AttVo> attList = mapper.attList(sno);
		bVo.setAttList(attList);
		return bVo;
	}
	
	public boolean delete(BoardVo bVo){
		boolean b = true;
		
		//자신의 글에 댓글이 있는지 판단하기
		//같은 grp 안에 자신의 seq보다 1 더 큰 seq의 자료에서 deep이 자신보다 큰것이 있으면 댓글이 있는 것임.
		int replCnt = mapper.replCheck(bVo);
		
		if(replCnt>0) {
			b = false;
			return b;
		}
		
		//sno에 해당하는 테이블 삭제
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(bVo);
		if(cnt<1) {
			b = false;
		}else {
			//sno를 pSno로 바꾸어 첨부 테이블에서 첨부파일 목록 가져오기
			List<String> attList = mapper.delFileList(bVo.getSno());
			// 첨부 테이블 삭제
			if(attList.size()>0) {
				cnt = mapper.attDeleteAll(bVo.getSno());
				if(cnt>0) {
					//첨부 파일 삭제
					if(attList.size()>0) {
						String[] delList = attList.toArray(new String[0]);
						fileDelete(delList);
					}
				}else {
					b = false;
				}
			}
		}
		
		if(b) manager.commit(status);
		else status.rollbackToSavepoint(savePoint);
		
		return b;
	}
	
	public void fileDelete(String[] delFiles) {
		
		 for(String f : delFiles){
		 	File file = new File(FileUploadController.path + f);
		 	if(file.exists()) file.delete();
		 }
	}
	
	public boolean insertR(BoardVo vo) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		boolean flag = true;
		
		int cnt = mapper.insertR(vo);
		if(cnt<1) {
			status.rollbackToSavepoint(savePoint);
			flag = false;
		}else if(vo.getAttList().size()>0) {
			int attCnt = mapper.insertAttList(vo.getAttList());
			if(attCnt<0) flag = false;
		}
		if(flag) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
			String[] delFiles = new String[vo.getAttList().size()];
			for(int i=0; i<vo.getAttList().size(); i++) {
				delFiles[i] = vo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
		}
		return flag;
	}
	
	public void insertAttList(List<AttVo> attList) {
		int cnt = mapper.insertAttList(attList);
		if(cnt>0) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
	}
	
	public boolean updateR(BoardVo bVo, String[] delFiles) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		
		boolean flag = true;
		int cnt = mapper.update(bVo);
		if(cnt<1) {
			status.rollbackToSavepoint(savePoint);
			flag = false;
		}else if(bVo.getAttList().size()>0) {
			int attCnt = mapper.attUpdate(bVo);
			if(attCnt<1) {
				flag = false;
			}
		}
		
		if(flag) {
			manager.commit(status);
			if(delFiles != null && delFiles.length>0) {
				//첨부파일 데이터 삭제
				cnt = mapper.attDelete(delFiles);
				if(cnt>0) {
					fileDelete(delFiles);	//파일 삭제
				}else {
					flag = false;
				}
			}
		}else {
			status.rollbackToSavepoint(savePoint);
			
			delFiles = new String[bVo.getAttList().size()];
			for(int i=0; i<bVo.getAttList().size(); i++) {
				delFiles[i] = bVo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
		}
		return flag;
	}
	
	public boolean replR(BoardVo bVo) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		
		boolean flag = true;
		mapper.seqUp(bVo);
		int cnt = mapper.replR(bVo);
		if(cnt<1) {
			status.rollbackToSavepoint(savePoint);
			flag = false;
		}else if(bVo.getAttList().size()>0) {
			int attCnt = mapper.insertAttList(bVo.getAttList());
			if(attCnt<0) flag = false;
		}
		
		if(flag) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
			
			String[] delFiles = new String[bVo.getAttList().size()];
			for(int i=0; i<bVo.getAttList().size(); i++) {
				delFiles[i] = bVo.getAttList().get(i).getSysFile();
			}
			fileDelete(delFiles);
		}
		return flag;
	}
	
	
	public PageVo getpVo() {
		return pVo;
	}
}
