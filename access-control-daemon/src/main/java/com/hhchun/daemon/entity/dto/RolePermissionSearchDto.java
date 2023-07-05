package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.utils.PageArguments;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色关联权限(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionSearchDto extends PageArguments implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
}
