package com.masou.coupon.dao.ShopApiDao;

import com.masou.coupon.action.erpapi.vo.TicketPageParam;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/19.
 */
@Repository
public class TicketManagerDao {

    private static Logger log = LoggerFactory.getLogger(TicketManagerDao.class);

    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 插入用户券
     * @param record 券内容
     * @return 返回影响行数
     */
    public int insertTicket(TicketWithBLOBs record){
        return ticketMapper.insertSelective(record);
    };

    /**
     * 更新券
     * @param record 更新的内容
     * @return 返回影响条数
     */
    public int updateTicket(TicketWithBLOBs record){
        return ticketMapper.updateByTidSelective(record);
    }

    /**
     * 删除券
     * @param tid 券的id，对应表的主键
     * @return
     */
    public int deleteTicket(String tid){
        try {
            return ticketMapper.deleteByPrimaryTid(tid);
        }catch(Exception e){
            log.error("删除券失败，原因："+ e.getLocalizedMessage());
        }
        //返回0表示失败
        return 0;
    }

    public int selectCount(TicketPageParam pageParam){
        return ticketMapper.selectCount(pageParam);
    }

    public List<TicketWithBLOBs> selectTicket( TicketPageParam param){
       return ticketMapper.selectByLimit(param);
    }

    public TicketWithBLOBs selectByTicketId(String tid){
        return ticketMapper.selectByPrimaryKey(tid);
    }

}
