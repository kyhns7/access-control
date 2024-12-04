package com.kyhns7.denied;

/**
 * 访问拒绝器
 */
public interface AccessDenied {
    /**
     * 拒绝
     */
    void denied(Object resource);
}
