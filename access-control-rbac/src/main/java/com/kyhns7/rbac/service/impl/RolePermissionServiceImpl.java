package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.common.base.Preconditions;
import com.kyhns7.rbac.dao.RolePermissionDao;
import com.kyhns7.rbac.entity.domain.RolePermissionEntity;
import com.kyhns7.rbac.entity.dto.RolePermissionDto;
import com.kyhns7.rbac.entity.vo.RolePermissionVo;
import com.kyhns7.rbac.service.RolePermissionService;
import com.kyhns7.rbac.service.event.RolePermissionEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionEntity> implements RolePermissionService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
        Preconditions.checkArgument(roleId != null, "roleId == null");
        List<RolePermissionEntity> list = list(new LambdaQueryWrapper<RolePermissionEntity>()
                .eq(RolePermissionEntity::getRoleId, roleId));
        return list.stream().map(RolePermissionEntity::getPermissionId).toList();
    }

    @Override
    public List<Long> getPermissionIdsByRoleIds(List<Long> roleIds) {
        Preconditions.checkArgument(roleIds != null, "roleIds == null");
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return list(new LambdaQueryWrapper<RolePermissionEntity>()
                .in(RolePermissionEntity::getRoleId, roleIds))
                .stream()
                .map(RolePermissionEntity::getPermissionId).toList();
    }

    @Transactional
    @Override
    public void saveRolePermission(RolePermissionDto rolePermissionDto) {
        Long roleId = rolePermissionDto.getRoleId();
        List<Long> permissionIds = rolePermissionDto.getPermissionIds();
        Preconditions.checkArgument(roleId != null, "请刷新后再说");
        remove(new LambdaQueryWrapper<RolePermissionEntity>()
                .eq(RolePermissionEntity::getRoleId, roleId));
        if (CollectionUtils.isEmpty(permissionIds)) {
            return;
        }
        List<RolePermissionEntity> rolePermissions = permissionIds.stream().map(permissionId -> {
            RolePermissionEntity rolePermission = new RolePermissionEntity();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            return rolePermission;
        }).toList();
        saveBatch(rolePermissions);

        publisher.publishEvent(new RolePermissionEvent(this, roleId));
    }

    @Override
    public List<Long> getRoleIdsByPermissionId(Long permissionId) {
        if (permissionId == null) {
            return List.of();
        }
        return list(new LambdaQueryWrapper<RolePermissionEntity>()
                .select(RolePermissionEntity::getRoleId)
                .eq(RolePermissionEntity::getPermissionId, permissionId))
                .stream().map(RolePermissionEntity::getRoleId).toList();
    }
}