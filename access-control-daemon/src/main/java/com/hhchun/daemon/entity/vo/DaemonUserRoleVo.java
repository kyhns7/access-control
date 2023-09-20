package com.hhchun.daemon.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台用户与角色关联
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class DaemonUserRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
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
     * 微信openid
     */
    private String openid;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickName;

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

}
