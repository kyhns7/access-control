package com.hhchun.daemon.provider;

import com.google.common.base.Preconditions;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DelegatingTargetAccessiblePermissionsProvider implements TargetAccessiblePermissionsProvider {
    public static final String BEAN_NAME = "com.hhchun.access.provider.DelegatingTargetAccessiblePermissionsProvider";

    private final Set<TargetAccessiblePermissionsProvider> taps;

    public DelegatingTargetAccessiblePermissionsProvider(Set<TargetAccessiblePermissionsProvider> taps) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(taps), "taps is Empty!");
        this.taps = taps;
    }

    @Override
    public List<Permission> provide() {
        return taps.stream().flatMap(tap -> tap.provide().stream()).collect(Collectors.toList());
    }
}
