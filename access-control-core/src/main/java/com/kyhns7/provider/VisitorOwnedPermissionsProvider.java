package com.kyhns7.provider;

import java.util.List;

/**
 * 访问者拥有的权限提供者
 */
public interface VisitorOwnedPermissionsProvider {
    /**
     * 提供拥有的权限
     *
     * @return 提供拥有的权限列表
     */
    List<Permission> provide();
}
