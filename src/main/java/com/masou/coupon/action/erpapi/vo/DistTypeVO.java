package com.masou.coupon.action.erpapi.vo;

import com.masou.coupon.data.models.City;
import com.masou.coupon.data.models.Province;

/**
 * Created by jason on 2017/5/21.
 */
public class DistTypeVO {

    protected Province province;

    protected City city;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
