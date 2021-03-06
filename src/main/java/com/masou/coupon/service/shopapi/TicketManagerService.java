package com.masou.coupon.service.shopapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.masou.coupon.action.api.vo.TicketResultVO;
import com.masou.coupon.action.api.vo.ticketvo.TicketVO;
import com.masou.coupon.action.erpapi.vo.TicketPageParam;
import com.masou.coupon.dao.ShopApiDao.TicketManagerDao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.LogUserTicketMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.mappers.TicketTypeMapper;
import com.masou.coupon.data.mappers.UserTicketMapper;
import com.masou.coupon.data.models.*;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.CommonService;
import com.masou.coupon.utils.GenTicketIdUtil;
import com.masou.coupon.utils.ModelConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/5/17.
 */
@Service
public class TicketManagerService {

    private Logger log = LoggerFactory.getLogger(TicketManagerService.class);

    @Autowired
    private TicketManagerDao ticketManagerDao;

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private LogUserTicketMapper logUserTicketMapper;

    @Autowired
    private UserTicketMapper userTicketMapper;

    @Autowired
    private CommonService commonService;

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
        }catch (JSONException e){
            throw new UserException("请求参数字段不符合规范");
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
    public  TicketResultVO showTicketList(Long sid, Long uid, Integer page, Integer pageSize, String status){
        try {
            TicketPageParam tdata = new TicketPageParam();
            tdata.setShop_id(sid);
            tdata.setPage(page);
            if(pageSize == null){
                tdata.setPageSize(PageParam.PAGESIZE_DEFAULT);
            }else{
                tdata.setPageSize(pageSize);
            }
            if(status != null && status.length() > 0){
                tdata.setStatus(new Byte(status));
            }

            List<TicketWithBLOBs> list;
            if (uid == null || uid <= 0){
                list = ticketManagerDao.selectTicketWithoutUTID(tdata);
            }else{
                list = ticketManagerDao.selectTicket(tdata);
            }

            TicketResultVO resultVo = new TicketResultVO();
            List<TicketVO> listVo = new ArrayList<TicketVO>();
            for (TicketWithBLOBs ticket: list) {
                TicketVO vo = new TicketVO();

                if(uid != null && uid > 0){
                    UserTicket userTicket = new UserTicket();
                    userTicket.setUserId(uid);
                    userTicket.setTicketId(ticket.getTicketId());

                    List<UserTicket> userTickets = userTicketMapper.findByUidTid(userTicket);
                    if(userTickets != null && userTickets.size() > 0){
                        vo.setIsTaken("已领取");
                    }
                }

                ticketBeenTakenAndUsed(ticket, vo);

                vo.setUnread(checkUnreadTicket(uid,ticket.getTicketId()));
                vo.setTicket(ticket);
                fileTicketVO(vo, ticket);
                listVo.add(vo);
            }
            resultVo.setTicketVO(listVo);
            resultVo.setTotal(selectCount(tdata));

            return resultVo;
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException();
        }
    }

    /**
     * 检查当前的券是否已读
     * @param uid
     * @param tid
     * @return true：存在未读：false：表示已读
     */
    private boolean checkUnreadTicket(Long uid, String tid){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setUid(uid);
        shopFilter.setTid(tid);
        if(logUserTicketMapper.checkUnreadTicket(shopFilter) > 0){
            return false;
        }
        return true;
    }

    /**
     * 查询数据库中已领取券数和已使用券数
     * @param ticket
     * @return
     */
    private void ticketBeenTakenAndUsed(TicketWithBLOBs ticket, TicketVO vo){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setTid(ticket.getTicketId());

        List<UserTicket> userTickets = userTicketMapper.ticketBeenTakenAndUsed(shopFilter);
        for (UserTicket userTicket : userTickets) {
            if (userTicket .getStatus() == 2){
                //设置已领取的数量
                vo.setTaken(userTicket.getNum());
            }else if(userTicket .getStatus() == 3){
                //设置已使用的数量
                vo.setUsed(userTicket.getNum());
            }
        }
    }

    /**
     * 将数字状态改成文字状态
     * @param vo
     * @param ticket
     */
    public void fileTicketVO(TicketVO vo, TicketWithBLOBs ticket){
        vo.set_typeId(commonService.changeTicketType(ticket.getTypeId()));
        vo.set_isRetaken(commonService.changeTicketRetaken(ticket.getIsRetaken()));
        vo.set_isReUse(commonService.changeTicketReuse(ticket.getIsReUse()));
        vo.set_status(commonService.changeTicketStatus(ticket.getStatus()));
        vo.set_colcMethod(commonService.changeCollecting_method(ticket.getCollectingMethod()));
    }

    private int selectCount(TicketPageParam tdata){
        return ticketManagerDao.selectCount(tdata);
    };

    /**
     * 查询单张券
     * @param tid
     * @return
     */
    public TicketVO selectSingleTicket(String tid){
        TicketWithBLOBs ticket = ticketManagerDao.selectByTicketId(tid);
        if(ticket != null){
            TicketVO ticketVO = new TicketVO();
            ticketVO.setTicket(ticket);
            fileTicketVO(ticketVO, ticket);
            return ticketVO;
        }
        return null;
    }

    /**
     * 获取券类型和中文名称
     * @param id
     * @return
     */
    public TicketType getTicketType(Integer id){
        return ticketTypeMapper.selectByPrimaryKey(id);
    }

    public int updateTicket(String data){
        try {
            TicketWithBLOBs ticket = ModelConvertUtil.convert(TicketWithBLOBs.class, data);
            return ticketManagerDao.updateTicket(ticket);
        }catch(Exception e){
            throw new UserException();
        }
    }


    public int updateTicket(TicketWithBLOBs ticket){
        try {
            return ticketManagerDao.updateTicket(ticket);
        }catch(Exception e){
            throw new UserException();
        }
    }

    public int deleteTicket(String tid){
       return ticketManagerDao.deleteTicket(tid);
    }
}
