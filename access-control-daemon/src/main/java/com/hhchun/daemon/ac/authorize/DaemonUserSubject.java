package com.hhchun.daemon.ac.authorize;

import lombok.Getter;

import java.util.Objects;

@Getter
public class DaemonUserSubject {
    public static final DaemonUserSubject ANONYMOUS = new DaemonUserSubject(Long.MIN_VALUE);

    private final Long daemonUserId;

    private DaemonUserSubject(Long daemonUserId) {
        this.daemonUserId = daemonUserId;
    }

    public static DaemonUserSubject newSubject(Long daemonUserId) {
        if (daemonUserId == null) {
            return ANONYMOUS;
        }
        return new DaemonUserSubject(daemonUserId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaemonUserSubject that = (DaemonUserSubject) o;
        return Objects.equals(daemonUserId, that.daemonUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daemonUserId);
    }
}
