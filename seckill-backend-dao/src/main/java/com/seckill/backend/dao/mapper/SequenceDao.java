package com.seckill.backend.dao.mapper;

import com.seckill.backend.dao.entity.Sequence;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SequenceDao {
    List<Sequence> selectAllSequence();

    int updateSequence(@Param("tableName") String tableName);
}
