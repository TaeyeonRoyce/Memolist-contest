package com.royce.memolist.memo;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.SecretLevel;
import com.royce.memolist.memo.model.SecretMemo;
import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;

@Transactional
@DisplayName("Memo_API_Test")
public class MemoApiTest extends WebTest {

	@BeforeEach
	void setDefaultData() {
		MemoSaveReq test1 = new MemoSaveReq("Title1", "This is test memo!");
		memoRepository.save(test1.toEntity());
	}


	@DisplayName("일반_메모_저장")
	@Test
	public void saveMemoTest() throws Exception {
	    //given
		String testTitle = "Title2";
		String testDetail = "This is test memo ver.2!";
		int memoLen = testDetail.length();

		MemoSaveReq memoSaveReq = new MemoSaveReq(testTitle, testDetail);
		String url = BASE_URL + port + "/memo";

	    //when
		mvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModules(new JavaTimeModule()).writeValueAsString(memoSaveReq)))
			.andExpect(status().isOk());

	    //then
		List<Memo> all = memoRepository.findAll();
		Memo postedMemo = all.get(1);
		assertThat(postedMemo.getMemoDetail()).isEqualTo(testDetail);
		assertThat(postedMemo.getMemoLength()).isEqualTo(memoLen);
	}

	@DisplayName("비밀_메모_저장")
	@Test
	public void saveSecretMemoTest() throws	Exception {
	    //given
		String testTitle = "Title2";
		String testDetail = "This is test secret memo with password = password!";
		SecretLevel testLevel = SecretLevel.HIGH;
		String testPwd = "password";

		MemoSecretSaveReq memoSaveReq = new MemoSecretSaveReq(testTitle, testDetail, testLevel, testPwd);
		String url = BASE_URL + port + "/memo/secret";

	    //when
		mvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().registerModules(new JavaTimeModule()).writeValueAsString(memoSaveReq)))
			.andExpect(status().isOk());

		//then
		List<Memo> all = memoRepository.findAll();
		SecretMemo postedMemo = (SecretMemo)all.get(1);

		assertThat(postedMemo.isSecret()).isTrue();
		assertThat(postedMemo.getMemoPwd()).isEqualTo(testPwd);
	}

	@DisplayName("전체_메모_조회")
	@Test
	public void getAllMemoTest() throws	Exception {
		//given
		String testTitle = "Title2";
		String testDetail = "This is test secret memo with password = password!";
		SecretLevel testLevel = SecretLevel.HIGH;
		String testPwd = "password";

		int saveCnt = 3;

		MemoSecretSaveReq memoSaveReq = new MemoSecretSaveReq(testTitle, testDetail, testLevel, testPwd);

		for (int i = 0; i < saveCnt; i++) {
			memoRepository.save(memoSaveReq.toEntity());
		}

		String url = BASE_URL + port + "/memo";

		//when
		mvc.perform(get(url)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result.length()").value(saveCnt + 1));
	}

	@DisplayName("메모_단일_조회")
	@Test
	public void getMemoByIdTest() throws Exception {
		//given
		String testTitle = "Title2";
		String testDetail = "This is test secret memo with password = password!";
		SecretLevel testLevel = SecretLevel.HIGH;
		String testPwd = "password";

		MemoSecretSaveReq memoSaveReq = new MemoSecretSaveReq(testTitle, testDetail, testLevel, testPwd);
		SecretMemo save = memoRepository.save(memoSaveReq.toEntity());
		Long memoIdx = save.getMemoIdx();

		String url = BASE_URL + port + "/memo/{memoIdx}";

		//when
		mvc.perform(get(url, memoIdx)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result.memoTitle").value(testTitle))
			.andExpect(jsonPath("$.result.memoPwd").value(testPwd));
	}
}
