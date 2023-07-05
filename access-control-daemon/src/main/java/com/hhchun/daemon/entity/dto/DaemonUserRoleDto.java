package com.hhchun.daemon.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hhchun.daemon.common.constant.ValidationConstant.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 后台用户关联角色(多对多)
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
@TableName("ac_daemon_user_mtm_role")
public class DaemonUserRoleDto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@NotNull(groups = UPDATE.class, message = "后台用户与角色关联id不能为空")
	private Long id;
	/**
	 * 后台用户id
	 */
	@NotNull(groups = ADD.class, message = "后台用户id不能为空")
	private Long daemonUserId;
	/**
	 * 角色id
	 */
	@NotNull(groups = ADD.class, message = "角色id不能为空")
	private Long roleId;
}
