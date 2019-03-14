package com.canbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.canbuy.model.AccountDetail;
import com.canbuy.model.BuyDecision;
import com.canbuy.model.UserDetails;
import com.canbuy.repository.AccountRepository;
import com.canbuy.repository.BuyRepository;
import com.canbuy.repository.UserDetailsRepository;

/**
 * 
 * @author Ramesh
 *
 */

@RestController
@RequestMapping(value = "/home", produces = "application/json")
public class AppController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BuyRepository buyRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@PostMapping(value = "/login")
	public AccountDetail login(@RequestParam String userName, @RequestParam String pwd) {

		UserDetails userDetails = userDetailsRepository.findByUserNameAndPwd(userName, pwd);
		AccountDetail accountDetail = null;
		if (userDetails != null && userDetails.getUserId() != null && userDetails.getCustId() != null) {
			accountDetail = accountRepository.findByCustId(userDetails.getCustId());
			if (null != accountDetail) {
				return accountDetail;
			} else {
				throw new RestClientException("Invalid User details entered");
			}

		}
		return accountDetail;
	}

	@RequestMapping(value = "/getMultipleAccount/{custId}", method = RequestMethod.GET)
	@ResponseBody
	public AccountDetail getMultipleAccount(@PathVariable Long custId) {
		AccountDetail accountDetail = null;
		if (custId != null) {
			accountDetail = accountRepository.findByCustId(custId);
		} else {
			throw new RestClientException("Valid Customer Id should be entered");
		}
		if (null != accountDetail) {
			return accountDetail;
		} else {
			throw new RestClientException("Valid Customer Id not found");
		}

	}

	@GetMapping(value = "/getBuyDecision/{custId}/{category}")
	@ResponseBody
	public BuyDecision findByCustIdAndCategory(@PathVariable Long custId, @PathVariable String category) {
		if (custId != null && category != null) {
			BuyDecision buyDecision = buyRepository.findByCustIdAndCategory(custId, category);
			if (null != buyDecision) {
				return buyDecision;
			}
			throw new RestClientException("No valid Decision found for the CustId and Category");
		} else {
			throw new RestClientException("CustID and Categorgy is Mandatory to get the Decision");
		}
	}

	@PutMapping("/fundTransfer/{custId}/{transferAmount}/{transferAccountNo}")
	public AccountDetail fundTransfer(@PathVariable Long custId, @PathVariable double transferAmount,
			@PathVariable Long transferAccountNo) {
		AccountDetail accountDetail = null;
		if (custId != null) {
			accountDetail = accountRepository.findByCustId(custId);
		}
		if (accountDetail.getBalance() != 0 && (accountDetail.getBalance() >= transferAmount)) {

			if (transferAccountNo != null) {
				AccountDetail transferAccountDetail = accountRepository.findByAccountNo(transferAccountNo);
				if (transferAccountDetail != null) {
					double transferAcctbalance = accountDetail.getBalance() + transferAmount;
					transferAccountDetail.setBalance(transferAcctbalance);
					transferAccountDetail = accountRepository.save(transferAccountDetail);
					double fundBalance = accountDetail.getBalance() - transferAmount;
					accountDetail.setBalance(fundBalance);
					accountDetail = accountRepository.save(accountDetail);
					return accountDetail;
				} else {
					throw new RestClientException("Account Information not found");
				}

			} else {
				throw new RestClientException("Please enter the valid transfer account details");
			}

		} else {
			throw new RestClientException("Insufficient Funds to transfer");
		}

	}

	@PutMapping("/fixedDeposit/{custId}/{depositAmount}")
	public AccountDetail fixedDeposit(@PathVariable Long custId, @PathVariable double depositAmount) {
		AccountDetail accountDetail = null;
		if (custId != null) {
			accountDetail = accountRepository.findByCustId(custId);
		}
		if (accountDetail.getBalance() != 0 && (accountDetail.getBalance() >= depositAmount)) {
			double fundBalance = accountDetail.getBalance() - depositAmount;
			accountDetail.setBalance(fundBalance);
			accountDetail = accountRepository.save(accountDetail);
			// Creation of Fixed Deposit
			accountDetail.setCustId(custId);
			accountDetail.setType("FixedDeposit");
			accountDetail.setBalance(depositAmount);
			accountDetail.setAccountNo(accountDetail.getAccountNo());
			accountDetail.setDesc("Fixed Deposit Account");
			accountRepository.save(accountDetail);
			return accountDetail;
		} else {
			throw new RestClientException("Insufficient Funds to open a new FD, Please enter the valid FD amount");
		}

	}

	@GetMapping("/getAccountType/{typeAccount}/{custId}/")

	@ResponseBody
	public AccountDetail getAccountType(@PathVariable Long custid, @PathVariable Long typeAccount) {
		if (custid != null && typeAccount != null) {
			AccountDetail accountDetail = accountRepository.findByTypeaccountAndCustId(custid, typeAccount);
			return accountDetail;
		} else {
			throw new RestClientException("Please enter the valid Type Accoung and CustId");
		}
	}

}
