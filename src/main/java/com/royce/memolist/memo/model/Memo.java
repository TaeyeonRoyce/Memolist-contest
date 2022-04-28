package com.royce.memolist.memo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Memo {

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

}
