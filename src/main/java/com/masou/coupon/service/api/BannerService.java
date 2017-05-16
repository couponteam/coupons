package com.masou.coupon.service.api;

import com.masou.coupon.dao.BannerDao;
import com.masou.coupon.data.models.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Paul on 2017/5/2.
 */
@Service
public class BannerService {

    @Autowired
    private BannerDao bannerDao;


    public int deleteByPrimaryKey(Integer id) {
        return bannerDao.deleteByPrimaryKey(id);
    }

    public int insertSelective(Banner record) {
        if (record.getId() != null) {
            return bannerDao.updateByPrimaryKeySelective(record);
        } else {
            return bannerDao.insertSelective(record);
        }
    }

    public Banner selectByPrimaryKey(Integer id) {
        return bannerDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Banner record) {
        return bannerDao.updateByPrimaryKeySelective(record);
    }


    public List<Banner> selectListBySection(String section) {
        return bannerDao.selectListBySection(section);
    }

}
