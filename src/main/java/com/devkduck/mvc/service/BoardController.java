package com.devkduck.mvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devkduck.mvc.controller.BoardService;
import com.devkduck.mvc.domain.Board;

/*
 * 게시판 컨트롤러
 */

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService; 
	
	//게시판 검색
	@GetMapping
	public List<Board> getList(){
		return boardService.getList();
	}
	
	@GetMapping("/{boardSeq}")
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	
	//실무에서 데이터 수정,삭제 put, delete 주로 사용
	//등록 수정 , 처리
	@GetMapping("/save")
	public void save(Board board) {
		boardService.save(board);
	}
	
	@GetMapping("/delete/{boardSeq}")
	public void delete(@PathVariable int boardSeq) {
		boardService.delete(boardSeq);
	}

}
