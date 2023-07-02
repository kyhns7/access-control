package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.dao.RoleDao;
import org.springframework.stereotype.Service;

import com.hhchun.daemon.entity.domain.RoleEntity;
import com.hhchun.daemon.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

}