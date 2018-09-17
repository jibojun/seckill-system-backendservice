package com.seckill.backend.service.serialization;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.seckill.backend.common.logger.LogUtil;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * @Author: Bojun Ji
 * @Date: Created in 2018-09-17 17:58
 * @Description:
 */
public class HessianDeserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(is);
            return hi.readObject();
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("hessian deserializer error: %s", e));
        }
        return null;
    }

    @Override
    public void close() {

    }
}
