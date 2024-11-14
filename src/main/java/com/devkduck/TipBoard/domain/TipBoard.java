package com.devkduck.TipBoard.domain;

import com.devkduck.TipBoard.dto.TipRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipBoard {
    private Long id;
    private String tipTitle;
    private String tipWriter;
    private String tipContents;
    private int tipHits;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public static TipBoard createTip(TipRequestDTO tipRequestDTO, Long userId, String userName) {
        return TipBoard.builder()
                .tipTitle(tipRequestDTO.getTipTitle())
                .tipWriter(userName)
                .tipContents(tipRequestDTO.getTipContents())
                .tipHits(0)
                .userId(userId)
                .build();
    }
}
