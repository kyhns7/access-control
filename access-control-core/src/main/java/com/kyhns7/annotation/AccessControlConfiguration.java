package com.kyhns7.annotation;


import com.kyhns7.decision.AccessDecision;
import com.kyhns7.decision.AffirmativeAccessDecision;
import com.kyhns7.denied.AccessDenied;
import com.kyhns7.provider.DelegatingResourceAccessiblePermissionsProvider;
import com.kyhns7.provider.DelegatingVisitorOwnedPermissionsProvider;
import com.kyhns7.provider.ResourceAccessPermissionsProvider;
import com.kyhns7.provider.VisitorOwnedPermissionsProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import java.util.Set;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
public class AccessControlConfiguration {

    @Bean
    @ConditionalOnMissingBean(AccessDecision.class)
    public AccessDecision accessDecision(@Qualifier(DelegatingVisitorOwnedPermissionsProvider.BEAN_NAME) VisitorOwnedPermissionsProvider vp,
                                         @Qualifier(DelegatingResourceAccessiblePermissionsProvider.BEAN_NAME) ResourceAccessPermissionsProvider rp) {
        return new AffirmativeAccessDecision(vp, rp);
    }

    @Bean
    @ConditionalOnMissingBean(AccessDenied.class)
    public AccessDenied accessDenied() {
        return resource -> {
            // Do not handle
        };
    }

    @Bean(name = DelegatingVisitorOwnedPermissionsProvider.BEAN_NAME)
    @Lazy
    public VisitorOwnedPermissionsProvider delegatingVisitorOwnedPermissionsProvider(Set<VisitorOwnedPermissionsProvider> vps) {
        return new DelegatingVisitorOwnedPermissionsProvider(vps);
    }

    @Bean(name = DelegatingResourceAccessiblePermissionsProvider.BEAN_NAME)
    @Lazy
    public ResourceAccessPermissionsProvider delegatingTargetAccessiblePermissionsProvider(Set<ResourceAccessPermissionsProvider> rps) {
        return new DelegatingResourceAccessiblePermissionsProvider(rps);
    }
}
