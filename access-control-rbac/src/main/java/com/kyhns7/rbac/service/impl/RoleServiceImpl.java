package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.dao.RoleDao;
import org.springframework.stereotype.Service;

import com.kyhns7.rbac.entity.domain.RoleEntity;
import com.kyhns7.rbac.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

}