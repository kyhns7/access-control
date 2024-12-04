package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.dao.PermissionDao;
import org.springframework.stereotype.Service;
import com.kyhns7.rbac.entity.domain.PermissionEntity;
import com.kyhns7.rbac.service.PermissionService;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {

}