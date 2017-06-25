package com.masou.coupon.dao.ShopApiDao;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.data.filter.AddressFilter;
import com.masou.coupon.data.mappers.*;
import com.masou.coupon.data.models.*;
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

    @Autowired
    private DistrictMapper districtMapper;

    public List<TicketType> ticketType(){
        return ticketTypeMapper.selectList();
    }

    public Industry selectByTicketType(Integer id){
        return industryMapper.selectByPrimaryKey(id);
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

    public int industryTypeUpdate(Industry record){
        return industryMapper.updateByPrimaryKeySelective(record);
    }

    public int industryTypeInsert(Industry record){
        return industryMapper.insertSelective(record);
    }

    public int ticketTypeUpdate(TicketType record){
        return ticketTypeMapper.updateByPrimaryKeySelective(record);
    }

    public int ticketTypeInsert(TicketType record){
        return ticketTypeMapper.insertSelective(record);
    }

    public Industry selectByPrimaryKey(Integer id){
        return industryMapper.selectByPrimaryKey(id);
    }

    public List<Province> provinceList(AddressFilter ads){
        return provinceMapper.selectList();
    }

    public List<City> cityList(AddressFilter ads){
        return cityMapper.selectByProvinceKey(ads.getProvince());
    }

    public List<District> discList(AddressFilter ads){
        return districtMapper.selectList(ads);
    }

}
