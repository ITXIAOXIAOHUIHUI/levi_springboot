package com.levi.springboot.mapper;

import com.levi.springboot.model.entity.SysUserEntity;


import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SysUserMapper {
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 根据用户名查询用户
     */
    SysUserEntity selectOne(@Param("userName") String userName) ;

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
}
