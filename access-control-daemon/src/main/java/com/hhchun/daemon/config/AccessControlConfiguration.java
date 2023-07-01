package com.hhchun.daemon.config;

import com.hhchun.daemon.annotation.EnableAccessControl;
import com.hhchun.daemon.provider.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAccessControl
public class AccessControlConfiguration {

    @Bean
    public SubjectOwnedPermissionsProvider daemonSubjectOwnedPermissionsProvider() {
        return new DaemonSubjectOwnedPermissionsProvider();
    }

    @Bean
    public TargetAccessiblePermissionsProvider daemonTargetAccessiblePermissionsProvider() {
        return new DaemonTargetAccessiblePermissionsProvider();
    }
}
