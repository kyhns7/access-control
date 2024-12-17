package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.entity.domain.RoleMenuEntity;
import com.kyhns7.rbac.entity.dto.RoleMenuDto;

import java.util.List;

/**
 * 角色关联菜单(多对多)
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {

    List<Long> getMenuIdsByRoleId(Long roleId);

    void saveRoleMenu(RoleMenuDto roleMenuDto);

}

