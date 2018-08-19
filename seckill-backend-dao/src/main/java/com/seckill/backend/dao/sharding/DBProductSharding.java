package com.seckill.backend.dao.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.seckill.backend.common.constants.CommonConstants;

import java.util.Collection;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/19_6:18 PM
 */
public class DBProductSharding implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        int productId = shardingValue.getValue();
        int index = productId % CommonConstants.DB_SHARDING_NUMBER;
        for (String item : availableTargetNames) {
            if (item.endsWith(index + "")) {
                return item;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        return null;
    }
}
