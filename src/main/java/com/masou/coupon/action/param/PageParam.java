package com.masou.coupon.action.param;

import com.masou.coupon.common.dto.BaseDTO;
import lombok.Data;

/**
 * 分页参数
 * Created by Paul on 16/11/30.
 */
@Data
public class PageParam extends BaseDTO {
    /**
     * 当前页码
     */
    protected Integer page = 1;

    /**
     * 每页大小
     */
    protected Integer pageSize = 20;

    private Integer totalPage ;

    public Integer getOffset() {
        if (pageSize == null || page == null) {
            return 0;
        }
        return (pageSize * (page - 1));
    }

    public Integer getPage() {
        return page - 1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
