package com.seckill.backend.dao.sharding;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.mysql.cj.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/3_12:39 AM
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("dataSource_1", createDS("dataSource_1"));
        dataSourceMap.put("dataSource_2", createDS("dataSource_2"));
        //ds rule, default ds
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "dataSource_1");
        //table rule,map physical tables to logical table, can select from logical table
        TableRule orderTableRule = TableRule.builder("order_logical")
                .actualTables(Arrays.asList("order_1", "order_2"))
                .dataSourceRule(dataSourceRule)
                .build();

        //DB, table sharding rules
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new DBSharding()))
                .tableShardingStrategy(new TableShardingStrategy("order_id", new TableSharding())).build();

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    private DataSource createDS(String dataSourceName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(String.format("jdbc:mysql://127.0.0.1:3306/%s", dataSourceName));
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }

}
