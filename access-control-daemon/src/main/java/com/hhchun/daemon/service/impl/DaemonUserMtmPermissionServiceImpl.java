package com.hhchun.daemon.service.impl;

import com.hhchun.daemon.dao.DaemonUserMtmPermissionDao;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hhchun.daemon.entity.domain.DaemonUserMtmPermissionEntity;
import com.hhchun.daemon.service.DaemonUserMtmPermissionService;


@Service("daemonUserMtmPermissionService")
public class DaemonUserMtmPermissionServiceImpl extends ServiceImpl<DaemonUserMtmPermissionDao, DaemonUserMtmPermissionEntity> implements DaemonUserMtmPermissionService {


}