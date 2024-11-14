package com.devkduck.TipBoard.Mapper;

import com.devkduck.TipBoard.domain.TipBoard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TipBoardMapper {
    int insertTip(TipBoard tipBoard);
}
