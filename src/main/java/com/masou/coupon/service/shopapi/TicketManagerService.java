package com.masou.coupon.service.shopapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.masou.coupon.action.api.vo.TicketResultVO;
import com.masou.coupon.action.api.vo.TicketVO;
import com.masou.coupon.action.erpapi.vo.TicketPageParam;
import com.masou.coupon.dao.ShopApiDao.TicketManagerDao;
import com.masou.coupon.data.mappers.TicketTypeMapper;
import com.masou.coupon.data.models.TicketType;
import com.masou.coupon.data.models.TicketWithBLOBs;
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
    public  TicketResultVO showTicketList(Long sid, Integer page, Integer pageSize){
        try {
            TicketPageParam tdata = new TicketPageParam();
            tdata.setShop_id(sid);
            tdata.setPage(page);
            tdata.setPageSize(pageSize);
            List<TicketWithBLOBs> list = ticketManagerDao.selectTicket(tdata);

            TicketResultVO resultVo = new TicketResultVO();
            List<TicketVO> listVo = new ArrayList<TicketVO>();
            for (TicketWithBLOBs ticket: list) {
                TicketVO vo = (TicketVO)ticket;
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

    private void fileTicketVO(TicketVO vo, TicketWithBLOBs ticket){
        vo.setTypeId(commonService.changeTicketType(ticket.get_typeId()));
        vo.setIsRetaken(commonService.changeTicketRetaken(ticket.get_isRetaken()));
        vo.setIsReUse(commonService.changeTicketReuse(ticket.get_isReUse()));
        vo.setStatus(commonService.changeTicketStatus(ticket.get_status()));
    }

    private int selectCount(TicketPageParam tdata){
        return ticketManagerDao.selectCount(tdata);
    };

    /**
     * 查询单张券
     * @param tid
     * @return
     */
    public TicketVO selectSingleTicket(Long tid){
        TicketWithBLOBs ticket = ticketManagerDao.selectByTicketId(tid);
        TicketVO ticketVO = (TicketVO)ticket;
        fileTicketVO(ticketVO, ticket);
        return ticketVO;
    }

    /**
     * 获取券类型和中文名称
     * @param id
     * @return
     */
    public TicketType getTicketType(Long id){
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

    public int deleteTicket(Long tid){
       return ticketManagerDao.deleteTicket(tid);
    }
}
