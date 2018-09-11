package com.seckill.backend.service.startup;

import com.seckill.backend.dao.mapper.ProductDao;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @Author: Bojun Ji
 * @Description: load cache when start up
 * @Date: 2018/8/4_2:24 AM
 */
public class ContextLoaderListenerNew extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //run spring context loader listener's initialization function
        super.contextInitialized(event);
        //TODO: load product cache from DB
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        context.getBean("productDao", ProductDao.class);
    }
}
