package com.company.rabbitmq;

import com.company.config.RabbitConfig;
import com.company.entity.User;
import com.company.service.GoodsService;
import com.company.service.RedisService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    RedisService redisServic;
/*
    //从队列消费
    @RabbitListener(queues=RabbitConfig.QUEUE)
    public void receive(String message){
        log.info("receive message:"+message);
        SeckillMessage m = RedisService.stringToBean(message, SeckillMessage.class);
        User user = m.getUser();
        long goodsId = m.getGoodsId();
        goodsService.reduceStock(goodsId);

    }*/

   /* @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(" topic  queue1 message:" + message);
    }*/

 /*   @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    public void receiveTopic2(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel)throws Exception {
        channel.basicAck(deliveryTag,false);
        System.out.print("这里是接收者1答应消息： ");
        System.out.println("SYS_TOPIC_ORDER_CALCULATE_ZZ_FEE process1  : " + message);
    }*/
}
