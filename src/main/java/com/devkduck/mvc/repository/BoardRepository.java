package com.devkduck.mvc.repository;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.devkduck.mvc.domain.Board;

@Repository
public interface BoardRepository {
	
	//게시판 검색
	List<Board> getList();
	Board get(int boardSeq);
	
	//저장
	void save(Board board);
	
	//수정
	void update(Board board);
	
	//삭제
	void delete(int boardSeq);

}
