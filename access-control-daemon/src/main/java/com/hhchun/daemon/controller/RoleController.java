package com.hhchun.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hhchun.daemon.entity.domain.RoleEntity;
import com.hhchun.daemon.service.RoleService;
import com.hhchun.daemon.common.utils.R;

/**
 * 角色
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R<?> save(@RequestBody RoleEntity role) {
        roleService.save(role);

        return R.success();
    }

}
