package com.royce.memolist.memo.model.dto;

import java.time.LocalDateTime;

import com.royce.memolist.memo.model.SecretLevel;

import lombok.Getter;

@Getter
public class MemoServletDto {
	protected String memoTitle;
	protected String memoDetail;
	protected int memoLength;
	protected boolean isSecret;
	protected SecretLevel secretLevel;
	protected String memoPwd;
	protected LocalDateTime createdAt;
	protected LocalDateTime lastModifiedAt;

	// public MemoServletDto(String memoTitle, String memoDetail, int memoLength, boolean isSecret, String memoPwd) {
	// 	this.memoTitle = memoTitle;
	// 	this.memoDetail = memoDetail;
	// 	this.memoLength = memoLength;
	// 	this.isSecret = isSecret;
	// 	this.memoPwd = memoPwd;
	// }
}
