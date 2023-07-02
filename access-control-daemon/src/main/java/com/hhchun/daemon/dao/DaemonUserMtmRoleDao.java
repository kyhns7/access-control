package com.hhchun.daemon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhchun.daemon.entity.domain.DaemonUserMtmRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户关联角色(多对多)
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface DaemonUserMtmRoleDao extends BaseMapper<DaemonUserMtmRoleEntity> {
	
}
