package com.seckill.backend.service.mq;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;

/**
 * @Author: Bojun Ji
 * @Description: kafka consumer to subscribe submit order message, generate order in DB
 * @Date: 2018/8/4_2:20 AM
 */
public class MqConsumer {
    //TODO: mq consumer,get submitted order from high level system
    KafkaConsumer consumer = new KafkaConsumer(new HashMap<>());
}
