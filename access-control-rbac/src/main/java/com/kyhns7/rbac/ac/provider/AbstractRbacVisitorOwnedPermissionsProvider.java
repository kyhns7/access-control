package com.kyhns7.rbac.ac.provider;

import com.kyhns7.provider.Permission;
import com.kyhns7.provider.VisitorOwnedPermissionsProvider;
import com.kyhns7.rbac.ac.cache.PermissionCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class AbstractRbacVisitorOwnedPermissionsProvider implements VisitorOwnedPermissionsProvider {
    @Autowired
    private PermissionCache cache;

    @Override
    public List<Permission> provide() {
        Long userId = getUserId();
        List<Permission> permissions = cache.loadUserPermissions(userId);
        return Optional.ofNullable(permissions).orElse(List.of());
    }

    Long getUserId() {
        return -1L;
    }
}
