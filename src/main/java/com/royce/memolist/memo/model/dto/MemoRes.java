package com.royce.memolist.memo.model.dto;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.SecretMemo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class MemoRes extends MemoServletDto {

	@Builder
	public MemoRes(Memo memoEntity) {
		this.memoTitle = memoEntity.getMemoTitle();
		this.memoDetail = memoEntity.getMemoDetail();
		this.memoLength = memoEntity.getMemoLength();
		this.createdAt = memoEntity.getCreatedAt();
		this.lastModifiedAt = memoEntity.getLastModifiedAt();
	}

	@Builder
	public MemoRes(SecretMemo secretMemo) {
		this.memoTitle = secretMemo.getMemoTitle();
		this.memoDetail = secretMemo.getMemoDetail();
		this.memoLength = secretMemo.getMemoLength();
		this.createdAt = secretMemo.getCreatedAt();
		this.lastModifiedAt = secretMemo.getLastModifiedAt();
		this.secretLevel = secretMemo.getSecretLevel();
		this.memoPwd = secretMemo.getMemoPwd();
		this.isSecret = secretMemo.isSecret();
	}
}
