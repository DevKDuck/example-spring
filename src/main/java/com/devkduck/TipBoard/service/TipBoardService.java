package com.devkduck.TipBoard.service;

import com.devkduck.TipBoard.Mapper.TipBoardMapper;
import com.devkduck.TipBoard.domain.TipBoard;
import com.devkduck.TipBoard.dto.TipRequestDTO;
import com.devkduck.TipBoard.dto.TipResponseDTO;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TipBoardService {

    private final TipBoardMapper tipBoardMapper;
    private final ParameterNamesModule parameterNamesModule;

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

    public int updateTip(Long id, TipRequestDTO tipRequestDTO){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("tipTitle", tipRequestDTO.getTipTitle());
        params.put("tipContents", tipRequestDTO.getTipContents());
        return tipBoardMapper.updateTip(params);
    }

    public int deleteTip(Long id){
        return tipBoardMapper.deleteTip(id);
    }

    public TipBoard findAllById(Long id){
        return tipBoardMapper.findAllById(id);
    }
}
