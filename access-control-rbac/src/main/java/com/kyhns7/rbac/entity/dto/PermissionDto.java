package com.kyhns7.rbac.entity.dto;

import lombok.Data;

/**
 * 权限
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class PermissionDto {
    /**
     * id
     */
    private Long id;
    /**
     * 权限标识
     */
    private String symbol;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 描述/备注
     */
    private String describe;
    /**
     * 权限主体对象
     */
    private String resource;
}
