package com.devkduck.TipBoard.Mapper;

import com.devkduck.TipBoard.domain.TipBoard;
import com.devkduck.TipBoard.dto.TipRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TipBoardMapper {
    //꿀팁 추가
    int insertTip(TipBoard tipBoard);
    //꿀팁 리스트 가져오기
    List<TipBoard> getTipList();
    //조회수 증가
    void updateHits(Long id);
    //자세히 보기
    TipBoard detailTip(Long id);
    //꿑팁 업데이트
    int updateTip(Map<String,Object> params);
}
