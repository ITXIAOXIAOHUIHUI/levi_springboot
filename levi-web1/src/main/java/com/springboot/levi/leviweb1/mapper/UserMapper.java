package com.springboot.levi.leviweb1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.levi.leviweb1.dto.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jianghaihui
 * @date 2021/1/10 23:41
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
