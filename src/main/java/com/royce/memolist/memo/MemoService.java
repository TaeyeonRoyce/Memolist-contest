package com.royce.memolist.memo;

import org.springframework.stereotype.Service;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.MemoRepository;
import com.royce.memolist.memo.model.SecretMemo;
import com.royce.memolist.memo.model.dto.MemoRes;
import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoService {

	private final MemoRepository memoRepository;

	public MemoRes saveMemo(MemoSaveReq saveReq) {
		Memo memo = saveReq.toEntity();
		memoRepository.save(memo);

		MemoRes res = new MemoRes(memo);
		return res;
	}

	public MemoRes saveSecretMemo(MemoSecretSaveReq saveReq) {
		SecretMemo secretMemo = saveReq.toEntity();
		memoRepository.save(secretMemo);

		MemoRes res = new MemoRes(secretMemo);
		return res;
	}


}
