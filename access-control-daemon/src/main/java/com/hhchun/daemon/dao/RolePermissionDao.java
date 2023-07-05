package com.hhchun.daemon.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhchun.daemon.entity.domain.RolePermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhchun.daemon.entity.dto.RolePermissionSearchDto;
import com.hhchun.daemon.entity.vo.RolePermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色关联权限(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface RolePermissionDao extends BaseMapper<RolePermissionEntity> {

    IPage<RolePermissionVo> getRolePermissionList(@Param("page") IPage<RolePermissionEntity> page,
                                                  @Param("search") RolePermissionSearchDto searchDto);
}
