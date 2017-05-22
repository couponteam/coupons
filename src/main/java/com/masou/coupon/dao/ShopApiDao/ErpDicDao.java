package com.masou.coupon.dao.ShopApiDao;

import com.masou.coupon.data.mappers.*;
import com.masou.coupon.data.models.City;
import com.masou.coupon.data.models.Industry;
import com.masou.coupon.data.models.Province;
import com.masou.coupon.data.models.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/21.
 */
@Repository
public class ErpDicDao {

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Autowired
    private IndustryMapper industryMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    public List<TicketType> ticketType(){
        return ticketTypeMapper.selectList();
    }

    public List<Industry> industryType(){
        return industryMapper.selectList();
    }

    public List<Province> provinceType(){
        return provinceMapper.selectList();
    }

    public  List<City> cityType(Integer provinceId){
        return cityMapper.selectByProvinceKey(provinceId);
    }
}
