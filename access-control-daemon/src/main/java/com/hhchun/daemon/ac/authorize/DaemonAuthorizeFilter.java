package com.hhchun.daemon.ac.authorize;

import com.hhchun.daemon.common.utils.TokenUtils;
import com.hhchun.daemon.filter.AccessControlFilter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class DaemonAuthorizeFilter implements Filter {
    /**
     * 注册顺序
     */
    public static final int FILTER_REGISTRATION_ORDER = AccessControlFilter.FILTER_REGISTRATION_ORDER - 1;

    public static final String TOKEN_HEADER_NAME = "daemonToken";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader(TOKEN_HEADER_NAME);
        Long daemonUserId = TokenUtils.getDaemonUserId(token);
        DaemonUserSubject subject = DaemonUserSubject.newSubject(daemonUserId);
        DaemonUserSubjectHolder.setSubject(subject);
        chain.doFilter(request, response);
        DaemonUserSubjectHolder.clearSubject();
    }
}
