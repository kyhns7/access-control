package com.hhchun.daemon.entity.dto;

import com.hhchun.daemon.common.utils.PageArguments;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限类别/分类
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionCategorySearchDto extends PageArguments implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 描述/备注
     */
    private String des;
}
