package com.masou.coupon.service;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.filter.LngAndLatParam;
import com.masou.coupon.data.filter.LocaltionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 距离计算的算法如下：
 * 已知一个点的经纬度，获取该点半径1000m范围内的店铺
 *
 * 1.通过该点经纬度与半径，可画出一个区域范围的圆
 * 2.因为圆比较难算，可得到该圆对应的外切正方形的四个点经纬度
 * 3.通过外切正方形的四个点坐标，去mysql筛选符合要求的店铺坐标
 * 4.对筛选得到的店铺，通过距离算法，得到店铺与用户直接的距离
 * 5.按照距离进行排序
 * 参考资料：计算外切正方形四个坐标：http://digdeeply.org/archives/06152067.html
 *           计算球面两点之间的距离：http://blog.csdn.net/b_h_l/article/details/8657040
 *
 * Created by jason on 2017/6/10.
 */
@Service
public class LocaltionCalcService {

    /**
     * 地球平均半径，单位(m)
     **/
    private static final double EARTH_RADIUS = 6378137;

    /**
     * 计算在用户当前位置范围1000米内的有效店铺
     **/
    private static final double USER_LOCALTION_REDIUS = 1000;

    private Logger logger = LoggerFactory.getLogger(LocaltionCalcService.class);

    /**
     * 获取一个方形区域内的四个点的经纬度，方便去数据库做点筛选
     *
     * @param longitude           经度
     * @param latitude            纬度
     * @param userLocaltionRadius 区域范围，单位m
     */
    public LocaltionFilter lbsCalc(float longitude, float latitude, double userLocaltionRadius) {

        LocaltionFilter localtionFilter = new LocaltionFilter();

        //获取经度的弧度值
        double dlng = Math.toDegrees(2 * Math.asin(Math.sin((userLocaltionRadius / (2 * EARTH_RADIUS)) / Math.cos(Math.toRadians(latitude)))));

        //获取纬度的弧度值
        double dlat = Math.toDegrees(userLocaltionRadius / EARTH_RADIUS);

        localtionFilter.setLeftTop(new LngAndLatParam((float) (longitude - dlng), (float) (latitude + dlat)));
        localtionFilter.setRightTop(new LngAndLatParam((float) (longitude + dlng), (float) (latitude + dlat)));
        localtionFilter.setLeftBottom(new LngAndLatParam((float) (longitude - dlng), (float) (latitude - dlat)));
        localtionFilter.setRightBottom(new LngAndLatParam((float) (longitude + dlng), (float) (latitude - dlat)));

        logger.info("四个点的经纬度分别是：" + JSON.toJSONString(localtionFilter));

        return localtionFilter;
    }

    /**
     * 转化为弧度(rad)
     */
    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     * @return 返回的距离，单位km
     */
    public double GetDistance(LngAndLatParam param1, LngAndLatParam param2) {
        double radLat1 = rad(param1.getLatitude());
        double radLat2 = rad(param2.getLatitude());
        double a = radLat1 - radLat2;
        double b = rad(param1.getLongitude()) - rad(param2.getLongitude());
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    public static void main(String[] args) {

//        float longitude = 39.9649f;
//        float latitude = 116.3086f;
        float longitude = 0f;
        float latitude = 0f;

        LocaltionCalcService localtionCalcService = new LocaltionCalcService();

        LocaltionFilter filter = localtionCalcService.lbsCalc(longitude, latitude, 500);

        System.out.println(JSON.toJSONString(filter));

//        System.out.println("两点之间的距离：" + localtionCalcService.distance(filter.getLeftTop(), filter.getRightTop()));


        System.out.println(localtionCalcService.GetDistance(
                filter.getLeftTop(),
                filter.getRightTop()));

    }


//    private static final double EARTH_RADIUS = 6378137;//赤道半径(单位m)



}
