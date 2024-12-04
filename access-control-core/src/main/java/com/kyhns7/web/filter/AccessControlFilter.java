package com.kyhns7.web.filter;

import com.google.common.base.Preconditions;
import com.kyhns7.decision.AccessDecision;
import com.kyhns7.denied.AccessDenied;

import javax.servlet.*;
import java.io.IOException;

/**
 * 权限控制过滤器
 */
public class AccessControlFilter implements Filter {
    /**
     * 访问决策器
     */
    private final AccessDecision accessDecision;
    /**
     * 访问拒绝器
     */
    private final AccessDenied accessDenied;

    public AccessControlFilter(final AccessDecision accessDecision, final AccessDenied accessDenied) {
        Preconditions.checkArgument(accessDecision != null, "accessDecision == null!");
        Preconditions.checkArgument(accessDenied != null, "accessDenied == null!");
        this.accessDecision = accessDecision;
        this.accessDenied = accessDenied;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (accessDecision.decide()) {
            chain.doFilter(request, response);
        } else {
            accessDenied.denied(new FilterWrapper(request, response, chain));
        }
    }
}
