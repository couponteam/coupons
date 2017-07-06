package com.masou.coupon.data.models;

import lombok.Data;

import java.util.Date;

/**
 * Created by Paul on 2017/7/6.
 */
@Data
public class UserTicketPersonal {

    private Long id;

    private Long userId;

    private String ticketId;

    private Byte status;

    private Date createTime;

    private Date lastUpdateTime;

    private String utId;

    private String statusStr;

    private Integer num;

    private Shop shop;

    private Ticket ticket;


}
