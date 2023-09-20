package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhchun.daemon.entity.domain.DaemonUserEntity;
import com.hhchun.daemon.entity.dto.DaemonUserLoginDto;

/**
 * 后台用户
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface DaemonUserService extends IService<DaemonUserEntity> {

    DaemonUserEntity getDaemonUserById(Long daemonUserId);

    String daemonUserLogin(DaemonUserLoginDto loginDto);

    DaemonUserEntity getDaemonUserByOpenid(String openid);

    String daemonUserLoginTest(DaemonUserLoginDto loginDto);
}

