package com.masou.coupon.data.mappers;


import com.masou.coupon.data.models.HotWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotWordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HotWord record);

    int insertSelective(HotWord record);

    HotWord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HotWord record);

    int updateByPrimaryKey(HotWord record);

    List<HotWord> selectList(Integer limit);
}