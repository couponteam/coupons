package com.masou.coupon.dao.api;

import com.masou.coupon.action.erpapi.vo.ShopTicketVO;
import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/26.
 */
@Repository
public class TicketDao {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 根据行业类型，券类型，获取店铺和券信息
     * @param industry 行业类型
     * @param type 券类型
     * @param param 分页信息
     * @return 店铺和券的信息
     */
    public List<ShopTicketVO> selectByType(Integer industry, Integer type, PageParam param){
        return shopMapper.selectByType(industry,type,param.getPage(),param.getPageSize());
    }
}
