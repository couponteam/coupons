package com.masou.coupon.service.api;

import com.masou.coupon.dao.ShopChiefDao;
import com.masou.coupon.data.models.ShopChief;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 2017/5/20.
 */
@Service
public class ShopChiefService {

    @Autowired
    private ShopChiefDao shopChiefDao;

    public int deleteByPrimaryKey(Integer id) {
        return shopChiefDao.deleteByPrimaryKey(id);
    }


    public int insertSelective(ShopChief record) {
        return shopChiefDao.insertSelective(record);
    }

    public ShopChief selectByPrimaryKey(Integer id) {
        return shopChiefDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ShopChief record) {
        return shopChiefDao.updateByPrimaryKeySelective(record);
    }
}
