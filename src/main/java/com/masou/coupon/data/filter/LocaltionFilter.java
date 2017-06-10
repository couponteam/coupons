package com.masou.coupon.data.filter;

/**
 * Created by jason on 2017/6/10.
 */
public class LocaltionFilter extends ShopFilter{

    private LngAndLatParam leftTop;

    private LngAndLatParam rightTop;

    private LngAndLatParam leftBottom;

    private LngAndLatParam rightBottom;

    public LngAndLatParam getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(LngAndLatParam leftTop) {
        this.leftTop = leftTop;
    }

    public LngAndLatParam getRightTop() {
        return rightTop;
    }

    public void setRightTop(LngAndLatParam rightTop) {
        this.rightTop = rightTop;
    }

    public LngAndLatParam getLeftBottom() {
        return leftBottom;
    }

    public void setLeftBottom(LngAndLatParam leftBottom) {
        this.leftBottom = leftBottom;
    }

    public LngAndLatParam getRightBottom() {
        return rightBottom;
    }

    public void setRightBottom(LngAndLatParam rightBottom) {
        this.rightBottom = rightBottom;
    }
}
