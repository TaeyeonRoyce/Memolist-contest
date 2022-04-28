package com.royce.memolist.memo;

import static com.royce.memolist.utils.BaseResponseStatus.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.royce.memolist.memo.model.dto.MemoRes;
import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.utils.BaseResponseEntity;
import com.royce.memolist.utils.BaseResponseStatus;

@DisplayName("Memo_API_Test")
public class MemoApiTest extends WebTest {

	@BeforeEach
	void setDefaultData() {
		MemoSaveReq test1 = new MemoSaveReq("Title1", "This is test memo!");
		memoRepository.save(test1.toEntity());
	}

	@DisplayName("일반_메모_저장")
	@Test
	public void saveMemo() {
	    //given
		String testTitle = "Title2";
		String testDetail = "This is test memo ver.2!";
		int memoLen = testDetail.length();

		MemoSaveReq memoSaveReq = new MemoSaveReq(testTitle, testDetail);
		String url = BASE_URL + port + "/memo";

		//when
		ResponseEntity<BaseResponseEntity> response = restTemplate.postForEntity(url,
			memoSaveReq, BaseResponseEntity.class);
		/*
			{
				"isSuccess": true,
				"code": 1000,
				"message": "요청에 성공하였습니다.",
				"result": {
					"memoTitle": "Title1",
					"memoDetail": "Hello world!",
					"memoLength": 0,
					"memoPwd": "",
					"secret": false
				}
			}
		 */

		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMessage()).isEqualTo(SUCCESS.getMessage());

	}

}
