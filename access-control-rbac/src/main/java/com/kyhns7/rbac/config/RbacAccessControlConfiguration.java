package com.kyhns7.rbac.config;

import com.kyhns7.web.annotation.EnableWebAccessControl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableWebAccessControl
@ComponentScan({"com.kyhns7.rbac"})
public class RbacAccessControlConfiguration {

}
