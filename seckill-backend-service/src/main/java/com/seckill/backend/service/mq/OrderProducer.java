package com.seckill.backend.service.mq;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Properties;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/9/13_12:54 AM
 */
@Component
public class OrderProducer {
    //TODO: send message, async, create order in DB
    KafkaProducer producer = new KafkaProducer(new HashMap<>());
    private Properties properties;

    public void initProducerConfig() {
        if (properties == null) {
            synchronized (this) {
                if (properties == null) {
                    properties = new Properties();
                    properties.put("bootstrap.servers", "127.0.0.1:9092");
                    properties.put("acks", "all");
                    properties.put("retries", 3);
                    properties.put("buffer.memory", 33554432);
                    properties.put("key.serializer",
                            "org.apache.kafka.common.serialization.StringSerializer");
                    properties.put("value.serializer",
                            "org.apache.kafka.common.serialization.StringSerializer");
                }
            }
        }
    }
}
