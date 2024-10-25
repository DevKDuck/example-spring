package com.devkduck.mvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devkduck.configuration.http.BaseResponse;
import com.devkduck.mvc.domain.Board;
import com.devkduck.mvc.parameter.BoardParameter;
import com.devkduck.mvc.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
/*
 * 게시판 컨트롤러
 */

@RestController
@RequestMapping("/board")
@Tag(name = "게시판 API" , description = "게시판 API 문서입니다.")
public class BoardController {
	
	@Autowired
	private BoardService boardService; 
	
	//게시판 검색
	@GetMapping
	@Operation(summary = "목록 조회", description = "게시물 목록을 조회합니다.")
	public BaseResponse<List<Board>> getList(){
		return new BaseResponse<List<Board>>(boardService.getList());
	}
	
	@GetMapping("/{boardSeq}")
	@Operation(summary = "상세 조회", description = "게시물 번호에 해당하는 상세 정보를 조회합니다.", 
		parameters = { 
				@Parameter(name = "boardSeq", description = "게시물 번호", required = true, example = "1")
		})
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		return new BaseResponse<Board>(boardService.get(boardSeq));
	}
	
	//실무에서 데이터 수정,삭제 put, delete 주로 사용
	//등록 수정 , 처리
	@PutMapping("/save")
	@Operation(summary = "등록/수정", description = "게시물 저장 및 수정합니다.", 
	parameters = { 
			@Parameter(name = "boardSeq", description = "게시물 번호", example = "1"),
			@Parameter(name = "title", description = "제목", example = "spring_title"),
			@Parameter(name = "contents", description = "내용", example = "spring_contents")
	})
	public BaseResponse<Integer> save(@RequestBody BoardParameter board) {
		boardService.save(board);
		return new BaseResponse<Integer>(board.getBoardSeq());
	}
	
	@DeleteMapping("/delete/{boardSeq}")
	@Operation(summary = "삭제", description = "게시물 삭제합니다.", 
	parameters = { 
			@Parameter(name = "boardSeq", description = "게시물 번호", example = "1"),
	})
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board==null) {
			return new BaseResponse<Boolean>(false);
		}	
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}

}


