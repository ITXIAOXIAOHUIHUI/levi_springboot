package com.levi.springboot.leviweb.dao;

import com.levi.springboot.leviweb.entity.RequestLog;
import com.levi.springboot.leviweb.entity.RequestLogWithBLOBs;

public interface RequestLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RequestLogWithBLOBs record);

    int insertSelective(RequestLogWithBLOBs record);

    RequestLogWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RequestLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RequestLogWithBLOBs record);

    int updateByPrimaryKey(RequestLog record);
}