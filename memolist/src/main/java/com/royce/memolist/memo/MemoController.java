package com.royce.memolist.memo;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.dto.MemoPwdReq;
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
	// TODO : 없는 ID 예외처리
	@GetMapping("/{memoIdx}")
	public BaseResponseEntity<MemoRes> getSingleMemo(@PathVariable Long memoIdx) {
		MemoRes memoById = memoService.getMemoById(memoIdx);

		return new BaseResponseEntity<>(memoById);
	}

	//단일 Memo 수정 API
	@PatchMapping("/{memoIdx}")
	public void editMemo(@PathVariable Long memoIdx, @RequestBody MemoSaveReq saveReq,
		HttpServletResponse response) throws IOException {
		memoService.updateMemo(memoIdx, saveReq);
		// response.sendRedirect("http://localhost:9000/memo/" + memoIdx);
	}

	//Secret Memo 수정 API
	@PatchMapping("/{memoIdx}/secret")
	public void editSecretMemo(@PathVariable Long memoIdx, @RequestBody MemoSecretSaveReq saveReq, HttpServletResponse response) throws
		IOException {
		memoService.updateSecretMemo(memoIdx, saveReq);
		// response.sendRedirect("http://localhost:9000/memo/" + memoIdx);
	}

	//단일 Memo 삭제 API
	// TODO : 없는 ID 예외처리 
	@DeleteMapping("/{memoIdx}")
	public BaseResponseEntity<Long> deleteMemo(@PathVariable Long memoIdx) {
		Long deletedId = memoService.deleteById(memoIdx);
		return new BaseResponseEntity<>(deletedId);
	}

	//비밀 Memo password 인증
	@PostMapping("/{memoIdx}/auth")
	public BaseResponseEntity<Boolean> matchPassword(@PathVariable Long memoIdx, @RequestBody MemoPwdReq password) {
		String pwd = password.getPwd();
		return new BaseResponseEntity<>(memoService.isMatchPassword(memoIdx, pwd));
	}
}
