package com.levi.springboot.mapper;

import com.levi.springboot.model.entity.SysMenuEntity;

import java.util.List;

public interface SysMenuMapper {
    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 查询全部数据
     */
    List<SysMenuEntity> selectList();

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();
}
