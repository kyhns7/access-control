package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.dao.DaemonUserMtmRoleDao;
import org.springframework.stereotype.Service;

import com.hhchun.daemon.entity.domain.DaemonUserMtmRoleEntity;
import com.hhchun.daemon.service.DaemonUserMtmRoleService;


@Service("daemonUserMtmRoleService")
public class DaemonUserMtmRoleServiceImpl extends ServiceImpl<DaemonUserMtmRoleDao, DaemonUserMtmRoleEntity> implements DaemonUserMtmRoleService {

}