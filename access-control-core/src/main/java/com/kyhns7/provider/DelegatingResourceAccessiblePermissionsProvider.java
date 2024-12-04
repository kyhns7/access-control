package com.kyhns7.provider;

import com.google.common.base.Preconditions;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DelegatingResourceAccessiblePermissionsProvider implements ResourceAccessPermissionsProvider {
    public static final String BEAN_NAME = "delegatingResourceAccessiblePermissionsProvider";

    private final Set<ResourceAccessPermissionsProvider> rps;

    public DelegatingResourceAccessiblePermissionsProvider(Set<ResourceAccessPermissionsProvider> rps) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(rps), "rps is Empty!");
        this.rps = rps;
    }

    @Override
    public List<Permission> provide() {
        return rps.stream().flatMap(rp -> rp.provide().stream()).collect(Collectors.toList());
    }
}
