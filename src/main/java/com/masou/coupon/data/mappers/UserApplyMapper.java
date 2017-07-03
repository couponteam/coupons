package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.UserApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserApply record);

    int insertSelective(UserApply record);

    UserApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserApply record);

    int updateByPrimaryKey(UserApply record);

    List<UserApply> applylist(ShopFilter shopFilter);
}