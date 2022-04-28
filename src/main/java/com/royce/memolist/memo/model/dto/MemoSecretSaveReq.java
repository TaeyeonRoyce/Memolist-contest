package com.royce.memolist.memo.model.dto;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.SecretLevel;
import com.royce.memolist.memo.model.SecretMemo;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemoSecretSaveReq extends MemoServletDto {

	@Builder
	public MemoSecretSaveReq(String memoTitle, String memoDetail, SecretLevel secretLevel, String memoPwd) {
		this.memoTitle = memoTitle;
		this.memoDetail = memoDetail;
		this.secretLevel = secretLevel;
		this.memoPwd = memoPwd;
	}

	public SecretMemo toEntity() {
		return SecretMemo.builder()
			.memoTitle(memoTitle)
			.memoDetail(memoDetail)
			.memoLength(memoDetail.length())
			.isSecret(true)
			.secretLevel(secretLevel)
			.memoPwd(memoPwd)
			.build();
	}
}
