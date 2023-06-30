package com.hhchun.access.filter;

import com.hhchun.access.decision.AccessDecision;
import com.hhchun.access.denied.AccessDenied;

import javax.servlet.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 权限控制过滤器
 */
public abstract class AccessControlFilter implements Filter {
    /**
     * 访问决策器
     */
    private final AccessDecision accessDecision;
    /**
     * 访问拒绝器
     */
    private final AccessDenied accessDenied;

    public AccessControlFilter(@NotNull final AccessDecision accessDecision,
                               @NotNull final AccessDenied accessDenied) {
        if (accessDecision == null) {
            throw new IllegalArgumentException("accessDecision == null!");
        }
        if (accessDenied == null) {
            throw new IllegalArgumentException("accessDenied == null!");
        }
        this.accessDecision = accessDecision;
        this.accessDenied = accessDenied;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (accessDecision.decide()) {
            chain.doFilter(request, response);
        }
        accessDenied.denied();
    }
}
