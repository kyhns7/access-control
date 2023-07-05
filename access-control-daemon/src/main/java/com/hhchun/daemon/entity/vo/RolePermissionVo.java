package com.hhchun.daemon.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class RolePermissionVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 角色标识
	 */
	private String roleSymbol;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 描述/备注
	 */
	private String roleDes;
	/**
	 * 默认角色,0-否、1-是
	 */
	private Integer roleDef;

	/**
	 * 权限id
	 */
	private Long permissionId;
	/**
	 * 权限标识
	 */
	private String permissionSymbol;
	/**
	 * 权限名称
	 */
	private String permissionName;
	/**
	 * 描述/备注
	 */
	private String permissionDes;
	/**
	 * 权限主体对象
	 */
	private String permissionSubject;
	/**
	 * 权限类别,关联ac_permission_category
	 */
	private Long permissionCategoryId;
	/**
	 * 权限类别名称
	 */
	private String permissionCategoryName;
}
