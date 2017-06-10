package com.masou.coupon.data.filter;

/**
 * Created by jason on 2017/6/10.
 */
public class LngAndLatParam {

    //经度
    private double longitude;

    //纬度
    private double latitude;

    public LngAndLatParam(){}

    public LngAndLatParam (double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
