package com.hhchun.daemon.annotation;


import com.hhchun.daemon.decision.AccessDecision;
import com.hhchun.daemon.decision.AffirmativeAccessDecision;
import com.hhchun.daemon.denied.AccessDenied;
import com.hhchun.daemon.filter.AccessControlFilter;
import com.hhchun.daemon.provider.DelegatingSubjectOwnedPermissionsProvider;
import com.hhchun.daemon.provider.DelegatingTargetAccessiblePermissionsProvider;
import com.hhchun.daemon.provider.SubjectOwnedPermissionsProvider;
import com.hhchun.daemon.provider.TargetAccessiblePermissionsProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Filter;
import java.util.Set;


/**
 * 适用于springmvc环境
 */
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
@ConditionalOnClass(DispatcherServlet.class)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class})
public class AccessControlWebConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> accessControlFilterRegistration(AccessDecision accessDecision, AccessDenied accessDenied) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        AccessControlFilter accessControlFilter = new AccessControlFilter(accessDecision, accessDenied);
        registration.setFilter(accessControlFilter);
        registration.addUrlPatterns("/*");
        registration.setName(AccessControlFilter.class.getName());
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean(AccessDecision.class)
    public AccessDecision accessDecision(@Qualifier(DelegatingSubjectOwnedPermissionsProvider.BEAN_NAME) SubjectOwnedPermissionsProvider sop,
                                         @Qualifier(DelegatingTargetAccessiblePermissionsProvider.BEAN_NAME) TargetAccessiblePermissionsProvider tap) {
        return new AffirmativeAccessDecision(sop, tap);
    }

    @Bean
    @ConditionalOnMissingBean(AccessDenied.class)
    public AccessDenied accessDenied() {
        return () -> {
            // Do not handle
        };
    }

    @Bean(name = DelegatingSubjectOwnedPermissionsProvider.BEAN_NAME)
    @Lazy
    public SubjectOwnedPermissionsProvider delegatingSubjectOwnedPermissionsProvider(Set<SubjectOwnedPermissionsProvider> sops) {
        return new DelegatingSubjectOwnedPermissionsProvider(sops);
    }

    @Bean(name = DelegatingTargetAccessiblePermissionsProvider.BEAN_NAME)
    @Lazy
    public TargetAccessiblePermissionsProvider delegatingTargetAccessiblePermissionsProvider(Set<TargetAccessiblePermissionsProvider> taps) {
        return new DelegatingTargetAccessiblePermissionsProvider(taps);
    }
}
