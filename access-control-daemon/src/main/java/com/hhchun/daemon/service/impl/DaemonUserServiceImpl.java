package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.dao.DaemonUserDao;
import org.springframework.stereotype.Service;



import com.hhchun.daemon.entity.domain.DaemonUserEntity;
import com.hhchun.daemon.service.DaemonUserService;


@Service("daemonUserService")
public class DaemonUserServiceImpl extends ServiceImpl<DaemonUserDao, DaemonUserEntity> implements DaemonUserService {

}