package com.kyhns7.rbac.ac.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kyhns7.provider.Permission;
import com.kyhns7.rbac.service.event.PermissionEvent;
import com.kyhns7.rbac.service.event.RoleEvent;
import com.kyhns7.rbac.service.event.RolePermissionEvent;
import com.kyhns7.rbac.service.event.UserRoleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
public class LocalPermissionCache extends DefaultPermissionCache {
    private final String ALL_RESOURCE_PERMISSIONS_CACHE_KEY = "ALL_RESOURCE_PERMISSIONS_CACHE_KEY";
    private final Cache<String, List<ResourcePermission>> ALL_RESOURCE_PERMISSIONS_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(6, TimeUnit.HOURS)
            .build();

    private final static String USER_PERMISSIONS_CACHE_LOCK_PREFIX = "USER_PERMISSIONS_CACHE_LOCK_";
    private final Cache<Long, List<Permission>> USER_PERMISSIONS_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    private final Timer delayDeleteCacheTimer = new Timer();

    private final static String PERMISSION_EVENT_CHANNEL = "PERMISSION_EVENT";
    private final static String ROLE_EVENT_CHANNEL = "ROLE_EVENT";
    private final static String ROLE_PERMISSION_EVENT_CHANNEL = "ROLE_PERMISSION_EVENT";
    private final static String USER_ROLE_EVENT_CHANNEL = "USER_ROLE_EVENT";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<ResourcePermission> loadAllResourcePermissions() {
        List<ResourcePermission> rps = ALL_RESOURCE_PERMISSIONS_CACHE.getIfPresent(ALL_RESOURCE_PERMISSIONS_CACHE_KEY);
        if (!CollectionUtils.isEmpty(rps)) {
            return rps;
        }
        synchronized (ALL_RESOURCE_PERMISSIONS_CACHE_KEY) {
            rps = ALL_RESOURCE_PERMISSIONS_CACHE.getIfPresent(ALL_RESOURCE_PERMISSIONS_CACHE_KEY);
            if (!CollectionUtils.isEmpty(rps)) {
                return rps;
            }
            rps = super.loadAllResourcePermissions();
            rps = Optional.ofNullable(rps).orElse(List.of());
            ALL_RESOURCE_PERMISSIONS_CACHE.put(ALL_RESOURCE_PERMISSIONS_CACHE_KEY, rps);
            return rps;
        }
    }

