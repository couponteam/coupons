package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.ImgResourceMapper;
import com.masou.coupon.data.models.ImgResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 2017/5/20.
 */
@Repository
public class ImageResourceDao {

    @Autowired
    private ImgResourceMapper imgResourceMapper;

    public int deleteByPrimaryKey(Long id) {
        return imgResourceMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(ImgResource record) {
        return imgResourceMapper.insertSelective(record);
    }

    public ImgResource selectByPrimaryKey(Long id) {
        return imgResourceMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ImgResource record) {
        return imgResourceMapper.updateByPrimaryKeySelective(record);
    }

}
