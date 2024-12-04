package com.kyhns7.rbac.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 权限
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
@TableName("ac_permission")
public class PermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 是否删除
	 */
	private Integer del;
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
	private String subject;
	/**
	 * 权限类别,关联ac_permission_category
	 */
	private Long categoryId;

}
