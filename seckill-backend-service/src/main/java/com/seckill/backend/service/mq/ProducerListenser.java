package com.seckill.backend.service.mq;

import com.seckill.backend.common.logger.LogUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/9/15_2:25 AM
 */
@Component
public class ProducerListenser implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (recordMetadata != null && e == null) {
            LogUtil.logInfo(this.getClass(), String.format("msg: %s, sent successfully", recordMetadata));
        } else if (recordMetadata == null && e != null) {
            LogUtil.logError(this.getClass(), String.format("msg sent failed, if retriable exception, it will be sent again, detailed exception is: %s ", e));
        }
    }
}
