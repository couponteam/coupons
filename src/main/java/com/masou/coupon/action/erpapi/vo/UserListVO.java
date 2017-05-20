package com.masou.coupon.action.erpapi.vo;

import com.masou.coupon.data.models.User;
import lombok.Data;

import java.util.List;

/**
 * Created by Paul on 2017/5/4.
 */
@Data
public class UserListVO extends BaseListVO {

    private List<User> list;

    private Integer total;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
