package com.liujj.sharding.jdbc.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.liujj.config.Database0Config;
import com.liujj.config.Database1Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;


@Component
public class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    public static final Long SPLIT_VALUE = 20L;

    @Autowired
    private Database0Config database0Config;

    @Autowired
    private Database1Config database1Config;

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValue();
        if (value < SPLIT_VALUE) {
            return database0Config.getDatabaseName();
        } else {
            return database1Config.getDatabaseName();
        }
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            if (value <= SPLIT_VALUE) {
                result.add(database0Config.getDatabaseName());
            } else {
                result.add(database1Config.getDatabaseName());
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long value = range.lowerEndpoint(); range.upperEndpoint() >= value; value++) {
            if (value <= SPLIT_VALUE) {
                result.add(database0Config.getDatabaseName());
            } else {
                result.add(database1Config.getDatabaseName());
            }
        }
        return result;
    }
}
