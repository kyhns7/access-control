package com.hhchun.daemon.ac.provider;

import com.google.common.collect.Lists;
import com.hhchun.daemon.provider.Permission;
import com.hhchun.daemon.provider.SubjectOwnedPermissionsProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DaemonSubjectOwnedPermissionsProvider implements SubjectOwnedPermissionsProvider {

    @Override
    public List<Permission> provide() {
        List<Permission> rps = releasePermissionProvide();
        List<Permission> aps = alonePermissionProvide();
        return Stream.concat(rps.stream(), aps.stream()).collect(Collectors.toList());
    }

    /**
     * 单独拥有的权限
     */
    private List<Permission> alonePermissionProvide() {
        return Lists.newArrayList();
    }

    /**
     * 公开的权限
     */
    private List<Permission> releasePermissionProvide() {
        return Lists.newArrayList(new Permission("develop"));
    }
}
