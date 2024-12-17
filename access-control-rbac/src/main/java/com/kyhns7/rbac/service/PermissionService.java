package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.entity.domain.PermissionEntity;
import com.kyhns7.rbac.entity.dto.PermissionDto;
import com.kyhns7.rbac.entity.dto.PermissionSearchDto;
import com.kyhns7.rbac.entity.vo.PermissionVo;

import java.util.List;


/**
 * 权限
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
public interface PermissionService extends IService<PermissionEntity> {

    void savePermission(PermissionDto permissionDto);

    void modifyPermission(PermissionDto permissionDto);

    void removePermission(Long id);

    PageResult<PermissionVo> getPermissionList(PermissionSearchDto searchDto);

    PermissionEntity getPermissionBySymbol(String symbol);

    List<PermissionEntity> getPermissionByIds(List<Long> ids);

    List<PermissionEntity> getPermissionsByUserId(Long userId);
}

