package com.baojia.connection.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wxr
 * @Title: OrdersConsumer
 * @Description: 定时接收订单服务的kafka消息
 * @date 2018/5/25 19:27*/


@Component
public class BikeConsumer {

    private static Logger logger = LoggerFactory.getLogger(BikeConsumer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

/**
     * 监听test主题,有消息就读取
     * @param message*/


    @KafkaListener(topics = {"test"},groupId = "bike-connection")
    public void consumer(String message){
    	logger.info("test topic message : {}"+message);
    }

    /*@KafkaListener(topics = {"order_detail"},groupId = "backstageGroup")
    public void getOrderDetail(String message){
        if(StringUtils.isEmpty(message)){
            logger.info("未接收到topics = {\"order_detail\"}消息！！");
        }
        OrderDetailEntity orderDetailEntity = JSONObject.parseObject(message, OrderDetailEntity.class);

        System.out.println("test topic message : {}"+message);
    }*/

}
