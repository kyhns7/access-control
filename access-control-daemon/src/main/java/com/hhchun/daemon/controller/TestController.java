package com.hhchun.daemon.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedissonClient client;

    @GetMapping("/test")
    public String test() {
        RLock lock = client.getLock("test1");
        lock.lock();
        System.out.println(client);
        lock.unlock();
        return "test";
    }
}
