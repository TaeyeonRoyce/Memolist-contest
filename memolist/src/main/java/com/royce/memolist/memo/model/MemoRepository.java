package com.royce.memolist.memo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
	@Modifying
	@Query("update SecretMemo m set "
		+ "m.memoPwd = :pwd, m.isSecret = true, m.secretLevel = :secretLevel "
		+ "where m.memoIdx = :memoIdx")
	void updateSecretMemo(@Param(value = "memoIdx") Long id, @Param(value = "pwd") String pwd,
		@Param(value = "secretLevel") SecretLevel secretLevel);

	@Modifying
	@Query(value = "update Memo set dtype = 'SecretMemo' where memo_idx = :memoIdx",nativeQuery = true)
	void toSecretMemo(@Param(value = "memoIdx") Long memoIdx);
}
