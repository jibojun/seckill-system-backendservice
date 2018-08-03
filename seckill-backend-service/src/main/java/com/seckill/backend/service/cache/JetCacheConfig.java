package com.seckill.backend.service.cache;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Bojun Ji
 * @Description: configure jetcache with caffeind and redis
 * @Date: 2018/8/4_1:25 AM
 */

@Configuration
@EnableMethodCache(basePackages = "com.seckill.backend.service.impl")
@EnableCreateCacheAnnotation
public class JetCacheConfig {

    @Bean
    public Pool<Jedis> pool() {
        //redis pool
        GenericObjectPoolConfig redisPoolConfig = new GenericObjectPoolConfig();
        redisPoolConfig.setMinIdle(2);
        redisPoolConfig.setMaxIdle(10);
        redisPoolConfig.setMaxTotal(10);
        return new JedisPool(redisPoolConfig, "localhost", 6379);
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool) {
        //local cache, caffeine
        Map localBuilders = new HashMap();
        EmbeddedCacheBuilder localBuilder = CaffeineCacheBuilder.createCaffeineCacheBuilder().keyConvertor(FastjsonKeyConvertor.INSTANCE);
        localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

        //remote cache, redis, with encoder and decoder
        Map remoteBuilders = new HashMap();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(JavaValueEncoder.INSTANCE)
                .valueDecoder(JavaValueDecoder.INSTANCE)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setStatIntervalMinutes(15);
        globalCacheConfig.setAreaInCacheName(false);

        return globalCacheConfig;

    }
}
