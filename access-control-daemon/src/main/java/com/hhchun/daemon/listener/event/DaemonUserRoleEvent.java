package com.hhchun.daemon.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 权限事件
 */
@Getter
public class DaemonUserRoleEvent extends ApplicationEvent {

    // 新增
    public static final String NEWLY_ADDED = "newly-added";

    // 修改
    public static final String MODIFY = "modify";

    // 删除
    public static final String REMOVE = "remove";

    /**
     * 权限id
     */
    private final Long daemonUserRole;
    /**
     * 权限事件类型
     */
    private final String type;

    public DaemonUserRoleEvent(Long daemonUserRole, String type, Object source) {
        super(source);
        this.daemonUserRole = daemonUserRole;
        this.type = type;
    }

    public DaemonUserRoleEvent(Long daemonUserRole, String type, Object source, Clock clock) {
        super(source, clock);
        this.daemonUserRole = daemonUserRole;
        this.type = type;
    }
}
