package com.kyhns7.rbac.entity.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 权限
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class PermissionVo {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 权限标识
	 */
	private String symbol;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 描述/备注
	 */
	private String describe;
	/**
	 * 权限主体对象
	 */
	private String resource;
	/**
     * 系统内置,1是、0否
	 */
	private Integer internal;
}
