package com.royce.memolist.memo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.royce.memolist.memo.model.dto.MemoRes;
import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;
import com.royce.memolist.utils.BaseResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/memo")
@RestController
public class MemoController {

	private final MemoService memoService;

	//새로운 Memo작성 API
	@PostMapping
	public BaseResponseEntity<MemoRes> addMemo(@RequestBody MemoSaveReq saveReq) {
		MemoRes memoRes = memoService.saveMemo(saveReq);

		return new BaseResponseEntity<>(memoRes);
	}

	//새로운 비밀 Memo작성 API
	@PostMapping("/secret")
	public BaseResponseEntity<MemoRes> addSecretMemo(@RequestBody MemoSecretSaveReq saveReq) {
		MemoRes secretMemo = memoService.saveSecretMemo(saveReq);
		return new BaseResponseEntity<>(secretMemo);
	}

	//Memo 전체 조회 API
	@GetMapping
	public BaseResponseEntity<List<MemoRes>> findAllMemos() {
		List<MemoRes> allMemos = memoService.getAllMemos();
		return new BaseResponseEntity<>(allMemos);
	}


	//Memo 단일 조회 API
	@GetMapping("/{memoIdx}")
	public void getSingleMemo(@PathVariable Long memoIdx) {

	}

	//단일 Memo 수정 API
	@PatchMapping("/{memoIdx}")
	public void editMemo(@PathVariable Long memoIdx) {

	}

	//단일 Memo 삭제 API
	@DeleteMapping("/{memoIdx}")
	public void deleteMemo(@PathVariable Long memoIdx) {

	}

	//비밀 Memo password 인증
	@PostMapping("/{memoIdx}/auth")
	public void matchPassword(@PathVariable Long memoIdx) {

	}
}
