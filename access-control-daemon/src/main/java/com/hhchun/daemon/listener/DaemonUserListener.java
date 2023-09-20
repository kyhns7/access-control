package com.hhchun.daemon.listener;

import com.hhchun.daemon.listener.event.DaemonUserLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DaemonUserListener {

    @Autowired
    private RedissonClient redisson;

    @EventListener(value = {DaemonUserLoginEvent.class})
    public void handlerDaemonUserLoginEvent(DaemonUserLoginEvent event) {
        Long daemonUserId = event.getDaemonUserId();
        String token = event.getToken();
    }

}
