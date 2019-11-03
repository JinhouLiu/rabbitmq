package com.company.service;

import com.company.entity.Goods;
import com.company.entity.Order;
import com.company.entity.User;
import com.company.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private OrderMapper orderMapper;


    @Transactional
    public Order createOrder(User user, Goods goods) {
        Order seckillOrder = new Order();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        seckillOrder.setUserId(user.getId());
        orderMapper.insertSeckillOrder(seckillOrder);
        return seckillOrder;
    }



}
