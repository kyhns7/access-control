package com.hhchun.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hhchun.daemon.entity.domain.DaemonUserEntity;
import com.hhchun.daemon.service.DaemonUserService;
import com.hhchun.daemon.common.utils.R;


/**
 * 后台用户
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemonuser")
public class DaemonUserController {
    @Autowired
    private DaemonUserService daemonUserService;


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R<?> save(@RequestBody DaemonUserEntity daemonUser) {
        daemonUserService.save(daemonUser);

        return R.success();
    }

}
