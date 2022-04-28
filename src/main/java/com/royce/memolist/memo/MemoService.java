package com.royce.memolist.memo;

import org.springframework.stereotype.Service;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.MemoRepository;
import com.royce.memolist.memo.model.dto.MemoRes;
import com.royce.memolist.memo.model.dto.MemoSaveReq;

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


}
