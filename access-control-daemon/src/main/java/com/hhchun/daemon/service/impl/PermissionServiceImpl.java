package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.dao.PermissionDao;
import org.springframework.stereotype.Service;
import com.hhchun.daemon.entity.domain.PermissionEntity;
import com.hhchun.daemon.service.PermissionService;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {

}