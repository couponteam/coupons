package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.models.UserManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserManager record);

    int insertSelective(UserManager record);

    UserManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserManager record);

    int updateByPrimaryKey(UserManager record);

    List<UserManager> memberList(BaseFilter baseFilter);

    List<UserManager> selectAllList();

    UserManager selectByPhone(@Param("phone") String phone);

    UserManager selectByUid(@Param("uid") Long uid);

}