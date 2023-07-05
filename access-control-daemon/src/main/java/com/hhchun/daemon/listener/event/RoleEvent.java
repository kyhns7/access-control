package com.hhchun.daemon.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 角色事件
 */
@Getter
public class RoleEvent extends ApplicationEvent {

    // 删除
    public static final String REMOVE = "remove";

    // 修改
    public static final String MODIFY = "modify";

    /**
     * 角色id
     */
    private final Long roleId;
    /**
     * 角色事件类型
     */
    private final String type;

    public RoleEvent(Long roleId, String type, Object source) {
        super(source);
        this.roleId = roleId;
        this.type = type;
    }

    public RoleEvent(Long roleId, String type, Object source, Clock clock) {
        super(source, clock);
        this.roleId = roleId;
        this.type = type;
    }
}
