package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.entity.domain.RoleEntity;
import com.hhchun.daemon.entity.dto.RoleDto;
import com.hhchun.daemon.entity.dto.RoleSearchDto;
import com.hhchun.daemon.entity.vo.RoleVo;

/**
 * 角色
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface RoleService extends IService<RoleEntity> {
    RoleEntity getRoleBySymbol(String symbol);

    RoleEntity getRoleById(Long roleId);

    void saveRole(RoleDto roleDto);

    void modifyRole(RoleDto roleDto);

    void removeRole(Long roleId);

    PageResult<RoleVo> getRoleList(RoleSearchDto searchDto);
}

