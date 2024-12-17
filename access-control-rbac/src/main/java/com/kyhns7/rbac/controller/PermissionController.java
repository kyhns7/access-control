package com.kyhns7.rbac.controller;

import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.entity.dto.PermissionDto;
import com.kyhns7.rbac.entity.dto.PermissionSearchDto;
import com.kyhns7.rbac.entity.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kyhns7.rbac.service.PermissionService;
import com.kyhns7.rbac.common.utils.R;


/**
 * 权限
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/list")
    public R<PageResult<PermissionVo>> getPermissionList(@RequestBody PermissionSearchDto searchDto) {
        PageResult<PermissionVo> page = permissionService.getPermissionList(searchDto);

        return R.success(page);
    }

    @PostMapping("/save")
    public R<?> savePermission(@RequestBody PermissionDto permissionDto) {
        permissionService.savePermission(permissionDto);

        return R.success();
    }

    @PostMapping("/modify")
    public R<?> modifyPermission(@RequestBody PermissionDto permissionDto) {
        permissionService.modifyPermission(permissionDto);

        return R.success();
    }

    @PostMapping("/remove/{id}")
    public R<?> removePermission(@PathVariable("id") Long id) {
        permissionService.removePermission(id);

        return R.success();
    }


}
