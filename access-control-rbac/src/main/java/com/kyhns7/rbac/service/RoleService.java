package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.entity.domain.RoleEntity;
import com.kyhns7.rbac.entity.dto.RoleDto;
import com.kyhns7.rbac.entity.dto.RoleSearchDto;
import com.kyhns7.rbac.entity.vo.RoleVo;

import java.util.List;

/**
 * 角色
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
public interface RoleService extends IService<RoleEntity> {

    PageResult<RoleVo> getRoleList(RoleSearchDto searchDto);

    void saveRole(RoleDto roleDto);

    RoleEntity getRoleBySymbol(String symbol);

    void modifyRole(RoleDto roleDto);

    void removeRole(Long id);

    List<RoleEntity> getRolesByUserId(Long userId);
}

