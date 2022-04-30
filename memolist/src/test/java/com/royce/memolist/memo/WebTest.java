package com.royce.memolist.memo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.royce.memolist.memo.model.MemoRepository;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest {

	@LocalServerPort
	protected int port;

	@Autowired
	protected TestRestTemplate restTemplate;

	@Autowired
	protected MemoRepository memoRepository;

	@Autowired
	protected MockMvc mvc;

	@AfterEach
	void deleteData() {
		memoRepository.deleteAll();
	}

	protected String BASE_URL = "http://localhost:";
}
