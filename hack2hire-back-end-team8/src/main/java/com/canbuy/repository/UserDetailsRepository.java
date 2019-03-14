package com.canbuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.canbuy.model.UserDetails;
/**
 * 
 * @author Ramesh
 *
 */
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
	UserDetails findByUserNameAndPwd(@Param("userName") String userName, @Param("pwd") String pwd);

}
