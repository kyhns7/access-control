package com.kyhns7.rbac.ac.provider;

import com.kyhns7.provider.Permission;
import com.kyhns7.provider.VisitorOwnedPermissionsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class RbacVisitorOwnedPermissionsProvider implements VisitorOwnedPermissionsProvider {

    @Override
    public List<Permission> provide() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return Arrays.asList(new Permission("admin"), new Permission("worker"));
    }
}
