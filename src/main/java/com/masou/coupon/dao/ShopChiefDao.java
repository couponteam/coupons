package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.ShopChiefMapper;
import com.masou.coupon.data.models.ShopChief;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 店铺管理员
 * Created by Paul on 2017/5/20.
 */
@Repository
public class ShopChiefDao {

    @Autowired
    private ShopChiefMapper shopChiefMapper;

    public int deleteByPrimaryKey(Integer id) {
        return shopChiefMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(ShopChief record) {
        return shopChiefMapper.insertSelective(record);
    }

    public ShopChief selectByPrimaryKey(Integer id) {
        return shopChiefMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ShopChief record) {
        return shopChiefMapper.updateByPrimaryKeySelective(record);
    }

}
