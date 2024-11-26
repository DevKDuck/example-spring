package com.devkduck.TipBoard.domain;

import java.time.LocalDateTime;

public class TipComment {
    private Long commentId;
    private String commentContetns;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private Long tipId;
}
