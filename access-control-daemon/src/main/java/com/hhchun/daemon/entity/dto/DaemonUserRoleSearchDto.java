package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.utils.PageArguments;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 后台用户关联角色(多对多)
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DaemonUserRoleSearchDto extends PageArguments implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 后台用户id
	 */
	private Long daemonUserId;
	/**
	 * 角色id
	 */
	private Long roleId;
}
