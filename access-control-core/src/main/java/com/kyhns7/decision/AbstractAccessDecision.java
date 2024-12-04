package com.kyhns7.decision;

import com.google.common.base.Preconditions;
import com.kyhns7.provider.Permission;
import com.kyhns7.provider.ResourceAccessPermissionsProvider;
import com.kyhns7.provider.VisitorOwnedPermissionsProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AbstractAccessDecision implements AccessDecision {
    /**
     * 访问者拥有的权限提供者
     */
    protected final VisitorOwnedPermissionsProvider vp;
    /**
     * 访问资源需要的权限提供者
     */
    protected final ResourceAccessPermissionsProvider rp;

    public AbstractAccessDecision(final VisitorOwnedPermissionsProvider vp,
                                  final ResourceAccessPermissionsProvider rp) throws IllegalArgumentException {
        Preconditions.checkArgument(vp != null, "vp == null!");
        Preconditions.checkArgument(rp != null, "rp == null!");
        this.vp = vp;
        this.rp = rp;
    }

    @Override
    public boolean decide() {
        final List<Permission> vps = Optional.ofNullable(vp.provide()).orElse(Collections.emptyList());
        final List<Permission> rps = Optional.ofNullable(rp.provide()).orElse(Collections.emptyList());
        for (Permission targetAccessiblePermission : rps) {
            if (!vps.contains(targetAccessiblePermission)) {
                return false;
            }
        }
        return true;
    }
}
