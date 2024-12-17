package com.kyhns7.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyhns7.rbac.entity.domain.MenuEntity;
import com.kyhns7.rbac.entity.domain.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {
    
}
