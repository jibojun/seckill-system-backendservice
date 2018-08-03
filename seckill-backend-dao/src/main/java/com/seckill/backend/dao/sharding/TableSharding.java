package com.seckill.backend.dao.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;

/**
 * @Author: Bojun Ji
 * @Description: table sharding
 * @Date: 2018/8/3_12:12 AM
 */
public class TableSharding implements SingleKeyTableShardingAlgorithm<Integer> {
    /**
     * equal
     *
     * @param collection
     * @param shardingValue
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        return null;
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
