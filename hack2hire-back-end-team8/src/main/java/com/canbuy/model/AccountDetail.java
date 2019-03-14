package com.canbuy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Ramesh
 *
 */
@Entity
@Table(name = "AccountDetail")
public class AccountDetail {
	@Id
	private Long custId;
	@Column(name = "typeAccount")
	private String type;
	@Column(name = "accountNo")
	private Long accountNo;
	@Column(name = "balance")
	private double balance;
	@Column(name = "description")
	private String desc;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
