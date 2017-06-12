package com.masou.coupon.service.api;

import com.masou.coupon.dao.api.SearchDao;
import com.masou.coupon.data.models.HotWord;
import com.masou.coupon.data.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 2017/5/25.
 */
@Service
public class SearchService {

    @Autowired
    private SearchDao searchDao;

    /**
     * 关键词插入
     * @param keyword
     * @return
     */
    public int insertKeyword(String keyword){
       return searchDao.insertKeyword(keyword);
    }

//    /**
//     * 查询热词
//     * @param limit
//     * @return
//     */
//    public List<HotWord> hotword(Integer limit){
//        return searchDao.hotWords(limit);
//    }
}
