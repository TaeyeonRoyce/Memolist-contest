package com.royce.memolist.memo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
	private Long memoIdx;

	@Column
	private String memoTitle;

	@Column(name = "detail", nullable = false)
	private String memoDetail;

	@Column
	private int memoLength;

	@Column
	private boolean isSecret;

	@Builder
	public Memo(String memoTitle, String memoDetail, int memoLength, boolean isSecret) {
		this.memoTitle = memoTitle;
		this.memoDetail = memoDetail;
		this.memoLength = memoLength;
		this.isSecret = isSecret;
	}
}
