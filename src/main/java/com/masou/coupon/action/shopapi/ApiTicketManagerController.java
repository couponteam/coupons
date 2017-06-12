package com.masou.coupon.action.shopapi;

import com.masou.coupon.action.api.vo.TicketResultVO;
import com.masou.coupon.action.api.vo.TicketVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.VerifyService;
import com.masou.coupon.service.api.UserTokenService;
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
@RequestMapping("/shop/api/ticket")
public class ApiTicketManagerController {

    private Logger log = LoggerFactory.getLogger(ApiTicketManagerController.class);

    @Autowired
    private TicketManagerService ticketManagerService;

    @Autowired
    private VerifyService verifyService;
    @Autowired
    private UserTokenService userTokenService;

    /**
     * 新增券
     * @param data 新增的数据内容
     * @param sid 店铺id
     * @return 新增券的信息
     */
    @ApiOperation("新增券")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertTicket(@RequestParam("data") String data,
                               @RequestParam("sid") String sid,
                               @RequestParam("token") String token){
        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }

        //输入数据为空
        if(data == null){
            return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_EMPTY.getCode(),ErrorCodeEnum.TICKET_INSERT_EMPTY.getMsg());
        }
        //店铺id为空
        if(sid == null || sid.length() < 0){
            return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_EMPTY_ID.getCode(),ErrorCodeEnum.TICKET_INSERT_EMPTY_ID.getMsg());
        }

        try {
            Ticket ticketMsg = ticketManagerService.insertTicket(data);
            if(ticketMsg != null){
               return ResultHelper.genResult(ticketMsg,ErrorCodeEnum.OK.getCode(),ErrorCodeEnum.OK.getMsg());
            }
        }catch(UserException e){
            log.error(e.getMsg());
            return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_FAILED.getCode(),e.getMsg());
        }catch(Exception e){
            log.error("插入券失败，原因：" + e.getLocalizedMessage());
        }
        return ResultHelper.genResult(ErrorCodeEnum.TICKET_INSERT_FAILED.getCode(),ErrorCodeEnum.TICKET_INSERT_FAILED.getMsg());
    }

    /**
     *
     * @return
     */
    @ApiOperation("查看单张券")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Result selectTicket(@RequestParam("tid") Long tid,
                               @RequestParam("token") String token){
        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }
        TicketVO tieket = ticketManagerService.selectSingleTicket(tid);
        if(tieket != null){
            return ResultHelper.genResultWithSuccess(tieket);
        }
        return ResultHelper.genResult(tieket, ErrorCodeEnum.TICKET_SELECT_FAILED.getCode(), ErrorCodeEnum.TICKET_SELECT_FAILED.getMsg());
    }

    @ApiOperation("根据shop id查看店铺的券列表")
    @RequestMapping(value = "/selectlist", method = RequestMethod.GET)
    public Result selectTicketList(@RequestParam("sid") Long sid,
                                   @RequestParam("page") Integer page,
                                   @RequestParam("pageSize") Integer pageSize){
        TicketResultVO ticketResultVO = ticketManagerService.showTicketList(sid, page, pageSize);
        if(ticketResultVO != null){
            return ResultHelper.genResultWithSuccess(ticketResultVO);
        }
        return ResultHelper.genResult(ticketResultVO, ErrorCodeEnum.TICKET_SELECT_FAILED.getCode(), ErrorCodeEnum.TICKET_SELECT_FAILED.getMsg());
    }

    /**
     *
     * @return
     */
    @ApiOperation("删除券")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result deleteTicket(@RequestParam("tid") Long tid,
                               @RequestParam("token") String token){
        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }
        int rows = ticketManagerService.deleteTicket(tid);
        if(rows > 0){
            return ResultHelper.genResultWithSuccess("删除券"+tid+"成功");
        }
        return ResultHelper.genResult(ErrorCodeEnum.TICKET_DELETE_FAILED.getCode(), ErrorCodeEnum.TICKET_DELETE_FAILED.getMsg());
    }

    /**
     *
     * @return
     */
    @ApiOperation("更新/编辑券")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result updateTicket(@RequestParam(" 5月") String data,
                               @RequestParam("token") String token){
        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }
        int rows = ticketManagerService.updateTicket(data);
        if(rows > 0){
            return ResultHelper.genResultWithSuccess();
        }
        return ResultHelper.genResult(data, ErrorCodeEnum.TICKET_UPDATE_FAILED.getCode(), ErrorCodeEnum.TICKET_UPDATE_FAILED.getMsg());
    }
}