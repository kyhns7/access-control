package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.dao.RoleMtmPermissionDao;
import org.springframework.stereotype.Service;

import com.hhchun.daemon.entity.domain.RoleMtmPermissionEntity;
import com.hhchun.daemon.service.RoleMtmPermissionService;


@Service("roleMtmPermissionService")
public class RoleMtmPermissionServiceImpl extends ServiceImpl<RoleMtmPermissionDao, RoleMtmPermissionEntity> implements RoleMtmPermissionService {

}