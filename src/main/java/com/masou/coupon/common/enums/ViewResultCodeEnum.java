package com.masou.coupon.common.enums;

import com.masou.coupon.utils.EnumMsg;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * 显示用的结果码.
 *
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 1.0 2016年10月20日
 * @since 1.0
 */
public enum ViewResultCodeEnum implements EnumMsg<Integer> {

    /**
     * 成功.
     */
    SUCCESS(0, "成功"),

    /**
     * 服务器未知异常.
     */
    UNKNOWN_EXCEPTION(-1, "服务器未知异常"),

    /**
     * 参数不正确.
     */
    PARAM_INVALID(1, "参数不正确"),

    /**
     * 绑定类型不正确.
     */
    BIND_TYPE_INVALID(1605, "绑定类型不正确"),;

    /**
     * 枚举码.
     */
    private int code;

    /**
     * 枚举描述.
     */
    private String msg;

    /**
     * 构造方法.
     *
     * @param code 枚举码
     * @param msg  枚举描述
     */
    private ViewResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 格式化枚举描述信息.
     *
     * @return the formatArgs 格式化参数
     */
    public String getMsg(Object[] formatArgs) {
        if (ArrayUtils.isEmpty(formatArgs) || StringUtils.isBlank(msg)) {
            return msg;
        }

        return MessageFormat.format(msg, formatArgs);
    }

    /*
     * (non-Javadoc)
     * @see com.store59.kylin.utils.EnumCode#getCode()
     */
    public Integer getCode() {
        // TODO Auto-generated method stub
        return this.code;
    }

    /*
     * (non-Javadoc)
     * @see com.store59.kylin.utils.EnumMsg#getMsg()
     */
    public String getMsg() {
        // TODO Auto-generated method stub
        return this.msg;
    }

}
