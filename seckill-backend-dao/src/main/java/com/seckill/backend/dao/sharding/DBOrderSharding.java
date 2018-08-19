package com.seckill.backend.dao.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.seckill.backend.common.constants.CommonConstants;

import java.util.Collection;

/**
 * @Author: Bojun Ji
 * @Description: db sharding
 * @Date: 2018/8/3_12:12 AM
 */
public class DBOrderSharding implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    /**
     * equal, db sharding
     *
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        int id = shardingValue.getValue();
        int index = id % CommonConstants.DB_SHARDING_NUMBER;
        for (String item : collection) {
            if (item.endsWith(index + "")) {
                return item;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * in
     *
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        return null;
    }

    /**
     * between
     *
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        return null;
    }
}
