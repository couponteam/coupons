package com.masou.coupon.utils;

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
public class LocaltionUtil {

    /** 地球平均半径，单位(m) **/
    private static final  double EARTH_RADIUS = 6378137;

    /** 计算在用户当前位置范围1000米内的有效店铺 **/
    private static final double  USER_LOCALTION_REDIUS = 1000;

//    public static


}
