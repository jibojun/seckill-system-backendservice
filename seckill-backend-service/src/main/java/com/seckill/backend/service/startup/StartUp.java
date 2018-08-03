package com.seckill.backend.service.startup;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: Bojun Ji
 * @Description: load cache when start up
 * @Date: 2018/8/4_2:24 AM
 */
public class StartUp implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO: load cache
    }
}
