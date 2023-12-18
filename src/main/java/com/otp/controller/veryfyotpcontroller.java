package com.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otp.entity.storeotp;
import com.otp.entity.tempOtp;
import com.otp.service.otpservice;

@RequestMapping
public class veryfyotpcontroller {

	@Autowired
	private otpservice otpservice;
	
	@PostMapping("/otp")
	public String veryfyotp(@RequestBody tempOtp otp) {
		
		if(otp.getOtp()==otpservice.getotp(otp.getPhonrnumber()))	
		{
			otpservice.clearotp(otp.getPhonrnumber());
			return "correct";
		}
		else
		return "not correct otp";
	}
}
