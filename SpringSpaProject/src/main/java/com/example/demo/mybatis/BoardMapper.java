package com.example.demo.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.board.AttVo;
import com.example.demo.board.BoardVo;
import com.example.demo.board.PageVo;



@Repository	//저장성(메모리에 저장)
@Mapper
public interface BoardMapper {
	public int totList(PageVo pVo);
	public List<BoardVo> select(PageVo pVo);
	public List<BoardVo> board10();
	//view
	public BoardVo view(int sno);
	public void hitUp(int sno);
	public List<AttVo> attList(int sno);
	//delete
	public int replCheck(BoardVo bVo);
	public int delete(BoardVo bVo);
	public List<String> delFileList(int sno);
	public int attDeleteAll(int sno);
	//insert
	public int insertR(BoardVo bVo);
	public int insertAttList(List<AttVo> attList);
	//update
	public int update(BoardVo bVo);
	public int attUpdate(BoardVo bVo);
	public int attDelete(String[] delFiles);
	//repl
	public void seqUp(BoardVo bVo);
	public int replR(BoardVo bVo);
}
