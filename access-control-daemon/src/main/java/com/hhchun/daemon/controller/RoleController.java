package com.hhchun.daemon.controller;

import com.hhchun.daemon.common.constant.ValidationConstant.ADD;
import com.hhchun.daemon.common.constant.ValidationConstant.UPDATE;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.entity.dto.RoleDto;
import com.hhchun.daemon.entity.dto.RoleSearchDto;
import com.hhchun.daemon.entity.vo.RoleVo;
import com.hhchun.daemon.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemon/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 保存/新增角色
     */
    @PostMapping("/save-role")
    public R<?> saveRole(@Validated(ADD.class) @RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);

        return R.success();
    }

    /**
     * 修改角色
     */
    @PutMapping("/modify-role")
    public R<?> modifyRole(@Validated(UPDATE.class) @RequestBody RoleDto roleDto) {
        roleService.modifyRole(roleDto);
        return R.success();
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/remove-role/{roleId}")
    public R<?> removeRole(@PathVariable("roleId") Long roleId) {
        roleService.removeRole(roleId);
        return R.success();
    }

    /**
     * 查询角色列表
     */
    @GetMapping("/role-list")
    public R<PageResult<RoleVo>> getRoleList(@Validated RoleSearchDto searchDto) {
        PageResult<RoleVo> result = roleService.getRoleList(searchDto);
        return R.success(result);
    }
}
