package com.kyhns7.rbac.controller;

import com.kyhns7.rbac.entity.domain.UserEntity;
import com.kyhns7.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kyhns7.rbac.common.utils.R;


/**
 * 后台用户
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R<?> save(@RequestBody UserEntity daemonUser) {
        userService.save(daemonUser);

        return R.success();
    }

}
