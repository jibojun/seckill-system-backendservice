package com.seckill.backend.service.mq;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;

/**
 * @Author: Bojun Ji
 * @Description: kafka consumer to subscribe submit order message
 * @Date: 2018/8/4_2:20 AM
 */
public class MqConsumer {
    //TODO: mq consumer
    KafkaConsumer<String, String> consumer = new KafkaConsumer(new HashMap<>());
}
