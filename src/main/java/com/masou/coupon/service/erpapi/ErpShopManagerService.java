package com.masou.coupon.service.erpapi;

import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.StatusEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.erpapi.ErpShopDao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.UserApplyMapper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.UserApply;
import com.masou.coupon.exception.UserException;
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
    public Result applyForShopList(Long id, String status){
        ShopFilter shopFilter = new ShopFilter();
        if(status != null && status.trim().length() > 0){
            shopFilter.setStatus(Integer.parseInt(status));
        }
        if (id != null && id > 0){
            if(status == null || status.trim().length() <= 0){
                throw new UserException("状态值为空");
            }
            //有id，表示为更新操作
            UserApply userApply = new UserApply();
            userApply.setId(id);
            userApply.setStatus(Byte.parseByte(status));
            return ResultHelper.genResultWithSuccess(userApplyMapper.updateByPrimaryKeySelective(userApply));
        }

        List<UserApply> userApplies = erpShopDao.applyForShopList(shopFilter);
        if(userApplies != null && userApplies.size() > 0){
            for (UserApply userApply : userApplies) {
                userApply.set_status(changeApplyOpStatus(userApply.getStatus()));
            }
            return ResultHelper.genResultWithSuccess(userApplies);
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }

    /**
     * 修改申请的店铺的状态为中文
     * @param status
     * @return
     */
    private String changeApplyOpStatus(Byte status){
        if(status != null && status > 0){
            Integer statusInt = Integer.parseInt(status.toString());
            if (statusInt == StatusEnum.ERP_APPLY_SHOP_DOING.getStatus()){
                return StatusEnum.ERP_APPLY_SHOP_DOING.getComment();
            }

            if (statusInt == StatusEnum.ERP_APPLY_SHOP_SUCC.getStatus()){
                return StatusEnum.ERP_APPLY_SHOP_SUCC.getComment();
            }

            if (statusInt == StatusEnum.ERP_APPLY_SHOP_DEL.getStatus()){
                return StatusEnum.ERP_APPLY_SHOP_DEL.getComment();
            }
        }
        return status.toString();
    }

    public Result bestShopList(){
        List<Shop> shops = erpShopDao.bestShopList();
        if(shops != null && shops.size() > 0) {
            return ResultHelper.genResultWithSuccess(shops);
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }
}
