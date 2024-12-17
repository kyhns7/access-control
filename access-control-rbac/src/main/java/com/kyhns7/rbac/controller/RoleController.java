package com.kyhns7.rbac.controller;

import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.entity.dto.RoleDto;
import com.kyhns7.rbac.entity.dto.RoleSearchDto;
import com.kyhns7.rbac.entity.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kyhns7.rbac.service.RoleService;
import com.kyhns7.rbac.common.utils.R;

/**
 * 角色
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    public R<PageResult<RoleVo>> getRoleList(@RequestBody RoleSearchDto searchDto) {
        PageResult<RoleVo> page = roleService.getRoleList(searchDto);

        return R.success(page);
    }

    @PostMapping("/save")
    public R<?> saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);

        return R.success();
    }

    @PostMapping("/modify")
    public R<?> modifyRole(@RequestBody RoleDto roleDto) {
        roleService.modifyRole(roleDto);

        return R.success();
    }

    @PostMapping("/remove/{id}")
    public R<?> removeRole(@PathVariable("id") Long id) {
        roleService.removeRole(id);

        return R.success();
    }

}
