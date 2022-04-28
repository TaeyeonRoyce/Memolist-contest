package com.royce.memolist.memo;

import static com.royce.memolist.utils.BaseResponseStatus.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.SecretLevel;
import com.royce.memolist.memo.model.SecretMemo;
import com.royce.memolist.memo.model.dto.MemoRes;
import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;
import com.royce.memolist.utils.BaseResponseEntity;

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

		List<Memo> all = memoRepository.findAll();
		Memo postedMemo = all.get(1);
		assertThat(postedMemo.getMemoDetail()).isEqualTo(testDetail);
		assertThat(postedMemo.getMemoLength()).isEqualTo(memoLen);
	}

	@DisplayName("비밀_메모_저장")
	@Test
	public void saveSecretMemo() {
		//given
		String testTitle = "Title2";
		String testDetail = "This is test secret memo with password = password!";
		SecretLevel testLevel = SecretLevel.HIGH;
		String testPwd = "password";

		MemoSecretSaveReq memoSaveReq = new MemoSecretSaveReq(testTitle, testDetail, testLevel, testPwd);
		String url = BASE_URL + port + "/memo/secret";

		//when
		ResponseEntity<BaseResponseEntity> response = restTemplate.postForEntity(url,
			memoSaveReq, BaseResponseEntity.class);
		/*

		 */

		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMessage()).isEqualTo(SUCCESS.getMessage());

		List<Memo> all = memoRepository.findAll();
		SecretMemo postedMemo = (SecretMemo)all.get(1);

		assertThat(postedMemo.isSecret()).isTrue();
		assertThat(postedMemo.getMemoPwd()).isEqualTo(testPwd);
	}
}
