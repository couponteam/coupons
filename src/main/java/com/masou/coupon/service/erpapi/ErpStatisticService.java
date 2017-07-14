package com.masou.coupon.service.erpapi;

import com.masou.coupon.action.erpapi.vo.ErpStscVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.erpapi.ErpStatisticDao;
import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.models.UserManager;
import com.masou.coupon.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/6/25.
 */
@Service
public class ErpStatisticService {

    @Autowired
    private ErpStatisticDao erpStatisticDao;

    @Autowired
    private DateUtil dateUtil;

    public ErpStscVO statistic(){
        ErpStscVO erpStscVO = new ErpStscVO();
        StatisticFilter statisticFilter = new StatisticFilter();
        BaseFilter baseFilter = new BaseFilter();
        //无须时间参数
        erpStscVO.setMemberTotal(erpStatisticDao.memberCount(baseFilter));
        erpStscVO.setPvTotal(erpStatisticDao.webStatisticPV(baseFilter));
        erpStscVO.setUvTotal(erpStatisticDao.webStatisticUV(baseFilter));

        erpStscVO.setShopTotal(erpStatisticDao.shopCount(baseFilter));
        erpStscVO.setTicketTaken(erpStatisticDao.ticketTaken());
        erpStscVO.setTicketTotal(erpStatisticDao.ticketCount(statisticFilter));

        /**设置时间**/
        baseFilter.setToday(dateUtil.todayOnlyDate());
        statisticFilter.setToday(dateUtil.todayOnlyDate());
        //需要时间参数的请求
        erpStscVO.setPvToday(erpStatisticDao.webStatisticPV(baseFilter));
        erpStscVO.setUvToday(erpStatisticDao.webStatisticUV(baseFilter));
        erpStscVO.setMemberDaily(erpStatisticDao.memberCount(baseFilter));
        erpStscVO.setShopDaily(erpStatisticDao.shopCount(baseFilter));
        erpStscVO.setTicketDaily(erpStatisticDao.ticketCount(statisticFilter));

        return erpStscVO;
    }

    public Result memberList(Integer page, Integer pageSize){
        BaseFilter baseFilter = new BaseFilter();
        baseFilter.setLimit(page);
        baseFilter.setOffset(pageSize);
        if(pageSize == null || pageSize <= 0){
            baseFilter.setOffset(pageSize);
        }
        List<UserManager> userManagers = erpStatisticDao.memberList(baseFilter);
        if (userManagers != null && userManagers.size() > 0){
            return ResultHelper.genResultWithSuccess(userManagers);
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }
}
