package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.BannerMapper;
import com.masou.coupon.data.models.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paul on 2017/5/2.
 */


@Repository
public class BannerDao {
    @Autowired
    private BannerMapper bannerMapper;


    public int deleteByPrimaryKey(Integer id) {
        return bannerMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(Banner record){
        return bannerMapper.insertSelective(record);
    }

    public Banner selectByPrimaryKey(Integer id){
        return bannerMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Banner record){
        return bannerMapper.updateByPrimaryKeySelective(record);
    }


    public List<Banner> selectListBySection(String section){
        return bannerMapper.selectListBySection(section);
    }

}
