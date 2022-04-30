package com.royce.memolist.memo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;
import com.royce.memolist.utils.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Memo extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long memoIdx;

	@Column
	protected String memoTitle;

	@Column(name = "detail", nullable = false)
	protected String memoDetail;

	@Column
	protected int memoLength;

	@Column
	protected boolean isSecret;

	@Builder
	public Memo(String memoTitle, String memoDetail, int memoLength, boolean isSecret) {
		this.memoTitle = memoTitle;
		this.memoDetail = memoDetail;
		this.memoLength = memoLength;
		this.isSecret = isSecret;
	}

	public void updateToSecret(MemoSecretSaveReq updateReq) {
		this.memoTitle = updateReq.getMemoTitle();
		this.memoDetail = updateReq.getMemoDetail();
		this.memoLength = updateReq.getMemoDetail().length();
		this.isSecret = true;
	}

	public void updateNormal(MemoSaveReq saveReq) {
		this.memoTitle = saveReq.getMemoTitle();
		this.memoDetail = saveReq.getMemoDetail();
		this.memoLength = saveReq.getMemoDetail().length();
	}
}
