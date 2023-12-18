package com.otp.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otp.entity.smspojo;
import com.otp.service.smsservice;
import com.twilio.twiml.fax.Receive.MediaType;

@RestController
public class smscontroller {

	@Autowired
	private smsservice smsservice;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	
	private final String TOPIC_DESTINATION="/lesson/sms";
	
	@PostMapping(value="/mobilenumber")
	public ResponseEntity<String> sendOtp(@RequestBody smspojo sms) 
	{
		try 
		{
			System.out.println(sms.getPhonenumber());
			this.smsservice.send(sms);
			
		}
		catch (Exception e) 
		{
			return new ResponseEntity<String>("some thing problem",HttpStatus.INTERNAL_SERVER_ERROR);
			//e.printStackTrace();
		}
		
		template.convertAndSend(TOPIC_DESTINATION,getTimeStamp()+"sms sent "+sms.getPhonenumber());
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	private String getTimeStamp() {
		
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}
	
}
