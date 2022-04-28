package com.royce.memolist.memo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.royce.memolist.utils.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Memo extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memoIdx;

	@Column(name = "title")
	private String memoTitle;

	@Column(name = "detail", nullable = false)
	private String memoDetail;

	@Column
	private int memoLength;

	@Column
	private boolean isSecret;

	@Column
	private String memoPwd;

	@Builder
	public Memo(String memoTitle, String memoDetail, int memoLength, boolean isSecret,
		String memoPwd) {
		this.memoTitle = memoTitle;
		this.memoDetail = memoDetail;
		this.memoLength = memoLength;
		this.isSecret = isSecret;
		this.memoPwd = memoPwd;
	}
}
