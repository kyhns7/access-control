package com.hhchun.daemon.dao;

import com.hhchun.daemon.entity.domain.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
	
}
