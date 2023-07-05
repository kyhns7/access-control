package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.entity.domain.PermissionCategoryEntity;
import com.hhchun.daemon.entity.dto.PermissionCategoryDto;
import com.hhchun.daemon.entity.dto.PermissionCategorySearchDto;
import com.hhchun.daemon.entity.vo.PermissionCategoryVo;


/**
 * 权限类别/分类
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface PermissionCategoryService extends IService<PermissionCategoryEntity> {

    void savePermissionCategory(PermissionCategoryDto permissionCategoryDto);

    void modifyPermissionCategory(PermissionCategoryDto permissionCategoryDto);

    void removePermissionCategory(Long permissionCategoryId);

    PageResult<PermissionCategoryVo> getPermissionCategoryList(PermissionCategorySearchDto searchDto);

    PermissionCategoryEntity getPermissionCategoryById(Long permissionCategoryId);
}

