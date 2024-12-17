package com.kyhns7.rbac.controller;

import com.kyhns7.rbac.common.utils.R;
import com.kyhns7.rbac.entity.dto.RolePermissionDto;
import com.kyhns7.rbac.entity.vo.RolePermissionVo;
import com.kyhns7.rbac.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色关联权限
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/system/role-permission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/list/{roleId}")
    public R<List<Long>> getPermissionIdsByRoleId(@PathVariable("roleId") Long roleId) {
        List<Long> permissionIds = rolePermissionService.getPermissionIdsByRoleId(roleId);
        return R.success(permissionIds);
    }

    @PostMapping("/save")
    public R<?> saveRolePermission(@RequestBody RolePermissionDto rolePermissionDto) {
        rolePermissionService.saveRolePermission(rolePermissionDto);

        return R.success();
    }
}
