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
	}

	public Memo toEntity() {
		return Memo.builder()
			.memoTitle(memoTitle)
			.memoDetail(memoDetail)
			.memoLength(memoDetail.length())
			.isSecret(false)
			.build();
	}
}
