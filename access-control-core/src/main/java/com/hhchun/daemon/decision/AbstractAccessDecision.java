package com.hhchun.daemon.decision;

import com.google.common.base.Preconditions;
import com.hhchun.daemon.provider.Permission;
import com.hhchun.daemon.provider.SubjectOwnedPermissionsProvider;
import com.hhchun.daemon.provider.TargetAccessiblePermissionsProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AbstractAccessDecision implements AccessDecision {
    /**
     * 当前操作者拥有的权限提供者
     */
    protected final SubjectOwnedPermissionsProvider sop;
    /**
     * 受保护目标资源可访问(可到达)的权限提供者
     */
    protected final TargetAccessiblePermissionsProvider tap;

    public AbstractAccessDecision(final SubjectOwnedPermissionsProvider sop,
                                  final TargetAccessiblePermissionsProvider tap) throws IllegalArgumentException {
        Preconditions.checkArgument(sop != null, "sop == null!");
        Preconditions.checkArgument(tap != null, "tap == null!");
        this.sop = sop;
        this.tap = tap;
    }

    @Override
    public boolean decide() {
        final List<Permission> subjectOwnedPermissions = Optional.ofNullable(sop.provide()).orElse(Collections.emptyList());
        final List<Permission> targetAccessiblePermissions = Optional.ofNullable(tap.provide()).orElse(Collections.emptyList());
        for (Permission targetAccessiblePermission : targetAccessiblePermissions) {
            if (!subjectOwnedPermissions.contains(targetAccessiblePermission)) {
                return false;
            }
        }
        return true;
    }
}
