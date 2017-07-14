package com.masou.coupon.data.mappers;

import com.masou.coupon.action.erpapi.vo.TicketPageParam;
import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.models.UserTicket;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TicketMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryTid(String id);

    int insert(TicketWithBLOBs record);

    int insertSelective(TicketWithBLOBs record);

    TicketWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TicketWithBLOBs record);

    int updateByTidSelective(TicketWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TicketWithBLOBs record);

    int updateByPrimaryKey(Ticket record);

    List<TicketWithBLOBs> selectTicketWithoutUTID( TicketPageParam param);

    //根据店铺的id，获取
    List<TicketWithBLOBs> selectByLimit(TicketPageParam page);

    int selectCount(TicketPageParam pageParam);

    List<TicketWithBLOBs> selectByShopId(ShopFilter shopFilter);

    List<Ticket> selectByType(Integer type, PageParam param);

    List<TicketWithBLOBs> findBySid(ShopFilter shopFilter);

    int ticketCanRetaken(UserTicket record);

    Integer ticketCount(StatisticFilter statisticFilter);

    Integer ticketCountByTake(StatisticFilter statisticFilter);

    Integer selectCountByTid(StatisticFilter statisticFilter);

    TicketWithBLOBs selectByTid(ShopFilter shopFilter);

    List<TicketWithBLOBs> myTicket(ShopFilter shopFilter);

    List<TicketWithBLOBs> myTicketNoUse(ShopFilter shopFilter);

    TicketWithBLOBs ticketByFollow(ShopFilter shopFilter);

    TicketWithBLOBs selectFollowTicket(ShopFilter shopFilter);

    List<TicketWithBLOBs> selectShopTicketType(ShopFilter filter);

    TicketWithBLOBs selectIdBySidUid(ShopFilter shopFilter);

    TicketWithBLOBs ticketByFollowShop(ShopFilter shopFilter);
}