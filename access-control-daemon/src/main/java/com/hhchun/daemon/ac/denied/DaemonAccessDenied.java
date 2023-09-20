package com.hhchun.daemon.ac.denied;

import com.hhchun.daemon.denied.AccessDenied;
import com.hhchun.daemon.exception.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class DaemonAccessDenied implements AccessDenied {

    private final HandlerExceptionResolver resolver;

    public DaemonAccessDenied(HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void denied() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
        resolver.resolveException(request, response, null, new AccessDeniedException());
    }
}
