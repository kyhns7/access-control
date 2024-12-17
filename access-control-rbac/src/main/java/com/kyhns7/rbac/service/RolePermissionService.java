package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.entity.domain.RolePermissionEntity;
import com.kyhns7.rbac.entity.dto.RolePermissionDto;
import com.kyhns7.rbac.entity.vo.RolePermissionVo;

import java.util.List;

/**
 * 角色关联权限(多对多)
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
public interface RolePermissionService extends IService<RolePermissionEntity> {

    List<Long> getPermissionIdsByRoleId(Long roleId);

    List<Long> getPermissionIdsByRoleIds(List<Long> roleIds);

    void saveRolePermission(RolePermissionDto rolePermissionDto);

    List<Long> getRoleIdsByPermissionId(Long permissionId);
}

