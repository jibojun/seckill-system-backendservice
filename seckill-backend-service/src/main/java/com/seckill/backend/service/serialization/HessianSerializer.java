package com.seckill.backend.service.serialization;

import com.alibaba.com.caucho.hessian.io.HessianOutput;
import com.seckill.backend.common.logger.LogUtil;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @Author: Bojun Ji
 * @Date: Created in 2018-09-17 17:55
 * @Description:
 */
public class HessianSerializer implements Serializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(data);
            return os.toByteArray();
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("hessian serializer error: %s", e));
        }
        return null;
    }

    @Override
    public void close() {

    }
}
