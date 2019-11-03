package com.company.controller;

import com.company.entity.Goods;
import com.company.entity.Order;
import com.company.entity.User;
import com.company.rabbitmq.MQReceiver;
import com.company.rabbitmq.MQSender;
import com.company.service.GoodsService;
import com.company.service.OrderService;
import com.company.service.RedisService;
import com.company.service.UserService;
import com.company.utils.RedisUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class DemoController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    private MQSender mqSender;

    @Autowired
    private MQReceiver mqReceiver;

    @Autowired
    private GoodsService goodsService;


    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    @ResponseBody
    public User say() {
//        return  "123";
        User user = userService.getById(1);
        return user;
    }


    @RequestMapping("/t")
    @ResponseBody
    public void say1() {
        redisUtil.set("123", "123");
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send() {
        mqSender.sendTopic("123");
        return "操作成功";
    }

    /*@RequestMapping("/consume")
    @ResponseBody
    public String consume() {
        mqReceiver.receiveTopic1("123");
        return "操作成功";
    }*/
    @RequestMapping("/reduceStock")
    @ResponseBody
    public String reduceStock( ) {
        goodsService.reduceStock(1L);
        return "秒杀成功";
    }


    @RequestMapping("/user")
    @ResponseBody
    public String user( ) {
        User user=new  User();
        user.setId(1L);
        user.setPassword("123456");
        mqSender.send(RedisService.beanToString(user));
        return "秒杀成功";
    }


    @RequestMapping("/test1")
    @ResponseBody
    public String user1( ) {
        User user=new  User();
        user.setId(1L);
        user.setPassword("123456");
        Goods goods=new  Goods();
        goods.setId(1L);
        Order  order=orderService.createOrder(user,goods);
        mqSender.send(order);
        return "进入队列成功";
    }




}
