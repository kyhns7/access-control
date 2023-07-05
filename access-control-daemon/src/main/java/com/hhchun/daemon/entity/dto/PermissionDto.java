package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.constant.ValidationConstant.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class PermissionDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @NotNull(groups = UPDATE.class, message = "权限id不能为空")
    private Long id;
    /**
     * 权限标识
     */
    @NotBlank(groups = ADD.class, message = "权限标识不能为空")
    private String symbol;
    /**
     * 权限名称
     */
    @NotBlank(groups = ADD.class, message = "权限名称不能为空")
    private String name;
    /**
     * 描述/备注
     */
    private String des;
    /**
     * 权限主体对象
     */
    @NotBlank(groups = ADD.class, message = "权限主体对象不能为空")
    private String subject;
    /**
     * 权限类别,关联ac_permission_category
     */
    @NotNull(groups = ADD.class, message = "权限类别不能为空")
    private Long categoryId;

}