    @Override
    public void refreshAllResourcePermissions() {
        ALL_RESOURCE_PERMISSIONS_CACHE.put(ALL_RESOURCE_PERMISSIONS_CACHE_KEY, List.of());
        delayDeleteCacheTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ALL_RESOURCE_PERMISSIONS_CACHE.put(ALL_RESOURCE_PERMISSIONS_CACHE_KEY, List.of());
            }
        }, TimeUnit.SECONDS.toMillis(5));
    }

    @Override
    public List<Permission> loadUserPermissions(Long userId) {
        if (userId == null) {
            return List.of();
        }
        List<Permission> ps = USER_PERMISSIONS_CACHE.getIfPresent(userId);
        if (!CollectionUtils.isEmpty(ps)) {
            return ps;
        }
        synchronized ((USER_PERMISSIONS_CACHE_LOCK_PREFIX + userId).intern()) {
            ps = USER_PERMISSIONS_CACHE.getIfPresent(userId);
            if (!CollectionUtils.isEmpty(ps)) {
                return ps;
            }
            ps = super.loadUserPermissions(userId);
            ps = Optional.ofNullable(ps).orElse(List.of());
            USER_PERMISSIONS_CACHE.put(userId, ps);
            return ps;
        }
    }

    @Override
    public void refreshUserPermissions(Long userId) {
        if (userId == null) {
            return;
        }
        USER_PERMISSIONS_CACHE.put(userId, List.of());
        delayDeleteCacheTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                USER_PERMISSIONS_CACHE.put(userId, List.of());
            }
        }, TimeUnit.SECONDS.toMillis(5));
    }

    @EventListener(value = PermissionEvent.class)
    public void listenerPermissionEventRefreshAllResourcePermissions(PermissionEvent event) {
        super.listenerPermissionEventRefreshAllResourcePermissions(event);
        redisTemplate.convertAndSend(PERMISSION_EVENT_CHANNEL, event.getPermissionId());
    }

    @EventListener(value = PermissionEvent.class)
    public void listenerPermissionEventRefreshUserPermissions(PermissionEvent event) {
        super.listenerPermissionEventRefreshUserPermissions(event);
        redisTemplate.convertAndSend(PERMISSION_EVENT_CHANNEL, event.getPermissionId());
    }

    @EventListener(value = RoleEvent.class)
    public void listenerRoleEventRefreshUserPermissions(RoleEvent event) {
        super.listenerRoleEventRefreshUserPermissions(event);
        redisTemplate.convertAndSend(ROLE_EVENT_CHANNEL, event.getRoleId());
    }

    @EventListener(value = RolePermissionEvent.class)
    public void listenerRolePermissionEventRefreshUserPermissions(RolePermissionEvent event) {
        super.listenerRolePermissionEventRefreshUserPermissions(event);
        redisTemplate.convertAndSend(ROLE_PERMISSION_EVENT_CHANNEL, event.getRoleId());
    }

    @EventListener(value = UserRoleEvent.class)
    public void listenerUserRoleEventRefreshUserPermissions(UserRoleEvent event) {
        super.listenerUserRoleEventRefreshUserPermissions(event);
        redisTemplate.convertAndSend(USER_ROLE_EVENT_CHANNEL, event.getUserId());
    }


    @Bean
    public MessageListener listenerPermissionEventRefreshAllResourcePermissions() {
        return (message, pattern) -> {
            Long permissionId = Long.getLong(new String(message.getBody()));
            if (permissionId == null) {
                return;
            }
            refreshAllResourcePermissions();
        };
    }

    @Bean
    public MessageListener listenerPermissionEventRefreshUserPermissions() {
        return (message, pattern) -> {
            Long permissionId = Long.getLong(new String(message.getBody()));
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
        };
    }

    @Bean
    public MessageListener listenerRoleEventRefreshUserPermissions() {
        return (message, pattern) -> {
            Long roleId = Long.getLong(new String(message.getBody()));
            if (roleId == null) {
                return;
            }
            List<Long> userIds = userRoleService.getUserIdsByRoleId(roleId);
            for (Long userId : userIds) {
                refreshUserPermissions(userId);
            }
        };
    }

    @Bean
    public MessageListener listenerRolePermissionEventRefreshUserPermissions() {
        return (message, pattern) -> {
            Long roleId = Long.getLong(new String(message.getBody()));
            if (roleId == null) {
                return;
            }
            List<Long> userIds = userRoleService.getUserIdsByRoleId(roleId);
            for (Long userId : userIds) {
                refreshUserPermissions(userId);
            }
        };
    }

    @Bean
    public MessageListener listenerUserRoleEventRefreshUserPermissions() {
        return (message, pattern) -> {
            Long userId = Long.getLong(new String(message.getBody()));
            if (userId == null) {
                return;
            }
            refreshUserPermissions(userId);
        };
    }

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(listenerPermissionEventRefreshAllResourcePermissions(),
                new ChannelTopic(PERMISSION_EVENT_CHANNEL));
        container.addMessageListener(listenerPermissionEventRefreshUserPermissions(),
                new ChannelTopic(PERMISSION_EVENT_CHANNEL));
        container.addMessageListener(listenerRoleEventRefreshUserPermissions(),
                new ChannelTopic(ROLE_EVENT_CHANNEL));
        container.addMessageListener(listenerRolePermissionEventRefreshUserPermissions(),
                new ChannelTopic(ROLE_PERMISSION_EVENT_CHANNEL));
        container.addMessageListener(listenerUserRoleEventRefreshUserPermissions(),
                new ChannelTopic(USER_ROLE_EVENT_CHANNEL));
        return container;
    }
}
