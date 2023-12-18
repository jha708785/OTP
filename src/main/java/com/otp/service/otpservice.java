package com.otp.service;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;


@Service
public class otpservice {

	
	private static final Integer EXPIRE_MINS=5;
	
	private com.google.common.cache.LoadingCache<String , Integer>otpCache;
	
	
	public otpservice()
	{
		super();
		otpCache=CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS,TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {

					@Override
					public Integer load(String key) throws Exception {
						// TODO Auto-generated method stub
						return 0;
					}
				});
				
	}
	
	public int generateotp(String key) {
		Random random=new Random();
		int otp=10000+random.nextInt(900000);
		
		otpCache.put(key, otp);
		return otp;
		
	}
	
	
	public int getotp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public void clearotp(String key) {
		otpCache.invalidate(key);
	}
	
}
