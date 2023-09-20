package com.hhchun.daemon.ac.authorize;

import org.springframework.lang.Nullable;

public class DaemonUserSubjectHolder {
    private static final ThreadLocal<DaemonUserSubject> SUBJECT = new ThreadLocal<>();

    @Nullable
    public static DaemonUserSubject getSubject() {
        return SUBJECT.get();
    }

    public static void setSubject(@Nullable DaemonUserSubject subject) {
        SUBJECT.set(subject);
    }

    @Nullable
    public static Long getDaemonUserId() {
        DaemonUserSubject subject = getSubject();
        if (subject == null) {
            return null;
        }
        return subject.getDaemonUserId();
    }

    public static void clearSubject() {
        SUBJECT.remove();
    }
}
