package com.hhchun.daemon.provider;

import java.util.List;

/**
 * 当前操作者拥有的权限提供者
 */
public interface SubjectOwnedPermissionsProvider {
    /**
     * 提供拥有的权限
     *
     * @return 拥有的权限列表
     */
    List<Permission> provide();
}
