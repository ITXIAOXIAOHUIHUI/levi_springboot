package com.levi.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.levi.springboot.dto.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jianghaihui
 * @date 2021/1/10 23:41
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
