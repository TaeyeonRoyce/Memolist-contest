package com.royce.memolist.utils;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
	/**
	 * 1000 : 요청 성공
	 */
	SUCCESS(true, 1000, "요청에 성공하였습니다."),


	/**
	 * 2000 : Request 오류
	 */
	// Common
	REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),

	/**
	 * 3000 : Response 오류
	 */
	// Common
	RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

	FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



	/**
	 * 4000 : Database, Server 오류
	 */
	//Common
	DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
	SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");


	private final boolean isSuccess;
	private final int code;
	private final String message;

	private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}
}
