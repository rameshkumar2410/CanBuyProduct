package com.canbuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.canbuy.model.AccountDetail;

/**
 * 
 * @author Ramesh
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountDetail, Long> {

	List<AccountDetail> findAll();

	@SuppressWarnings("unchecked")
	AccountDetail save(AccountDetail accountDetail);

	AccountDetail findByCustId(@Param("custId") Long custId);

	AccountDetail findByAccountNo(@Param("transferAccountNo") Long transferAccountNo);

	AccountDetail findByTypeaccountAndCustId(@Param("custId") Long custId, @Param("typeAccount") Long typeAccount);

}
