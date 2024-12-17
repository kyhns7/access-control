package com.kyhns7.rbac.entity.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 权限
 * 
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
@TableName("ac_permission")
public class PermissionEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	// 是否系统内置
	public static final int INTERNAL_YES = 0;
	public static final int INTERNAL_NO = 0;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 是否删除
	 */
	private Integer del;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.UPDATE)
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
	 * 受保护的资源
	 */
	private String resource;
	/**
	 * 系统内置,1是、0否
	 */
	private Integer internal;
}
