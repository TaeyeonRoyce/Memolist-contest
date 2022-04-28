package com.royce.memolist.memo.model.dto;

import lombok.Getter;

@Getter
public class MemoServletDto {
	protected String memoTitle;
	protected String memoDetail;
	protected int memoLength;
	protected boolean isSecret;
	protected String memoPwd;

	// public MemoServletDto(String memoTitle, String memoDetail, int memoLength, boolean isSecret, String memoPwd) {
	// 	this.memoTitle = memoTitle;
	// 	this.memoDetail = memoDetail;
	// 	this.memoLength = memoLength;
	// 	this.isSecret = isSecret;
	// 	this.memoPwd = memoPwd;
	// }
}
