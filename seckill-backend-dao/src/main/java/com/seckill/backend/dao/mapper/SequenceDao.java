package com.seckill.backend.dao.mapper;

import com.seckill.backend.dao.entity.Sequence;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SequenceDao {
    List<Sequence> selectAllSequence() throws DataAccessException;

    int updateSequence(Sequence sequence) throws DataAccessException;
}
