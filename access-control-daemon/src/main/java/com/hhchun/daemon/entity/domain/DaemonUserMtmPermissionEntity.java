package com.hhchun.daemon.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 后台用户关联权限(多对多)
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
@TableName("ac_daemon_user_mtm_permission")
public class DaemonUserMtmPermissionEntity implements Serializable {
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
	 * 后台用户id
	 */
	private Long daemonUserId;
	/**
	 * 权限id
	 */
	private Long permissionId;

}
