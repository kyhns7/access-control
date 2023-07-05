package com.hhchun.daemon.controller;

import com.hhchun.daemon.common.constant.ValidationConstant.ADD;
import com.hhchun.daemon.common.constant.ValidationConstant.UPDATE;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.entity.dto.RolePermissionDto;
import com.hhchun.daemon.entity.dto.RolePermissionSearchDto;
import com.hhchun.daemon.entity.vo.RolePermissionVo;
import com.hhchun.daemon.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色与权限关联
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemon/role-permission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 保存/新增角色与权限关联
     */
    @PostMapping("/save-role-permission")
    public R<?> saveRolePermission(@Validated(ADD.class) @RequestBody RolePermissionDto rolePermissionDto) {
        rolePermissionService.saveRolePermission(rolePermissionDto);

        return R.success();
    }

    /**
     * 修改角色与权限关联
     */
    @PutMapping("/modify-role-permission")
    public R<?> modifyRolePermission(@Validated(UPDATE.class) @RequestBody RolePermissionDto rolePermissionDto) {
        rolePermissionService.modifyRolePermission(rolePermissionDto);
        return R.success();
    }

    /**
     * 删除角色与权限关联
     */
    @DeleteMapping("/remove-role-permission/{rolePermissionId}")
    public R<?> removeRolePermission(@PathVariable("rolePermissionId") Long rolePermissionId) {
        rolePermissionService.removeRolePermission(rolePermissionId);
        return R.success();
    }

    /**
     * 查询角色下的权限列表
     */
    @GetMapping("/permission-list")
    public R<PageResult<RolePermissionVo>> getPermissionList(@Validated RolePermissionSearchDto searchDto) {
        PageResult<RolePermissionVo> result = rolePermissionService.getPermissionList(searchDto);
        return R.success(result);
    }
    /**
     *
     * 查询权限下的角色列表
     */
    @GetMapping("/role-list")
    public R<PageResult<RolePermissionVo>> getRoleList(@Validated RolePermissionSearchDto searchDto) {
        PageResult<RolePermissionVo> result = rolePermissionService.getRoleList(searchDto);
        return R.success(result);
    }

}
