package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import  com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.entity.domain.DaemonUserMtmRoleEntity;

import java.util.Map;

/**
 * 后台用户关联角色(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface DaemonUserMtmRoleService extends IService<DaemonUserMtmRoleEntity> {

}

