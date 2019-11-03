package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillGood {

    private Long id;
    // 商品Id
    private long goodId;
    //秒杀价格
    private BigDecimal seckillPrice;

    //库存数量
    private Integer stockCount;

    //    开始时间
    private Date startTime;

    //结束时间
    private Date endTime;

    //并发版本控制
    private Integer version;

}
