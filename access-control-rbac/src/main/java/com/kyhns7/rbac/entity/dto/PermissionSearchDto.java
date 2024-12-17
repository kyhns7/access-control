package com.kyhns7.rbac.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionSearchDto extends BaseSearchDto {
    /**
     * 权限标识
     */
    private String symbol;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限主体对象
     */
    private String resource;
}
