package com.masou.coupon.utils;

/**
 * Null枚举码对象.
 * Created by paul on 16/12/21.
 */
public class NullEnumCode<C> implements EnumCode<C> {

    /**
     * 无参构造.
     */
    private NullEnumCode() {
        super();
    }

    /**
     * 枚举码为{@link String}类型的{@link NullEnumCode}对象.
     */
    public static final EnumCode<String> STRING = new NullEnumCode<String>();

    /**
     * 枚举码为{@link Integer}类型的{@link NullEnumCode}对象.
     */
    public static final EnumCode<Integer> INTEGER = new NullEnumCode<Integer>();

    /**
     * 枚举码为{@link Byte}类型的{@link NullEnumCode}对象.
     */
    public static final EnumCode<Byte> BYTE = new NullEnumCode<Byte>();

    @Override
    public C getCode() {
        return null;
    }

}
