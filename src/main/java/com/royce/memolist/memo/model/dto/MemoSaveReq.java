package com.royce.memolist.memo.model.dto;

import com.royce.memolist.memo.model.Memo;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemoSaveReq extends MemoServletDto {

	@Builder
	public MemoSaveReq(String memoTitle, String memoDetail) {
		this.memoTitle = memoTitle;
		this.memoDetail = memoDetail;
		this.memoLength = memoDetail.length();
	}

	public Memo toEntity() {
		return Memo.builder()
			.memoTitle(memoTitle)
			.memoDetail(memoDetail)
			.memoLength(memoLength)
			.isSecret(false)
			.memoPwd("")
			.build();
	}
}
