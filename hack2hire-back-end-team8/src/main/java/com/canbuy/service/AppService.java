package com.canbuy.service;

import org.springframework.stereotype.Service;

@Service
public class AppService {

	public String checkValidUser(String uname, String pwd) {
		if (uname.equals("Admin") && pwd.equals("Admin") ? true : false) {
			return "1234";
		} else {
			return null;
		}

	}


}
