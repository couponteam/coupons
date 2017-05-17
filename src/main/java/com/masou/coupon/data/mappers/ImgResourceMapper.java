package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.ImgResource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImgResource record);

    int insertSelective(ImgResource record);

    ImgResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImgResource record);

    int updateByPrimaryKeyWithBLOBs(ImgResource record);

    int updateByPrimaryKey(ImgResource record);
}