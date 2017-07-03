package com.masou.coupon.service.erpapi;

import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.erpapi.ErpShopDao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.UserApplyMapper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.UserApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/6/12.
 */
@Service
public class ErpShopManagerService {

    @Autowired
    private ErpShopDao erpShopDao;

    @Autowired
    private UserApplyMapper userApplyMapper;

    public int bestShopRank(Long sid, Integer rank){
        Shop shop = new Shop();
        shop.setRank(1);
        shop.setLongitude(null);
        shop.setDimensionality(null);
        if(rank != null){
            shop.setRank(rank);
        }
        shop.setId(sid);
        return erpShopDao.bestShopRank(shop);
    }


    public List<Shop> shopList(Long uid, Integer page, Integer pageSize){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setUid(uid);
        shopFilter.setOffset(page);
        shopFilter.setLimit(pageSize);



        return null;
    }

    /**
     * 获取店铺申请列表
     * @param status
     * @return
     */
    public Result applyForShopList(String status){
        ShopFilter shopFilter = new ShopFilter();
        if(status != null && status.trim().length() > 0){
            shopFilter.setStatus(Integer.parseInt(status));
        }else{
            shopFilter.setStatus(DicValue.APPLY_SHOP_DOING);
        }
        List<UserApply> userApplies = erpShopDao.applyForShopList(shopFilter);
        if(userApplies != null && userApplies.size() > 0){
            return ResultHelper.genResultWithSuccess(userApplies);
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }

    public Result bestShopList(){
        List<Shop> shops = erpShopDao.bestShopList();
        if(shops != null && shops.size() > 0) {
            return ResultHelper.genResultWithSuccess(shops);
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }
}
