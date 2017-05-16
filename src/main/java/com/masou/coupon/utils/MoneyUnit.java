package com.masou.coupon.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额枚举
 *
 * Created by paul on 16/12/21.
 */
public enum MoneyUnit {

    YUAN {
        public Integer toFen(Double d) {
            if(d == null)
                return 0;
            return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).movePointRight(2).intValue();
        }
    },

    FEN {
        public Double toYuan(Integer d) {
            if(d == null)
                return 0d;
            return new BigDecimal(d).movePointLeft(2).doubleValue();
        }
    }

    ;

    public Integer toFen(Double d) {
        throw new AbstractMethodError();
    }

    public Double toYuan(Integer d) {
        throw new AbstractMethodError();
    }
}
