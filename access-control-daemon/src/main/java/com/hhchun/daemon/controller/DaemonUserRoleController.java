package com.hhchun.daemon.controller;

import com.hhchun.daemon.common.constant.ValidationConstant.ADD;
import com.hhchun.daemon.common.constant.ValidationConstant.UPDATE;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.entity.dto.DaemonUserRoleDto;
import com.hhchun.daemon.entity.dto.DaemonUserRoleSearchDto;
import com.hhchun.daemon.entity.vo.DaemonUserRoleVo;
import com.hhchun.daemon.service.DaemonUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 后台用户与角色关联
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemon/daemon-user-role")
public class DaemonUserRoleController {
    @Autowired
    private DaemonUserRoleService daemonUserRoleService;

    /**
     * 保存/新增角色与权限关联
     */
    @PostMapping("/save-daemon-user-role")
    public R<?> saveDaemonUserRole(@Validated(ADD.class) @RequestBody DaemonUserRoleDto daemonUserRoleDto) {
        daemonUserRoleService.saveDaemonUserRole(daemonUserRoleDto);
        return R.success();
    }

    /**
     * 修改角色与权限关联
     */
    @PutMapping("/modify-daemon-user-role")
    public R<?> modifyDaemonUserRole(@Validated(UPDATE.class) @RequestBody DaemonUserRoleDto daemonUserRoleDto) {
        daemonUserRoleService.modifyDaemonUserRole(daemonUserRoleDto);
        return R.success();
    }

    /**
     * 删除角色与权限关联
     */
    @DeleteMapping("/remove-daemon-user-role/{daemonUserRoleId}")
    public R<?> removeDaemonUserRole(@PathVariable("daemonUserRoleId") Long daemonUserRoleId) {
        daemonUserRoleService.removeDaemonUserRole(daemonUserRoleId);
        return R.success();
    }

    /**
     * 查询角色下的后台用户列表
     */
    @GetMapping("/daemon-user-list")
    public R<PageResult<DaemonUserRoleVo>> getDaemonUserList(@Validated DaemonUserRoleSearchDto searchDto) {
        PageResult<DaemonUserRoleVo> result = daemonUserRoleService.getDaemonUserList(searchDto);
        return R.success(result);
    }

    /**
     *
     * 查询后台用户下的角色列表
     */
    @GetMapping("/role-list")
    public R<PageResult<DaemonUserRoleVo>> getRoleList(@Validated DaemonUserRoleSearchDto searchDto) {
        PageResult<DaemonUserRoleVo> result = daemonUserRoleService.getRoleList(searchDto);
        return R.success(result);
    }

}
