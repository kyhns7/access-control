package com.hhchun.daemon.dao;

import com.hhchun.daemon.entity.domain.PermissionCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限类别/分类
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface PermissionCategoryDao extends BaseMapper<PermissionCategoryEntity> {
	
}
