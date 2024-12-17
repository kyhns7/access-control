package com.kyhns7.rbac.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户关联角色(多对多)
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class UserRoleDto {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private List<Long> roleIds;

}
