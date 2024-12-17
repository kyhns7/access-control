package com.kyhns7.rbac.dao;

import com.kyhns7.rbac.entity.domain.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface PermissionDao extends BaseMapper<PermissionEntity> {
	
}
