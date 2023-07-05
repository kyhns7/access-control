package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.constant.ValidationConstant.*;
import com.hhchun.daemon.common.validation.ListValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @NotNull(groups = UPDATE.class, message = "角色id不能为空")
    private Long id;
    /**
     * 角色标识
     */
    @NotBlank(groups = ADD.class, message = "角色标识不能为空")
    private String symbol;
    /**
     * 角色名
     */
    @NotBlank(groups = ADD.class, message = "角色名不能为空")
    private String name;
    /**
     * 描述/备注
     */
    private String des;
    /**
     * 默认角色,0-否、1-是
     */
    @ListValue(groups = {ADD.class, UPDATE.class}, values = {"0", "1"}, message = "默认角色错误")
    private Integer def;
}
