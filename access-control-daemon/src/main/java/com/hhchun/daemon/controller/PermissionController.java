package com.hhchun.daemon.controller;


import com.hhchun.daemon.common.constant.ValidationConstant.ADD;
import com.hhchun.daemon.common.constant.ValidationConstant.UPDATE;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.entity.dto.PermissionDto;
import com.hhchun.daemon.entity.dto.PermissionSearchDto;
import com.hhchun.daemon.entity.vo.PermissionVo;
import com.hhchun.daemon.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 权限
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemon/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 保存/新增权限类别
     */
    @PostMapping("/save-permission")
    public R<?> savePermission(@Validated(ADD.class) @RequestBody PermissionDto permissionDto) {
        permissionService.savePermission(permissionDto);

        return R.success();
    }

    /**
     * 修改权限类别
     */
    @PutMapping("/modify-permission")
    public R<?> modifyPermission(@Validated(UPDATE.class) @RequestBody PermissionDto permissionDto) {
        permissionService.modifyPermission(permissionDto);
        return R.success();
    }

    /**
     * 删除权限类别
     */
    @DeleteMapping("/remove-permission/{permissionId}")
    public R<?> removePermission(@PathVariable("permissionId") Long permissionId) {
        permissionService.removePermission(permissionId);
        return R.success();
    }

    /**
     * 查询权限类别列表
     */
    @GetMapping("/permission-list")
    public R<PageResult<PermissionVo>> getPermissionList(@Validated PermissionSearchDto searchDto) {
        PageResult<PermissionVo> result = permissionService.getPermissionList(searchDto);
        return R.success(result);
    }

}
