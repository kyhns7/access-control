package com.kyhns7.rbac.ac.cache;
/**
 * @param symbol   权限标识
 * @param resource 受保护的资源
 */
public record ResourcePermission(String symbol, String resource) {

}
