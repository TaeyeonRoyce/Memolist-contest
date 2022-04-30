package com.royce.memolist.utils;


import static com.royce.memolist.utils.BaseResponseStatus.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponseEntity<T> {//BaseResponse 객체를 사용할때 성공, 실패 경우
	@JsonProperty("isSuccess")
	private final Boolean isSuccess;
	private final String message;
	private final int code;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result;

	// 요청에 성공한 경우
	public BaseResponseEntity(T result) {
		this.isSuccess = SUCCESS.isSuccess();
		this.message = SUCCESS.getMessage();
		this.code = SUCCESS.getCode();
		this.result = result;
	}

	// 요청에 실패한 경우
	public BaseResponseEntity(BaseResponseStatus status) {
		this.isSuccess = status.isSuccess();
		this.message = status.getMessage();
		this.code = status.getCode();
	}
}
