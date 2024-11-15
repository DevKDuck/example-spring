package com.devkduck.TipBoard.service;

import com.devkduck.TipBoard.Mapper.TipBoardMapper;
import com.devkduck.TipBoard.domain.TipBoard;
import com.devkduck.TipBoard.dto.TipRequestDTO;
import com.devkduck.TipBoard.dto.TipResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipBoardService {

    private final TipBoardMapper tipBoardMapper;

    public int insertTip(TipRequestDTO tipRequestDTO, Long userId, String userName) {
        TipBoard tipBoard = TipBoard.createTip(tipRequestDTO, userId, userName);
        return tipBoardMapper.insertTip(tipBoard);
    }
    public List<TipBoard> getTipList() {
        return tipBoardMapper.getTipList();
    }

    public TipBoard detailTip(Long id){
        //조회수 +1
        tipBoardMapper.updateHits(id);

        //정보 가져오기
        TipBoard tip = tipBoardMapper.detailTip(id);
        return tip;
    }
}
