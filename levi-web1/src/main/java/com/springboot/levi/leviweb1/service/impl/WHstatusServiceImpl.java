package com.springboot.levi.leviweb1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.levi.leviweb1.dto.domain.WhStatus;
import com.springboot.levi.leviweb1.mapper.WhStatusMapper;
import com.springboot.levi.leviweb1.service.WHstatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jianghaihui
 * @date 2020/11/10 14:46
 */
@Service
public class WHstatusServiceImpl implements WHstatusService {

    @Resource
    private WhStatusMapper mapper;

    @Override
    public List<WhStatus> getAll() {
        QueryWrapper<WhStatus> queryWrapper = new QueryWrapper<>();
        return mapper.selectList(queryWrapper);
    }

    @Override
    public WhStatus queryOne(String line) {
        QueryWrapper<WhStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LINE", line);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public int updateOne(WhStatus whStatus, String line) {
        whStatus.setStatus("1");
        QueryWrapper<WhStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LINE", line);
        return mapper.update(whStatus, queryWrapper);
    }


    @Override
    public int insert(WhStatus record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(WhStatus record) {
        return 0;
    }

    @Override
    public int delete(WhStatus record) {
        return 0;
    }
}
