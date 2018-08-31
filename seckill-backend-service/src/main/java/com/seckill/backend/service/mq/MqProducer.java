package com.seckill.backend.service.mq;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/6_1:07 AM
 */
public class MqProducer {
    //TODO: producer,send message with DB request
    KafkaProducer producer = new KafkaProducer(new HashMap<>()); 
}
