package com.masou.coupon.action.erpapi.vo;

import com.masou.coupon.data.models.City;
import com.masou.coupon.data.models.Province;

import java.util.List;

/**
 * Created by jason on 2017/5/21.
 */
public class ProvinceListVO {

    protected Province province;

    protected List<City> cityList;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
