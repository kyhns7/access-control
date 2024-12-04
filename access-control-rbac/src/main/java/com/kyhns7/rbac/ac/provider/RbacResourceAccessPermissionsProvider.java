package com.kyhns7.rbac.ac.provider;

import com.kyhns7.provider.Permission;
import com.kyhns7.provider.ResourceAccessPermissionsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class RbacResourceAccessPermissionsProvider implements ResourceAccessPermissionsProvider {

    @Override
    public List<Permission> provide() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info("request：{}", request);
        return Arrays.asList(new Permission("wx"), new Permission("member"));
    }
}
