package com.masou.coupon;

import com.masou.coupon.service.sms.SMSResult;
import com.masou.coupon.service.sms.SmsClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SMSTests {

	@Autowired
	private SmsClientService smsClientService;


	@Test
	public void send() {

		SMSResult result = smsClientService.sendMessage("18667179291","【码艘科技】您的验证码是123456");

		System.out.println(result.toString());
	}

}
