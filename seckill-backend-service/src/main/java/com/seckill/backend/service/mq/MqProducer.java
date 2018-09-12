package com.seckill.backend.service.mq;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/9/13_12:54 AM
 */
public class MqProducer {
    //TODO: send message, async, create order in DB
    KafkaProducer producer = new KafkaProducer(new HashMap<>());
}
