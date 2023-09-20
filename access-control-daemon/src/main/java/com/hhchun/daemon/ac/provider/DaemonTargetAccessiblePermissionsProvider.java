package com.hhchun.daemon.ac.provider;

import com.google.common.collect.Lists;
import com.hhchun.daemon.provider.Permission;
import com.hhchun.daemon.provider.TargetAccessiblePermissionsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Slf4j
public class DaemonTargetAccessiblePermissionsProvider implements TargetAccessiblePermissionsProvider {

    @Override
    public List<Permission> provide() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.info("Not a web environment!");
            return Collections.emptyList();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        return Lists.newArrayList(new Permission("develop"));
    }
}
