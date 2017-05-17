package com.masou.coupon.data.filter;

import lombok.Data;

/**
 * Created by Paul on 2017/2/21.
 */
@Data
public class BaseFilter {
    private Integer limit;
    private Integer offset;
    private Long uid;

    private String timeBegin;
    private String timeEnd;

    private Integer enable;

    private Integer placeholder=1;

}
