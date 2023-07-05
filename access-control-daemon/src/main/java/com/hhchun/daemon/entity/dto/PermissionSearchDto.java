package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.utils.PageArguments;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionSearchDto extends PageArguments implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
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

}
