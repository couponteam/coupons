package test;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.utils.ModelConvertUtil;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jason on 2017/5/14.
 */
public class JedisDemo {

//    public static void main(String args[]){
//        Jedis test = new Jedis("121.42.153.133",6379);
//        test.auth("jasonlin");
//        test.set("hw","hello world");
//
//        System.out.println(test.info());
//
//        String hello = test.get("hw");
//        System.out.println(hello);
//    }


    public static void main(String[] args){

//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid.toString().replaceAll("-", ""));
//        System.out.println(uuid.toString().length());
//        JedisDemo.ticket();


//        String data = "{\"shop_id\": 8, \"ticket_name\": 一点点优惠券, \"content\": 9折优惠券, \"type_id\": 1, \"applicable_scope\": 杭州, \"working_condition\": 无, \"period_of_validity_startTime\": 2017-06-08 19:20:53, \"period_of_validity_endTime\": 2017-06-14 19:20:54, \"validity_comment\": 周一至周五可用, \"status\": 0, \"collecting_method\": 0, \"is_retaken\": 0, \"is_re_use\": 0}";
//
//        TicketWithBLOBs tdata = JSON.toJavaObject((JSON)JSON.parse(data), TicketWithBLOBs.class);
//
//        System.out.println(tdata.getContent());
        JedisDemo.ticket();

    }

    public void shop(){
        Shop s = new Shop();

        s.setId(10810L);
        s.setBriefIntro("");
        s.setIndustryId(1);
        s.setPhone("187");
        s.setShopAddress("");
        s.setShopName("");
//        s.setBusinessLicenseId(18181L);
        s.setIconId("127313");
        Byte b = new Byte("1");
        s.setIsShopVerified(b);
        s.setIsPhoneVerified(b);
        s.setCreateTime(new Date());

        System.out.println(JSON.toJSONString(s));
    }


    public static void ticket(){
        TicketWithBLOBs t = new TicketWithBLOBs();

        Byte b = new Byte("1");
        t.setCollectingMethod(b);
        t.setComment("备注");
        t.setContent("9折优惠券");
        t.setId(183719237L);
        t.setPeriodOfValidityStarttime(new Date());
        t.setPeriodOfValidityEndtime(new Date());
//        t.setShopId(123918231);
        t.setStatus(b);
        t.setTicketName("");
        t.setTypeId(b);
        t.setApplicableScope("");
        t.setWorkingCondition("");

        String data = JSON.toJSONString(t);

        System.out.println(data);

        TicketWithBLOBs tdata = JSON.toJavaObject((JSON)JSON.parse(data), TicketWithBLOBs.class);

        System.out.println(tdata.getContent());
    }

}
