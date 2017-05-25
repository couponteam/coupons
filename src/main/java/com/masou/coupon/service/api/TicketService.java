package com.masou.coupon.service.api;

import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/5/23.
 */
@Service
public class TicketService {


    public List<TicketWithBLOBs> selectTicketByShopId(Long sid, Integer type){



        return null;
    }

    /**
     * 根据用户当前的位置，展示附近的券
     * @param longitude
     * @param latitude
     */
    public void selectList(String longitude,String latitude){

    }

    /**
     * 当没有用户位置信息时，默认展示最新的数据
     */
    public void selectList(){

    }

    /**
     * 展示精选店铺
     */
    public List<Shop> bestShop(){

        return null;
    }
}
