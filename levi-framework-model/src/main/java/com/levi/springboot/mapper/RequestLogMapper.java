package com.levi.springboot.mapper;

import com.levi.springboot.model.RequestLog;

public interface RequestLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RequestLog record);

    int insertSelective(RequestLog record);

    RequestLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RequestLog record);

    int updateByPrimaryKey(RequestLog record);
}