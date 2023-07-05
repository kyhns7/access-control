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
public class PermissionVo implements Serializable {
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
	private String des;
	/**
	 * 权限主体对象
	 */
	private String subject;
	/**
	 * 权限类别,关联ac_permission_category
	 */
	private Long categoryId;
	/**
	 * 权限类别名称
	 */
	private String categoryName;
}
