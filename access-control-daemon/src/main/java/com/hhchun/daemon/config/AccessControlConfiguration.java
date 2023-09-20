package com.hhchun.daemon.config;

import com.hhchun.daemon.ac.authorize.DaemonAuthorizeFilter;
import com.hhchun.daemon.ac.denied.DaemonAccessDenied;
import com.hhchun.daemon.ac.provider.DaemonSubjectOwnedPermissionsProvider;
import com.hhchun.daemon.ac.provider.DaemonTargetAccessiblePermissionsProvider;
import com.hhchun.daemon.annotation.EnableAccessControl;
import com.hhchun.daemon.denied.AccessDenied;
import com.hhchun.daemon.provider.SubjectOwnedPermissionsProvider;
import com.hhchun.daemon.provider.TargetAccessiblePermissionsProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;

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

    @Bean
    public AccessDenied daemonAccessDenied(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        return new DaemonAccessDenied(resolver);
    }

    @Bean
    public FilterRegistrationBean<Filter> daemonAuthorizeFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        DaemonAuthorizeFilter daemonAuthorizeFilter = new DaemonAuthorizeFilter();
        registration.setFilter(daemonAuthorizeFilter);
        registration.addUrlPatterns("/*");
        registration.setName(DaemonAuthorizeFilter.class.getName() + "RegistrationBean");
        registration.setOrder(DaemonAuthorizeFilter.FILTER_REGISTRATION_ORDER);
        return registration;
    }
}
