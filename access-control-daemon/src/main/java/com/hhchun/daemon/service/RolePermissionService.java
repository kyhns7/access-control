package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.entity.domain.RolePermissionEntity;
import com.hhchun.daemon.entity.dto.RolePermissionDto;
import com.hhchun.daemon.entity.dto.RolePermissionSearchDto;
import com.hhchun.daemon.entity.vo.RolePermissionVo;

/**
 * 角色关联权限(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface RolePermissionService extends IService<RolePermissionEntity> {
    RolePermissionEntity getRolePermission(Long roleId, Long permissionId);

    RolePermissionEntity getRolePermissionById(Long rolePermissionId);

    void saveRolePermission(RolePermissionDto rolePermissionDto);

    void modifyRolePermission(RolePermissionDto rolePermissionDto);

    void removeRolePermission(Long rolePermissionId);

    PageResult<RolePermissionVo> getPermissionList(RolePermissionSearchDto searchDto);

    PageResult<RolePermissionVo> getRolePermissionList(RolePermissionSearchDto searchDto);

    PageResult<RolePermissionVo> getRoleList(RolePermissionSearchDto searchDto);
}

