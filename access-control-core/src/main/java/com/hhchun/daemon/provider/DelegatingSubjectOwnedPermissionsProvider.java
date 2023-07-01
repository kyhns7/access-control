package com.hhchun.daemon.provider;

import com.google.common.base.Preconditions;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DelegatingSubjectOwnedPermissionsProvider implements SubjectOwnedPermissionsProvider {
    public static final String BEAN_NAME = "com.hhchun.access.provider.DelegatingSubjectOwnedPermissionsProvider";

    private final Set<SubjectOwnedPermissionsProvider> sops;

    public DelegatingSubjectOwnedPermissionsProvider(Set<SubjectOwnedPermissionsProvider> sops) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(sops), "sops is Empty!");
        this.sops = sops;
    }

    @Override
    public List<Permission> provide() {
        return sops.stream().flatMap(sop -> sop.provide().stream()).collect(Collectors.toList());
    }
}
