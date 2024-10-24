package com.devkduck.mvc.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Board {
	private int boardSeq;
	private String title;
	private String contents;
	private LocalDateTime regDate;
}
