package com.masou.coupon.action.shopapi;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.service.shopapi.TicketManagerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2017/5/17.
 */
@RestController
@RequestMapping("/manager/api/ticket")
public class TicketManagerController {

    private Logger log = LoggerFactory.getLogger(TicketManagerController.class);

    @Autowired
    private TicketManagerService ticketManagerService;

    /**
     * 新增券
     * @param data 新增的数据内容
     * @param sid 店铺id
     * @return 新增券的信息
     */
    @ApiOperation("新增券")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertTicket(@RequestParam("data") TicketWithBLOBs data, @RequestParam("sid") Long sid){
        //输入数据为空
        if(data == null){
            return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_EMPTY.getCode(),ErrorCodeEnum.TICKET_INSERT_EMPTY.getMsg());
        }
        //店铺id为空
        if(sid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_EMPTY_ID.getCode(),ErrorCodeEnum.TICKET_INSERT_EMPTY_ID.getMsg());
        }

        try {
            Ticket ticketMsg = ticketManagerService.insertTicket(data , sid);
            if(ticketMsg != null){
               return ResultHelper.genResult(ticketMsg,ErrorCodeEnum.OK.getCode(),ErrorCodeEnum.OK.getMsg());
            }
        }catch(Exception e){
            log.error("插入券失败，原因：" + e.getLocalizedMessage());
        }
        return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_FAILED.getCode(),ErrorCodeEnum.TICKET_INSERT_FAILED.getMsg());
    }

    @ApiOperation("刚登陆展示一定数量票")
    @RequestMapping(value = "/showlist", method = RequestMethod.GET)
    public Result showTicketList(@RequestParam("sid") Long sid){

        ticketManagerService.showTicket(sid);

        return null;
    }

    /**
     *
     * @return
     */
    @ApiOperation("查看券")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Result selectTicket(@RequestParam("tid") String tid){


        ticketManagerService.selectTicket(tid);
        return null;
    }

    /**
     *
     * @return
     */
    @ApiOperation("删除券")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result deleteTicket(@RequestParam("tid") Long tid){

        return ResultHelper.genResultWithSuccessMessage(ticketManagerService.deleteTicket(tid), "删除券成功");
    }

    /**
     *
     * @return
     */
    @ApiOperation("更新/编辑券")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result updateTicket(@RequestParam("data") String data){


        ticketManagerService.updateTicket(data);
        return null;
//        return ticketManagerService.updateTicket();
    }
}
