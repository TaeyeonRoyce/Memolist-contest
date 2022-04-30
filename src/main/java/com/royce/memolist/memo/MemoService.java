package com.royce.memolist.memo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.royce.memolist.memo.model.Memo;
import com.royce.memolist.memo.model.MemoRepository;
import com.royce.memolist.memo.model.SecretMemo;
import com.royce.memolist.memo.model.dto.MemoRes;
import com.royce.memolist.memo.model.dto.MemoSaveReq;
import com.royce.memolist.memo.model.dto.MemoSecretSaveReq;

import lombok.RequiredArgsConstructor;

@Transactional
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

	public List<MemoRes> getAllMemos() {
		List<Memo> all = memoRepository.findAll();

		List<MemoRes> memoResList = new ArrayList<>();
		for (Memo memo : all) {
			if (memo instanceof SecretMemo) {
				memoResList.add(new MemoRes((SecretMemo)memo));
				continue;
			}
			memoResList.add(new MemoRes(memo));
		}

		return memoResList;
	}

	public MemoRes getMemoById(Long memoIdx) {
		Memo memo = memoRepository.findById(memoIdx).orElseGet(Memo::new);
		MemoRes memoRes = new MemoRes(memo);
		if (memo instanceof SecretMemo) {
			return new MemoRes((SecretMemo)memo);
		}
		return memoRes;
	}

	public Long deleteById(Long memoIdx) {
		memoRepository.deleteById(memoIdx);
		return memoIdx;
	}

	//TODO : 예외처리
	//1. 비밀 메모인지 확인
	//2. 존재하는 메모인지 확인
	public boolean isMatchPassword(Long memoIdx, String password) {
		Memo memo = memoRepository.findById(memoIdx).get();
		if (memo.isSecret() && password.equals(((SecretMemo)memo).getMemoPwd())) {
				return true;
			}

		return false;
	}

	public void updateMemo(Long memoIdx, MemoSaveReq saveReq) {
		Memo memo = memoRepository.findById(memoIdx).get();
		if (memo.isSecret()) {
			((SecretMemo)memo).toNormalMemo();
		}

		memo.updateNormal(saveReq);
	}

	public void updateSecretMemo(Long memoIdx, MemoSecretSaveReq saveReq) {
		memoRepository.toSecretMemo(memoIdx);
		Memo memo = memoRepository.findById(memoIdx).get();
		memo.updateToSecret(saveReq);
	}

}
