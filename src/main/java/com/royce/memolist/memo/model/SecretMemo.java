package com.royce.memolist.memo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class SecretMemo extends Memo {

	private String memoPwd = null;

	@Enumerated(EnumType.STRING)
	private SecretLevel secretLevel = null;

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

	public void updateToSecret(MemoSecretSaveReq updateReq) {
		this.memoTitle = updateReq.getMemoTitle();
		this.memoDetail = updateReq.getMemoDetail();
		this.memoLength = updateReq.getMemoDetail().length();
		this.isSecret = true;
		this.memoPwd = updateReq.getMemoPwd();
		this.secretLevel = updateReq.getSecretLevel();
	}

	public void toNormalMemo() {
		this.isSecret = false;
		this.memoPwd = null;
		this.secretLevel = null;
	}
}
