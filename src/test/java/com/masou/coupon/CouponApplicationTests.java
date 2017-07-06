package com.masou.coupon;

import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.mappers.UserTicketMapper;
import com.masou.coupon.data.models.UserTicketPersonal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponApplicationTests {

	@Autowired
	private UserTicketMapper userTicketMapper;
	@Test
	public void contextLoads() {

		BaseFilter filter = new BaseFilter();
		filter.setUid(200220439442621212L);
		List<UserTicketPersonal> list = userTicketMapper.selectListByUid(filter);
		System.out.print(list.size());
	}

}
