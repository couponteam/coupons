package com.masou.coupon.service.api;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.ImageResourceDao;
import com.masou.coupon.data.mappers.ImgResourceMapper;
import com.masou.coupon.data.models.ImgResource;
import com.masou.coupon.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 2017/5/20.
 */
@Service
public class ImageResourceService {

    @Autowired
    private ImageResourceDao imageResourceDao;

    public int deleteByPrimaryKey(Long id) {
        return imageResourceDao.deleteByPrimaryKey(id);
    }


    public int insertSelective(ImgResource record) {
        return imageResourceDao.insertSelective(record);
    }

    public ImgResource selectByPrimaryKey(Long id) {
        return imageResourceDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ImgResource record) {
        return imageResourceDao.updateByPrimaryKeySelective(record);
    }

}
