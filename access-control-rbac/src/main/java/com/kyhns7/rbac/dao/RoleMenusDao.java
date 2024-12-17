package com.kyhns7.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyhns7.rbac.entity.domain.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色关联权限(多对多)
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface RoleMenusDao extends BaseMapper<RoleMenuEntity> {

}
