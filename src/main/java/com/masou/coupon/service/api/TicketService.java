package com.masou.coupon.service.api;

import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.dao.api.ShopDao;
import com.masou.coupon.dao.api.TicketDao;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/5/23.
 */
@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private ShopDao shopDao;

    public List<Shop> selectByType(Integer industry, Integer type, Integer page){

        PageParam param = new PageParam();
        param.setPage(page);
        List<Ticket> rs = ticketDao.selectByType(industry, param);
        if(rs != null && rs.size() > 0){




        }



        return null;
//        return shopDao.selectBySType(industry, limit);
    }


    public List<TicketWithBLOBs> selectTicketByShopId(Long sid, Integer type){



        return null;
    }

    /**
     * 根据用户当前的位置，展示附近的券
     * @param longitude
     * @param latitude
     */
    public List<Shop> selectList(String longitude,String latitude){


        return null;
    }

    /**
     * 当没有用户位置信息时，默认展示最新的数据
     */
    public List<Shop> selectList(){


        return null;
    }

    /**
     * 展示精选店铺
     */
    public List<Shop> bestShop(){

        return null;
    }
}
