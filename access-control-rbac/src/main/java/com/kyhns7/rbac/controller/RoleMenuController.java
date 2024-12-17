package com.kyhns7.rbac.controller;

import com.kyhns7.rbac.common.utils.R;
import com.kyhns7.rbac.entity.dto.RoleMenuDto;
import com.kyhns7.rbac.service.RoleMenuService;
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
@RequestMapping("/system/role-menu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;

    @PostMapping("/list/{roleId}")
    public R<List<Long>> getMenuIdsByRoleId(@PathVariable("roleId") Long roleId) {
        List<Long> menuIds = roleMenuService.getMenuIdsByRoleId(roleId);

        return R.success(menuIds);
    }

    @PostMapping("/save")
    public R<?> saveRoleMenu(@RequestBody RoleMenuDto roleMenuDto) {
        roleMenuService.saveRoleMenu(roleMenuDto);

        return R.success();
    }
}
