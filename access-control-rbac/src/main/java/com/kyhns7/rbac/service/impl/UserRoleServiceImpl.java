package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.common.base.Preconditions;
import com.kyhns7.rbac.dao.UserRoleDao;
import com.kyhns7.rbac.entity.domain.UserRoleEntity;
import com.kyhns7.rbac.entity.dto.UserRoleDto;
import com.kyhns7.rbac.service.UserRoleService;
import com.kyhns7.rbac.service.event.UserRoleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public void saveUserRole(UserRoleDto userRoleDto) {
        Long userId = userRoleDto.getUserId();
        List<Long> roleIds = userRoleDto.getRoleIds();
        Preconditions.checkArgument(userId != null, "userId == null");
        remove(new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getUserId, userId));
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        List<UserRoleEntity> userRoles = roleIds.stream().map(roleId -> {
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            return userRole;
        }).toList();
        saveBatch(userRoles);
        publisher.publishEvent(new UserRoleEvent(this, userId));
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        Preconditions.checkArgument(userId != null, "userId == null");
        return list(new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getUserId, userId))
                .stream()
                .map(UserRoleEntity::getRoleId).toList();
    }

    @Override
    public void bindRole(Long userId, Long roleId) {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        save(userRole);
        publisher.publishEvent(new UserRoleEvent(this, userId));
    }

    @Override
    public void unbindRole(Long userId, Long roleId) {
        remove(new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getUserId, userId)
                .eq(UserRoleEntity::getRoleId, roleId));
        publisher.publishEvent(new UserRoleEvent(this, userId));
    }

    @Override
    public List<Long> getUserIdsByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return List.of();
        }
        return list(new LambdaQueryWrapper<UserRoleEntity>()
                .select(UserRoleEntity::getUserId)
                .in(UserRoleEntity::getRoleId, roleIds))
                .stream().map(UserRoleEntity::getUserId).toList();
    }

    @Override
    public List<Long> getUserIdsByRoleId(Long roleId) {
        if (roleId == null) {
            return List.of();
        }
        return getUserIdsByRoleIds(List.of(roleId));
    }
}