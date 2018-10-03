package com.seckill.backend.dao.sharding;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.mysql.cj.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Bojun Ji
 * @Description: db sharding configuration
 * @Date: 2018/8/3_12:39 AM
 */
@Configuration
public class ShardingDataSourceConfig {
    @Bean(name = "shardingDataSource")
    public DataSource getDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("dataSource_0", createDS("dataSource1"));
        dataSourceMap.put("dataSource_1", createDS("dataSource2"));
        //ds rule, default ds
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "dataSource1");

        //table's DB sharding rule
        List<String> orderActualTables = new ArrayList<>();
        orderActualTables.add("order");
        TableRule orderDbShardingRule = TableRule.builder("t_order").actualTables(orderActualTables).databaseShardingStrategy(new DatabaseShardingStrategy("order_id", new OrderDbSharding())).dataSourceRule(dataSourceRule).build();
        List<String> productActualTables = new ArrayList<>();
        productActualTables.add("product");
        TableRule productDbShardingRule = TableRule.builder("t_product").actualTables(productActualTables).databaseShardingStrategy(new DatabaseShardingStrategy("product_id", new ProductDbSharding())).dataSourceRule(dataSourceRule).build();

        List<TableRule> tableRuleList = new ArrayList<>();
        tableRuleList.add(orderDbShardingRule);
        tableRuleList.add(productDbShardingRule);

        //sharding rule
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(tableRuleList)
                .databaseShardingStrategy(new DatabaseShardingStrategy("order_id", new OrderDbSharding())).build();

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    private DataSource createDS(String dataSourceName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("test123");
        dataSource.setInitialSize(0);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        return dataSource;
    }

}
