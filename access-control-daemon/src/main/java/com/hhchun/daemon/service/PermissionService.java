package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.entity.domain.PermissionEntity;
import com.hhchun.daemon.entity.dto.PermissionDto;
import com.hhchun.daemon.entity.dto.PermissionSearchDto;
import com.hhchun.daemon.entity.vo.PermissionVo;


/**
 * 权限
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface PermissionService extends IService<PermissionEntity> {

    void savePermission(PermissionDto permissionDto);

    void modifyPermission(PermissionDto permissionDto);

    void removePermission(Long permissionId);

    PageResult<PermissionVo> getPermissionList(PermissionSearchDto searchDto);

    PermissionEntity getPermissionBySymbol(String symbol);

    PermissionEntity getPermissionById(Long permissionId);
}

