package com.hhchun.daemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.entity.domain.DaemonUserRoleEntity;
import com.hhchun.daemon.entity.dto.DaemonUserRoleDto;
import com.hhchun.daemon.entity.dto.DaemonUserRoleSearchDto;
import com.hhchun.daemon.entity.vo.DaemonUserRoleVo;

/**
 * 后台用户关联角色(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
public interface DaemonUserRoleService extends IService<DaemonUserRoleEntity> {
    DaemonUserRoleEntity getDaemonUserRole(Long daemonUserId, Long roleId);

    DaemonUserRoleEntity getDaemonUserRoleById(Long daemonUserRoleId);

    void saveDaemonUserRole(DaemonUserRoleDto daemonUserRoleDto);

    void modifyDaemonUserRole(DaemonUserRoleDto daemonUserRoleDto);

    void removeDaemonUserRole(Long daemonUserRoleId);

    PageResult<DaemonUserRoleVo> getDaemonUserList(DaemonUserRoleSearchDto searchDto);

    PageResult<DaemonUserRoleVo> getRoleList(DaemonUserRoleSearchDto searchDto);

    PageResult<DaemonUserRoleVo> getDaemonUserRoleList(DaemonUserRoleSearchDto searchDto);
}

