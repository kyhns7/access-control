package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.dao.UserRoleDao;
import com.kyhns7.rbac.entity.domain.UserRoleEntity;
import com.kyhns7.rbac.service.UserRoleService;
import org.springframework.stereotype.Service;


@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

}