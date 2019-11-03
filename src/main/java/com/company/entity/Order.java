package com.company.entity;

import lombok.Data;

@Data
public class Order {

    private Long  id;
   //用户Id
    private Long    userId;
    //订单Id
    private  Long  orderId;
    //商品Id
    private  Long   goodsId;
}
