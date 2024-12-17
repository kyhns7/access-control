package com.kyhns7.rbac.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleSearchDto extends BaseSearchDto{
    /**
     * 角色标识
     */
    private String symbol;
    /**
     * 角色名
     */
    private String name;
}
