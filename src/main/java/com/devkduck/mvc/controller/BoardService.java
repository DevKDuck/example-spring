package com.devkduck.mvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devkduck.mvc.domain.Board;
import com.devkduck.mvc.repository.BoardRepository;

/*
 * 게시판 서비스
 */

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRespository; 
	
	//게시판 검색
	public List<Board> getList(){
		return boardRespository.getList();
	}
	public Board get(int boardSeq) {
		return boardRespository.get(boardSeq);
	}
	
	//저장
	public void save(Board board) {
		boardRespository.save(board);
	}
	
	//수정
	public void update(Board board) {
		boardRespository.update(board);
	}
	
	//삭제
	public void delete(int boardSeq) {
		boardRespository.delete(boardSeq);
	}

}
