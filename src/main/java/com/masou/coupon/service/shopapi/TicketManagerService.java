package com.masou.coupon.service.shopapi;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.action.erpapi.vo.TicketPageParam;
import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.dao.BannerDao;
import com.masou.coupon.dao.ShopApiDao.TicketManagerDao;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.GenTicketIdUtil;
import com.masou.coupon.utils.ModelConvertUtil;
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

    public TicketWithBLOBs insertTicket(String data){
        try {

            TicketWithBLOBs tdata = JSON.toJavaObject((JSON)JSON.parse(data), TicketWithBLOBs.class);

            //生成券id
            tdata.setTicketId(GenTicketIdUtil.genTicketId());

            int rs = ticketManagerDao.insertTicket(tdata);
            if(rs > 0){
                //查询票信息
                return tdata;
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
    public  List<TicketWithBLOBs> showTicketList(Long sid, Integer page, Integer pageSize){
        try {
            TicketPageParam tdata = new TicketPageParam();
            tdata.setShop_id(sid);
            tdata.setPage(page);
            tdata.setPageSize(pageSize);

            return ticketManagerDao.selectTicket(tdata);
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException();
        }
    }

    /**
     * 查询单张券
     * @param tid
     * @return
     */
    public TicketWithBLOBs selectSingleTicket(String tid){
        return ticketManagerDao.selectByTicketId(Long.parseLong(tid));
    }

    public int updateTicket(String data){
        try {
            TicketWithBLOBs ticket = ModelConvertUtil.convert(TicketWithBLOBs.class, data);
            return ticketManagerDao.updateTicket(ticket);
        }catch(Exception e){
            throw new UserException();
        }
    }

    public int deleteTicket(Long tid){
       return ticketManagerDao.deleteTicket(tid);
    }
}
