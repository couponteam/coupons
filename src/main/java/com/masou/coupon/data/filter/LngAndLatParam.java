package com.masou.coupon.data.filter;

/**
 * Created by jason on 2017/6/10.
 */
public class LngAndLatParam {

    //经度
    private float longitude;

    //纬度
    private float latitude;

    public LngAndLatParam (float longitude, float latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
