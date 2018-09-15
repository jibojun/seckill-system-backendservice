package com.seckill.backend.service.mq;

import com.seckill.backend.common.logger.LogUtil;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * @Author: Bojun Ji
 * @Description: kafka consumer to subscribe submit order message, generate order in DB
 * @Date: 2018/8/4_2:20 AM
 */
@Component
public class OrderConsumer {
    private static Properties properties;

    static {
        initConsumerConfig();
        LogUtil.logInfo(OrderProducer.class, String.format("init consumer configuration: %s", properties));
    }

    public void consumer(List<String> topics) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(topics);
    }

    private static void initConsumerConfig() {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "orderConsumerGroup");
        //close auto commit offset
        properties.put("enable.auto.commit", "false");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "60000");
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
    }
}
