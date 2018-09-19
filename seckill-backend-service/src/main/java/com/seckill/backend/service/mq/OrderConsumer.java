package com.seckill.backend.service.mq;

import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.entity.Order;
import com.seckill.backend.service.api.IDbOpsService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * @Author: Bojun Ji
 * @Description: kafka consumer to subscribe submit order message, generate order in DB
 * @Date: 2018/8/4_2:20 AM
 */
@Component
public class OrderConsumer {
    @Autowired
    private IDbOpsService dbOpsService;

    private static Properties properties;

    static {
        initConsumerConfig();
        LogUtil.logInfo(OrderProducer.class, String.format("init consumer configuration: %s", properties));
    }

    public void consumer(List<String> topics) {
        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(topics);
        ConsumerRecords<String, Order> consumerRecords;
        while (true) {
            consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Order> consumerRecord : consumerRecords) {
                long offset = consumerRecord.offset();
                int partition = consumerRecord.partition();
                String key = consumerRecord.key();
                Order value = consumerRecord.value();
                LogUtil.logInfo(this.getClass(), offset + " " + partition + " " + key + " " + value);
                if (key.equalsIgnoreCase(OrderMqConstants.ORDER_KEY)) {
                    LogUtil.logInfo(this.getClass(), String.format("got a message and put order to DB, order: %s", value));
                    boolean result = dbOpsService.createOrderAndUpdateProduct(value);
                    LogUtil.logInfo(this.getClass(), String.format("order and product DB operation result: %s", result));
                }
            }
        }
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
                "com.seckill.backend.service.serialization.HessianDeserializer");
        properties.put("value.deserializer",
                "com.seckill.backend.service.serialization.HessianDeserializer");
    }
}
