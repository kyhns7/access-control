package com.hhchun.daemon.decision;

import com.hhchun.daemon.provider.Permission;
import com.hhchun.daemon.provider.SubjectOwnedPermissionsProvider;
import com.hhchun.daemon.provider.TargetAccessiblePermissionsProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class AffirmativeAccessDecision extends AbstractAccessDecision {

    public AffirmativeAccessDecision(final SubjectOwnedPermissionsProvider sop,
                                     final TargetAccessiblePermissionsProvider tap) throws IllegalArgumentException {
        super(sop, tap);
    }

    @Override
    public boolean decide() {
        final List<Permission> subjectOwnedPermissions = Optional.ofNullable(sop.provide()).orElse(Collections.emptyList());
        final List<Permission> targetAccessiblePermissions = Optional.ofNullable(tap.provide()).orElse(Collections.emptyList());
        for (Permission targetAccessiblePermission : targetAccessiblePermissions) {
            if (subjectOwnedPermissions.contains(targetAccessiblePermission)) {
                return true;
            }
        }
        return false;
    }

}
