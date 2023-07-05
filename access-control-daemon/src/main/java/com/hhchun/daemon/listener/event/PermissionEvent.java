package com.hhchun.daemon.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 权限事件
 */
@Getter
public class PermissionEvent extends ApplicationEvent {

    // 修改
    public static final String MODIFY = "modify";

    // 删除
    public static final String REMOVE = "remove";

    /**
     * 权限id
     */
    private final Long permissionId;
    /**
     * 权限事件类型
     */
    private final String type;

    public PermissionEvent(Long permissionId, String type, Object source) {
        super(source);
        this.permissionId = permissionId;
        this.type = type;
    }

    public PermissionEvent(Long permissionId, String type, Object source, Clock clock) {
        super(source, clock);
        this.permissionId = permissionId;
        this.type = type;
    }
}
