package com.hhchun.daemon.controller;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedissonClient redisson;

    @GetMapping("/test")
    public String test() {
        RMap<String, Integer> map = redisson.getMap("test");
        map.put("t1", 1);
        return "test";
    }
}
