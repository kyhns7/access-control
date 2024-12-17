package com.kyhns7.rbac.ac.provider;

import com.kyhns7.provider.Permission;
import com.kyhns7.provider.ResourceAccessPermissionsProvider;
import com.kyhns7.rbac.ac.cache.PermissionCache;
import com.kyhns7.rbac.ac.cache.ResourcePermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class RbacResourceAccessPermissionsProvider implements ResourceAccessPermissionsProvider {
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Autowired
    private PermissionCache cache;

    @Override
    public List<Permission> provide() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String path = request.getRequestURI();
        List<ResourcePermission> rps = cache.loadAllResourcePermissions();
        rps = Optional.ofNullable(rps).orElse(List.of());
        return rps.stream().filter(rp -> matcher.match(rp.resource(), path))
                .map(rp -> new Permission(rp.symbol()))
                .toList();

    }
}
