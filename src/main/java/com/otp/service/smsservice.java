package com.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.entity.smspojo;
import com.otp.entity.storeotp;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class smsservice {

	
	@Autowired
	private otpservice otpservice;
	
	private final String ACCOUNT_SID="sssssssssssssssss";
	private final String AUTH_TOKEN="ssssssss";
	private final String FROM_NUMBER="+8521954403";
	
	public void send(smspojo sms) {
		Twilio.init(AUTH_TOKEN, ACCOUNT_SID);
		
		
		int number=otpservice.generateotp(sms.getPhonenumber());
		System.out.print(number);
		String msg="your otp -"+number+"plzz veryfy this otp ";
		Message message=Message.creator(new PhoneNumber(sms.getPhonenumber()), new PhoneNumber(FROM_NUMBER), msg)
				.create();
		
		
		storeotp.setOtp(number);
		
		
	}
}
