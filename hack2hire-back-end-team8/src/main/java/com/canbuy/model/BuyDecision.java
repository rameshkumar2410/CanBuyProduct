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
@Table(name = "BuyDecision")
public class BuyDecision {
	@Id
	private Long custId;
	@Column(name = "decision")
	private String decision;
	@Column(name = "suggAccType")
	private String suggAccType;
	@Column(name = "success")
	private String success;
	@Column(name = "category")
	private String category;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getSuggAccType() {
		return suggAccType;
	}

	public void setSuggAccType(String suggAccType) {
		this.suggAccType = suggAccType;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
