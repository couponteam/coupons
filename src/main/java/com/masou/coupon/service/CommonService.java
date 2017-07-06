package com.masou.coupon.service;

import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.StatusEnum;
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
        if(status != null){
            String msg = status.toString();
            switch (status){
                case(0):
                    msg = "未上架";
                    break;
                case(1):
                    msg = "上架";
                    break;
                case(2):
                    msg = "过期";
                    break;
                case(3):
                    msg = "下架";
                    break;
                default:;
            }
            return msg;
        }
        return null;
    }

    /**
     * 改变券的领取方式
     * @param status
     * @return
     */
    public String changeCollecting_method(Byte status){
        if (status != null && status > 0){
            int statusInt = Integer.parseInt(status.toString());
            if (statusInt == StatusEnum.PICKUP_RIGHT_NOW.getStatus()){
                return StatusEnum.PICKUP_RIGHT_NOW.getComment();
            }

            if (statusInt == StatusEnum.PICKUP_FORWARD.getStatus()){
                return StatusEnum.PICKUP_FORWARD.getComment();
            }

            if (statusInt == StatusEnum.PICKUP_FOLLOW.getStatus()){
                return StatusEnum.PICKUP_FOLLOW.getComment();
            }
        }
        return null;
    }

    /**
     * 将status状态改为中文输出
     * @param type
     * @return
     */
    public String changeTicketType(Byte type){
        if(type != null){
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
        }
        return null;
    }

    public String changeTicketRetaken(Byte retaken){
        if(retaken != null){
            String msg = retaken.toString();
            switch (retaken){
                case(0):
                    msg = "不可以";
                    break;
                case(1):
                    msg = "可以";
                    break;
                default:;
            }
            return msg;
        }
        return null;
    }

    public static String changeTicketReuse(Byte reuse){
        if(reuse != null){
            String msg = reuse.toString();
            switch (reuse){
                case(0):
                    msg = "不可叠加";
                    break;
                case(1):
                    msg = "可叠加";
                    break;
                default:
            }
            return msg;
        }
        return null;
    }

}
