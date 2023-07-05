package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.constant.ValidationConstant.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限类别/分类
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class PermissionCategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(groups = UPDATE.class, message = "权限类别id不能为空")
    private Long id;
    /**
     * 类别名称
     */
    @NotBlank(groups = ADD.class, message = "类别名称不能为空")
    private String name;
    /**
     * 描述/备注
     */
    private String des;
}
