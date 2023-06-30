package com.hhchun.access.decision;

import com.hhchun.access.provider.Permission;
import com.hhchun.access.provider.SubjectOwnedPermissionsProvider;
import com.hhchun.access.provider.TargetAccessiblePermissionsProvider;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class AffirmativeAccessDecision extends AbstractAccessDecision {

    public AffirmativeAccessDecision(@NotNull final SubjectOwnedPermissionsProvider sop,
                                     @NotNull final TargetAccessiblePermissionsProvider tap) throws IllegalArgumentException {
        super(sop, tap);
    }

    @Override
    public boolean decide() {
        final List<Permission> subjectOwnedPermissions = Optional.ofNullable(sop.provide()).orElse(Collections.emptyList());
        final List<Permission> targetAccessiblePermissions = Optional.ofNullable(tap.provide()).orElse(Collections.emptyList());
        if (targetAccessiblePermissions.isEmpty()) {
            return true;
        }
        for (Permission targetAccessiblePermission : targetAccessiblePermissions) {
            if (subjectOwnedPermissions.contains(targetAccessiblePermission)) {
                return true;
            }
        }
        return false;
    }

}
