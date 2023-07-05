package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.constant.ValidationConstant.ADD;
import com.hhchun.daemon.common.constant.ValidationConstant.UPDATE;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色关联权限(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class RolePermissionDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @NotNull(groups = UPDATE.class, message = "角色关联权限id不能为空")
    private Long id;
    /**
     * 角色id
     */
    @NotNull(groups = ADD.class, message = "角色id不能为空")
    private Long roleId;
    /**
     * 权限id
     */
    @NotNull(groups = ADD.class, message = "权限id不能为空")
    private Long permissionId;
}
