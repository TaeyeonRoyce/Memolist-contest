package com.royce.memolist.memo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class SecretMemo extends Memo {

	private String memoPwd;

	@Enumerated(EnumType.STRING)
	private SecretLevel secretLevel;

	@Builder
	public SecretMemo(String memoTitle, String memoDetail, int memoLength, boolean isSecret, String memoPwd,
		SecretLevel secretLevel) {
		super(memoTitle, memoDetail, memoLength, isSecret);
		this.memoPwd = memoPwd;
		this.secretLevel = secretLevel;
	}

	//Builder 상속 충돌 방지
	public static class SecretMemoBuilder extends MemoBuilder {
		SecretMemoBuilder() {
			super();
		}
	}
}
