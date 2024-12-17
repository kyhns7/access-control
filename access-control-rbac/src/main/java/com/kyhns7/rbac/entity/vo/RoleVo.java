package com.kyhns7.rbac.entity.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 角色
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class RoleVo  {
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
	 * 角色标识
	 */
	private String symbol;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 描述/备注
	 */
	private String describe;
	/**
	 * 系统内置,1是、0否
	 */
	private Integer internal;
}
