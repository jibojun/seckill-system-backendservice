package com.seckill.backend.dao.mapper;

import com.seckill.backend.dao.entity.Sequence;

import java.util.List;

public interface SequenceDao {
    List<Sequence> selectAllSequence();

    int updateSequence(Sequence sequence);
}
