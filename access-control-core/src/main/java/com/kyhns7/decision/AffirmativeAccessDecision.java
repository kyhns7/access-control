package com.kyhns7.decision;

import com.kyhns7.provider.Permission;
import com.kyhns7.provider.ResourceAccessPermissionsProvider;
import com.kyhns7.provider.VisitorOwnedPermissionsProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class AffirmativeAccessDecision extends AbstractAccessDecision {

    public AffirmativeAccessDecision(final VisitorOwnedPermissionsProvider vp,
                                     final ResourceAccessPermissionsProvider rp) throws IllegalArgumentException {
        super(vp, rp);
    }

    @Override
    public boolean decide() {
        final List<Permission> vps = Optional.ofNullable(super.vp.provide()).orElse(Collections.emptyList());
        final List<Permission> rps = Optional.ofNullable(super.rp.provide()).orElse(Collections.emptyList());
        if (rps.isEmpty()) {
            return true;
        }
        for (Permission targetAccessiblePermission : rps) {
            if (vps.contains(targetAccessiblePermission)) {
                return true;
            }
        }
        return false;
    }

}
