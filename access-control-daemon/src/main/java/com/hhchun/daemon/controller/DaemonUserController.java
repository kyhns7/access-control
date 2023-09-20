package com.hhchun.daemon.controller;

import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.entity.dto.DaemonUserLoginDto;
import com.hhchun.daemon.service.DaemonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 后台用户
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemon-user")
public class DaemonUserController {
    @Autowired
    private DaemonUserService daemonUserService;

    /**
     * 注册/登录
     */
    @PostMapping("/login")
    public R<String> daemonUserLogin(@Validated @RequestBody DaemonUserLoginDto loginDto) {
        String token = daemonUserService.daemonUserLogin(loginDto);
        return R.success(token);
    }

    /**
     * 注册/登录
     * tips: 测试
     */
    @PostMapping("/login-test")
    public R<String> daemonUserLoginTest(@Validated @RequestBody DaemonUserLoginDto loginDto) {
        String token = daemonUserService.daemonUserLoginTest(loginDto);
        return R.success(token);
    }

}
