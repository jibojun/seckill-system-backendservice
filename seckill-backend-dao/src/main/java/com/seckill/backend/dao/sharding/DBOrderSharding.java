package com.seckill.backend.dao.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.seckill.backend.common.constants.CommonConstants;

import java.util.Collection;
import java.util.LinkedHashSet;

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
        int orderId = shardingValue.getValue();
        int index = orderId % CommonConstants.DB_SHARDING_NUMBER;
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
        Collection<String> result = new LinkedHashSet<>(collection.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : collection) {
                if (tableName.endsWith(value % CommonConstants.DB_SHARDING_NUMBER + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
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
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % CommonConstants.DB_SHARDING_NUMBER + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
