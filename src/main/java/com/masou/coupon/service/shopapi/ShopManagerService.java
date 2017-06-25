package com.masou.coupon.service.shopapi;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.action.api.vo.ticketvo.ShopTicket;
import com.masou.coupon.action.api.vo.ticketvo.Shops;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.ShopApiDao.ShopManagerDao;
import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.mappers.IndustryMapper;
import com.masou.coupon.data.mappers.UserTicketMapper;
import com.masou.coupon.data.models.*;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.service.RedisService;
import com.masou.coupon.utils.GenTicketIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2017/5/18.
 */
@Service
public class ShopManagerService {

    @Autowired
    private ShopManagerDao shopManagerDao;

    public static final String KEY_INDUSTRY_ID = "key_industry_id";

    @Autowired
    private RedisService redisService;

    @Autowired
    private IndustryMapper industryMapper;

    @Autowired
    private UserTicketMapper userTicketMapper;

    /**
     * 查询店铺列表
     * @param uid
     * @return
     */
    public Shops shopList(Long uid,Integer page, Integer pageSize){
        Shops shops = new Shops();

        BaseFilter b = new BaseFilter();
        b.setUid(uid);
        b.setOffset(pageSize);
        if(pageSize == null){
            b.setOffset(PageParam.PAGESIZE_DEFAULT);
        }
        b.setLimit(page);

        List<Shop> shopList = shopManagerDao.shopList(b);
        List<ShopTicket> shopTickets = new ArrayList<ShopTicket>();
        for (Shop shop: shopList) {
            ShopTicket shopTicket = new ShopTicket();
            changeStatus2Char(shop);
            shopTicket.setShop(shop);
            shopTickets.add(shopTicket);
        }
        shops.setShops(shopTickets);
        b.setLimit(null);
        b.setOffset(null);
        shops.setTotal(shopManagerDao.shopListCount(b));
        return shops;
    }

    /**
     * 查看单个店铺
     * @param sid
     * @return
     */
    public Shop shopBysid(Long sid){
        Shop shop = shopManagerDao.shopBysid(sid);
        changeStatus2Char(shop);
        return shop;
    }

    public Shop changeStatus2Char(Shop shop){
        shop.setIsShopVerifiedStr(changeShopVerify(shop.getIsShopVerified()));
        shop.setIndustryIdStr(changeIndustryId(shop.getIndustryId()));
        return shop;
    }

    //0-待审核；1-已审核；2-未通过；3-已通过
    private String changeShopVerify(Byte isShopVe){
        if(isShopVe != null && isShopVe > 0){
            String msg = "";
            switch (isShopVe){
                case 0:
                    msg = "待审核";
                    break;
                case 1:
                    msg = "已审核";
                    break;
                case 2:
                    msg = "未通过";
                    break;
                case 3:
                    msg = "已通过";
                    break;
            }
            return msg;
        }
        return null;
    }

    public Result userTicketUse(String tid){

        //查询券状态
        Integer status = shopManagerDao.findStatusByUid(tid);
        if(status == null){
            return ResultHelper.genResult(ErrorCodeEnum.TICKET_USE_FAILED.getCode(),"券已下架，无法使用");
        }
        StringBuffer msg = new StringBuffer("券");
        switch (status){
            case(2):
                UserTicket userTicket = new UserTicket();
                userTicket.setUtId(tid);
                userTicket.setStatus(new Byte("3"));
                //更新券状态为3
                if(userTicketMapper.updateByUtId(userTicket) > 0){
                    return ResultHelper.genResultWithSuccess();
                }
                break;
            case(3):
                msg.append("已使用");
                break;
            case(4):
                msg.append("已放弃");
                break;
        }
        return ResultHelper.genResult(ErrorCodeEnum.TICKET_USE_FAILED.getCode(),msg.toString());
    }


    private String changeIndustryId(Integer industryId){
        if(industryId != null){
            if(redisService.exists(KEY_INDUSTRY_ID)){
                return redisService.mapGet(KEY_INDUSTRY_ID, industryId.toString());
            }else{
                List<Industry> list = industryMapper.selectList();
                if(list != null && list.size() > 0){
                    Map<String, String> typeMap = new HashMap<>();
                    for (Industry industry : list) {
                        typeMap.put(industry.getId()+"", industry.getComment());
                    }
                    redisService.mapSet(KEY_INDUSTRY_ID, typeMap, 60 * 60 * 24);
                    return typeMap.get(industryId.toString());
                }
            }
        }
        return null;
    }

    public int delete(Long sid){
        return shopManagerDao.delete(sid);
    }

}
