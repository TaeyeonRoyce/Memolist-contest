package com.royce.memolist.memo.model.dto;

import com.royce.memolist.memo.model.Memo;

import lombok.Builder;

public class MemoRes extends MemoServletDto {

	@Builder
	public MemoRes(Memo memoEntity) {
		this.memoTitle = memoEntity.getMemoTitle();
		this.memoDetail = memoEntity.getMemoDetail();
		this.memoLength = memoEntity.getMemoLength();
		this.isSecret = memoEntity.isSecret();
		this.memoPwd = memoEntity.getMemoPwd();
	}
}
