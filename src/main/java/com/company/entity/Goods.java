package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {

    private Long id;
    //商品名称
    private String goods_name;
    //商品标题
    private String goods_title;
    //商品地址
    private String goods_img;
    //商品详情
    private String goods_detail;
    //商品价格
    private BigDecimal goods_price;
    //   商品库存
    private Long goods_stock;

}
