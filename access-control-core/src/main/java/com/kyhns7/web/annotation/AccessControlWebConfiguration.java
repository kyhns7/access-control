package com.kyhns7.web.annotation;


import com.kyhns7.annotation.AccessControlConfiguration;
import com.kyhns7.decision.AccessDecision;
import com.kyhns7.denied.AccessDenied;
import com.kyhns7.web.filter.AccessControlFilter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Filter;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
@ConditionalOnClass(DispatcherServlet.class)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, AccessControlConfiguration.class})
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
}
