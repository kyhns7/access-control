package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.common.base.Preconditions;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.dao.RolePermissionDao;
import com.hhchun.daemon.entity.domain.PermissionEntity;
import com.hhchun.daemon.entity.domain.RoleEntity;
import com.hhchun.daemon.entity.domain.RolePermissionEntity;
import com.hhchun.daemon.entity.dto.RolePermissionDto;
import com.hhchun.daemon.entity.dto.RolePermissionSearchDto;
import com.hhchun.daemon.entity.vo.RolePermissionVo;
import com.hhchun.daemon.listener.event.RolePermissionEvent;
import com.hhchun.daemon.service.PermissionService;
import com.hhchun.daemon.service.RolePermissionService;
import com.hhchun.daemon.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionEntity> implements RolePermissionService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public RolePermissionEntity getRolePermissionById(Long rolePermissionId) {
        return getById(rolePermissionId);
    }

    @Override
    public RolePermissionEntity getRolePermission(Long roleId, Long permissionId) {
        return getOne(new LambdaQueryWrapper<RolePermissionEntity>()
                .eq(RolePermissionEntity::getRoleId, roleId)
                .eq(RolePermissionEntity::getPermissionId, permissionId), false);
    }

    @Override
    public void saveRolePermission(RolePermissionDto rolePermissionDto) {
        Long roleId = rolePermissionDto.getRoleId();
        RoleEntity role = roleService.getRoleById(roleId);
        Preconditions.checkCondition(role != null, "角色不存在");
        Long permissionId = rolePermissionDto.getPermissionId();
        PermissionEntity permission = permissionService.getPermissionById(permissionId);
        Preconditions.checkCondition(permission != null, "权限不存在");
        RolePermissionEntity rolePermission = getRolePermission(roleId, permissionId);
        Preconditions.checkCondition(rolePermission == null, "角色与权限已关联过");
        rolePermission = new RolePermissionEntity();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        save(rolePermission);

        // 发布新增角色与权限关联事件
        RolePermissionEvent rolePermissionEvent = new RolePermissionEvent(rolePermission.getId(),
                RolePermissionEvent.NEWLY_ADDED, this);
        publisher.publishEvent(rolePermissionEvent);
    }

    @Override
    public void modifyRolePermission(RolePermissionDto rolePermissionDto) {
        Long rolePermissionId = rolePermissionDto.getId();
        RolePermissionEntity rolePermission = getRolePermissionById(rolePermissionId);
        Preconditions.checkCondition(rolePermission != null, "角色与权限关联不存在");
        Long roleId = rolePermissionDto.getRoleId();
        if (roleId != null) {
            RoleEntity role = roleService.getRoleById(roleId);
            Preconditions.checkCondition(role != null, "角色不存在");
        }
        Long permissionId = rolePermission.getPermissionId();
        if (permissionId != null) {
            PermissionEntity permission = permissionService.getPermissionById(permissionId);
            Preconditions.checkCondition(permission != null, "权限不存在");
        }
        Preconditions.checkCondition(getRolePermission(roleId, permissionId) == null, "角色与权限已关联过");

        BeanUtils.copyProperties(rolePermissionDto, rolePermission);
        updateById(rolePermission);

        // 发布修改角色与权限关联事件
        RolePermissionEvent rolePermissionEvent = new RolePermissionEvent(rolePermissionId, RolePermissionEvent.MODIFY, this);
        publisher.publishEvent(rolePermissionEvent);
    }

    @Override
    public void removeRolePermission(Long rolePermissionId) {
        RolePermissionEntity rolePermission = getRolePermissionById(rolePermissionId);
        Preconditions.checkCondition(rolePermission != null, "角色与权限关联不存在");
        removeById(rolePermissionId);

        // 发布删除角色与权限关联事件
        RolePermissionEvent rolePermissionEvent = new RolePermissionEvent(rolePermissionId, RolePermissionEvent.REMOVE, this);
        publisher.publishEvent(rolePermissionEvent);
    }

    @Override
    public PageResult<RolePermissionVo> getPermissionList(RolePermissionSearchDto searchDto) {
        Long roleId = searchDto.getRoleId();
        Preconditions.checkArgument(roleId != null, "角色id不能为空");
        return getRolePermissionList(searchDto);
    }

    @Override
    public PageResult<RolePermissionVo> getRolePermissionList(RolePermissionSearchDto searchDto) {
        IPage<RolePermissionVo> page = rolePermissionDao.getRolePermissionList(searchDto.getPage(RolePermissionEntity.class), searchDto);
        return PageResult.convert(page, page.getRecords());
    }

    @Override
    public PageResult<RolePermissionVo> getRoleList(RolePermissionSearchDto searchDto) {
        Long permissionId = searchDto.getPermissionId();
        Preconditions.checkArgument(permissionId != null, "权限id不能为空");
        return getRolePermissionList(searchDto);
    }
}