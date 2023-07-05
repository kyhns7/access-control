package com.hhchun.daemon.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 权限事件
 */
@Getter
public class RolePermissionEvent extends ApplicationEvent {

    // 新增
    public static final String NEWLY_ADDED = "newly-added";

    // 修改
    public static final String MODIFY = "modify";

    // 删除
    public static final String REMOVE = "remove";

    /**
     * 权限id
     */
    private final Long rolePermissionId;
    /**
     * 权限事件类型
     */
    private final String type;

    public RolePermissionEvent(Long rolePermissionId, String type, Object source) {
        super(source);
        this.rolePermissionId = rolePermissionId;
        this.type = type;
    }

    public RolePermissionEvent(Long rolePermissionId, String type, Object source, Clock clock) {
        super(source, clock);
        this.rolePermissionId = rolePermissionId;
        this.type = type;
    }
}
