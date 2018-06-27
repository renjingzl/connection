package com.baojia.connection.kafka;

/**
 * @author wxr
 * @Title: OrdersConsumer
 * @Description: 定时接收订单服务的kafka消息
 * @date 2018/5/25 19:27*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BikeSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;

/**
 * 发送消息到kafka,主题为test
 */
    public void sendTest(){
        kafkaTemplate.send("test","hello,kafka,hahahaha "  + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }
}
