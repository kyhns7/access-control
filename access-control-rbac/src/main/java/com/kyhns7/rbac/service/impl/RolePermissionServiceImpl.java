package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.dao.RolePermissionDao;
import com.kyhns7.rbac.entity.domain.RolePermissionEntity;
import com.kyhns7.rbac.service.RolePermissionService;
import org.springframework.stereotype.Service;


@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionEntity> implements RolePermissionService {

}