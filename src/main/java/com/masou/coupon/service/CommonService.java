package com.masou.coupon.service;

import com.masou.coupon.data.mappers.TicketTypeMapper;
import com.masou.coupon.data.models.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2017/6/10.
 */
@Service
public class CommonService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    private static final String KEY_TICKET_TYPE_ID = "typeid";

    /**
     *
     * @param status
     * @return
     */
    public String changeTicketStatus(Byte status){
        String msg = status.toString();
        switch (status){
            case(0):
                msg = "未上架";
            case(1):
                msg = "上架";
            case(2):
                msg = "过期";
            case(3):
                msg = "下架";
            default:;
        }
        return msg;
    }

    /**
     * 将status状态改为中文输出
     * @param type
     * @return
     */
    public String changeTicketType(Byte type){
        if(redisService.exists(KEY_TICKET_TYPE_ID)){
            return redisService.mapGet(KEY_TICKET_TYPE_ID, type.toString());
        }else{
            List<TicketType> list = ticketTypeMapper.selectList();
            if(list != null && list.size() > 0){
                Map<String, String> typeMap = new HashMap<>();
                for (TicketType ticketType : list) {
                    typeMap.put(ticketType.getId()+"", ticketType.getComment());
                }
                redisService.mapSet(KEY_TICKET_TYPE_ID, typeMap, 60 * 60 * 24);
                return typeMap.get(type.toString());
            }
        }
        return null;
    }

    public String changeTicketRetaken(Byte retaken){
        String msg = retaken.toString();
        switch (retaken){
            case(0):
                msg = "不可以";
            case(1):
                msg = "科艺";
            default:;
        }
        return msg;
    }

    public String changeTicketReuse(Byte reuse){
        String msg = reuse.toString();
        switch (reuse){
            case(0):
                msg = "不可叠加";
            case(1):
                msg = "可叠加";
            default:
        }
        return msg;
    }

}
