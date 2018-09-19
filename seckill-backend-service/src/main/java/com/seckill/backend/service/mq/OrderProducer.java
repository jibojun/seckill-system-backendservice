package com.seckill.backend.service.mq;

import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.entity.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/9/13_12:54 AM
 */
@Component
public class OrderProducer {
    @Autowired
    private ProducerListenser producerListenser;

    private static Properties properties;

    static {
        initProducerConfig();
        LogUtil.logInfo(OrderProducer.class, String.format("init producer configuration: %s", properties));
    }

    public void sendMessage(String topicName, String key, Order value) {
        try (KafkaProducer<String, Order> producer = new KafkaProducer<>(properties)) {
            if (!StringUtils.isEmpty(topicName) && !StringUtils.isEmpty(key) && value != null) {
                producer.send(new ProducerRecord<>(topicName, key, value), producerListenser);
            }
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("met exception when sending msg, exception is: %s", e));
        }
    }

    private static void initProducerConfig() {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("acks", "all");
        properties.put("retries", 10);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer",
                "com.seckill.backend.service.serialization.HessianSerializer");
        properties.put("value.serializer",
                "com.seckill.backend.service.serialization.HessianSerializer");
    }
}

