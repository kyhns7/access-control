package com.hhchun.daemon.provider;

import java.util.List;

/**
 * 受保护目标资源可访问(可到达)的权限提供者
 */
public interface TargetAccessiblePermissionsProvider {
    /**
     * 提供可访问(可到达)的权限
     *
     * @return 可访问(可到达)的权限列表
     */
    List<Permission> provide();
}
