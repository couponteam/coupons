package com.masou.coupon.service.shopapi;

import com.masou.coupon.dao.BannerDao;
import com.masou.coupon.dao.ShopApiDao.TicketManagerDao;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/5/17.
 */
@Service
public class TicketManagerService {

    private Logger log = LoggerFactory.getLogger(TicketManagerService.class);

    @Autowired
    private TicketManagerDao ticketManagerDao;

    public Ticket insertTicket(TicketWithBLOBs data, Long sid){
        try {
            int rs = ticketManagerDao.insertTicket(data);
            if(rs > 0){
                //查询票信息


//                return rs;
            }
        }catch(Exception e){
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 显示券列表
     * @param sid
     * @return
     */
    public  List<Ticket> showTicketList(Long sid){


        return null;
    }

    /**
     * 查询单张券
     * @param tid
     * @return
     */
    public Ticket selectSingleTicket(Long tid){

        return null;
    }

    public void updateTicket(String data){}

    public int deleteTicket(Long tid){
       return ticketManagerDao.deleteTicket(tid);
    }
}
