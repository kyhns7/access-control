package com.kyhns7.provider;

import com.google.common.base.Preconditions;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DelegatingVisitorOwnedPermissionsProvider implements VisitorOwnedPermissionsProvider {
    public static final String BEAN_NAME = "delegatingVisitorOwnedPermissionsProvider";

    private final Set<VisitorOwnedPermissionsProvider> vps;

    public DelegatingVisitorOwnedPermissionsProvider(Set<VisitorOwnedPermissionsProvider> vps) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(vps), "vps is Empty!");
        this.vps = vps;
    }

    @Override
    public List<Permission> provide() {
        return vps.stream().flatMap(vp -> vp.provide().stream()).collect(Collectors.toList());
    }
}
