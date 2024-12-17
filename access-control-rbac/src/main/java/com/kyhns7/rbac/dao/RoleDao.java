package com.kyhns7.rbac.dao;

import com.kyhns7.rbac.entity.domain.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
	
}
