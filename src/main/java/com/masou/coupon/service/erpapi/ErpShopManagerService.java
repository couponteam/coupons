package com.masou.coupon.service.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.Shop;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/6/12.
 */
@Service
public class ErpShopManagerService {

    public List<Shop> shopList(Long uid, Integer page, Integer pageSize){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setUid(uid);
        shopFilter.setOffset(page);
        shopFilter.setLimit(pageSize);



        return null;
    }

    public Shop shopBysid(){
        return null;
    }

    public int updateShop(){



        return 0;
    }
}
