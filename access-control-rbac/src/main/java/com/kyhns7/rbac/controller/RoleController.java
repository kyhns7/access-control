package com.kyhns7.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kyhns7.rbac.entity.domain.RoleEntity;
import com.kyhns7.rbac.service.RoleService;
import com.kyhns7.rbac.common.utils.R;

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
