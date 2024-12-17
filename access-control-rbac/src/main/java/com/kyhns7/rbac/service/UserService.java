package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.entity.domain.UserEntity;

/**
 * 用户
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
public interface UserService extends IService<UserEntity> {

    /**
     * @return 用户id
     */
    Long createUser();

    /**
     *
     * @param userId 用户id
     */
    boolean removeUser(Long userId);

}

