package com.itheima.takeout2.model.net.bean;

import java.util.List;

/**
 * 订单的概要信息
 */

public class OrderOverview {
    public int addressId;
    public int userId;
    public List<Cart> cart;
    public long sellerid;
    public int type;
}