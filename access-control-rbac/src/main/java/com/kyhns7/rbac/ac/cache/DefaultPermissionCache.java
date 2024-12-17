package com.kyhns7.rbac.ac.cache;

import com.kyhns7.provider.Permission;
import com.kyhns7.rbac.service.PermissionService;
import com.kyhns7.rbac.service.RolePermissionService;
import com.kyhns7.rbac.service.UserRoleService;
import com.kyhns7.rbac.service.event.PermissionEvent;
import com.kyhns7.rbac.service.event.RoleEvent;
import com.kyhns7.rbac.service.event.RolePermissionEvent;
import com.kyhns7.rbac.service.event.UserRoleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 默认实现,从数据库中加载数据,不做其他操作
 */
@Component
@ConditionalOnMissingBean(PermissionCache.class)
public class DefaultPermissionCache implements PermissionCache {

    @Autowired
    protected PermissionService permissionService;
    @Autowired
    protected RolePermissionService rolePermissionService;
    @Autowired
    protected UserRoleService userRoleService;

    @Override
    public List<ResourcePermission> loadAllResourcePermissions() {
        return permissionService.list().stream().map(p ->
                new ResourcePermission(p.getSymbol(), p.getResource())
        ).toList();
    }

    @Override
    public void refreshAllResourcePermissions() {
        // no
    }

    @Override
    public List<Permission> loadUserPermissions(Long userId) {
        if (userId == null) {
            return List.of();
        }
        return permissionService.getPermissionsByUserId(userId)
                .stream().map(p -> new Permission(p.getSymbol()))
                .toList();
    }

    @Override
    public void refreshUserPermissions(Long userId) {
        // no
    }


    @EventListener(value = PermissionEvent.class)
    public void listenerPermissionEventRefreshAllResourcePermissions(PermissionEvent event) {
        refreshAllResourcePermissions();
    }

    @EventListener(value = PermissionEvent.class)
    public void listenerPermissionEventRefreshUserPermissions(PermissionEvent event) {
        Long permissionId = event.getPermissionId();
        if (permissionId == null) {
            return;
        }
        List<Long> roleIds = rolePermissionService.getRoleIdsByPermissionId(permissionId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        List<Long> userIds = userRoleService.getUserIdsByRoleIds(roleIds);
        for (Long userId : userIds) {
            refreshUserPermissions(userId);
        }
    }

    @EventListener(value = RoleEvent.class)
    public void listenerRoleEventRefreshUserPermissions(RoleEvent event) {
        Long roleId = event.getRoleId();
        if (roleId == null) {
            return;
        }
        List<Long> userIds = userRoleService.getUserIdsByRoleId(roleId);
        for (Long userId : userIds) {
            refreshUserPermissions(userId);
        }
    }

    @EventListener(value = RolePermissionEvent.class)
    public void listenerRolePermissionEventRefreshUserPermissions(RolePermissionEvent event) {
        Long roleId = event.getRoleId();
        if (roleId == null) {
            return;
        }
        List<Long> userIds = userRoleService.getUserIdsByRoleId(roleId);
        for (Long userId : userIds) {
            refreshUserPermissions(userId);
        }
    }

    @EventListener(value = UserRoleEvent.class)
    public void listenerUserRoleEventRefreshUserPermissions(UserRoleEvent event) {
        Long userId = event.getUserId();
        if (userId == null) {
            return;
        }
        refreshUserPermissions(userId);
    }

}
