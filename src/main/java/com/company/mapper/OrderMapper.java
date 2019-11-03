package com.company.mapper;


import com.company.entity.Order;

import org.apache.ibatis.annotations.*;

/**
 * Created by jiangyunxiong on 2018/5/23.
 */
@Mapper
public interface OrderMapper {


    @Select("select * from order where user_id = #{userId} and goods_id = #{goodsId}")
    public Order getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertSeckillOrder(Order order);

}
