package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.dao.UserDao;
import com.kyhns7.rbac.entity.domain.UserEntity;
import com.kyhns7.rbac.service.UserService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public Long createUser() {
        UserEntity user = new UserEntity();
        save(user);
        return user.getId();
    }

    @Override
    public boolean removeUser(Long userId) {
        return removeById(userId);
    }
}