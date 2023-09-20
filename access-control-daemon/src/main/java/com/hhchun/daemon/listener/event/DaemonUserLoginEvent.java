package com.hhchun.daemon.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 后台用户登录事件
 */
@Getter
public class DaemonUserLoginEvent extends ApplicationEvent {
    /**
     * 后台用户id
     */
    private final Long daemonUserId;
    /**
     * 登录token
     */
    private final String token;

    public DaemonUserLoginEvent(Long daemonUserId, String token, Object source) {
        super(source);
        this.daemonUserId = daemonUserId;
        this.token = token;
    }

    public DaemonUserLoginEvent(Long daemonUserId, String token, Object source, Clock clock) {
        super(source, clock);
        this.daemonUserId = daemonUserId;
        this.token = token;
    }
}
