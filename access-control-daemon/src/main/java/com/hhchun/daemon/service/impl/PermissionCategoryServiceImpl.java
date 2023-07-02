package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.dao.PermissionCategoryDao;
import org.springframework.stereotype.Service;
import com.hhchun.daemon.entity.domain.PermissionCategoryEntity;
import com.hhchun.daemon.service.PermissionCategoryService;


@Service("permissionCategoryService")
public class PermissionCategoryServiceImpl extends ServiceImpl<PermissionCategoryDao, PermissionCategoryEntity> implements PermissionCategoryService {

}