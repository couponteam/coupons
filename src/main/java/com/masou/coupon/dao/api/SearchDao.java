package com.masou.coupon.dao.api;

import com.masou.coupon.data.mappers.HotWordMapper;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.models.HotWord;
import com.masou.coupon.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/25.
 */
@Repository
public class SearchDao {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private HotWordMapper hotWordMapper;

    public int insertKeyword(String keyword){
        HotWord hotWord = new HotWord();
        hotWord.setWord(keyword);
        hotWord.setFrequence(1);
        return hotWordMapper.insertSelective(hotWord);
    }

    /**
     * 查询热词
     * @param limit
     * @return
     */
//    public List<HotWord> hotWords(Integer limit){
//        try {
//            return null;
//            return hotWordMapper.selectList(limit);
//        }catch (Exception e){
//            throw new UserException(e);
//        }
//    }
}
