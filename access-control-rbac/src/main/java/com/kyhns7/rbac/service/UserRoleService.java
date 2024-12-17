package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.entity.domain.UserRoleEntity;
import com.kyhns7.rbac.entity.dto.UserRoleDto;

import java.util.List;

/**
 * 用户关联角色(多对多)
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    void saveUserRole(UserRoleDto userRoleDto);

    List<Long> getRoleIdsByUserId(Long userId);

    void bindRole(Long userId, Long roleId);

    void unbindRole(Long userId, Long roleId);

    List<Long> getUserIdsByRoleIds(List<Long> roleIds);

    List<Long> getUserIdsByRoleId(Long roleId);
}

