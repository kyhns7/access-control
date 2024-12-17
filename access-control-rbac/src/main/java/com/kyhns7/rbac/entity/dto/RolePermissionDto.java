package com.kyhns7.rbac.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 角色关联权限(多对多)
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class RolePermissionDto {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 权限id
	 */
	private List<Long> permissionIds;
}
