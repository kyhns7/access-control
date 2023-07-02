package com.hhchun.daemon.dao;

import com.hhchun.daemon.entity.domain.DaemonUserMtmPermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户关联权限(多对多)
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface DaemonUserMtmPermissionDao extends BaseMapper<DaemonUserMtmPermissionEntity> {
	
}
