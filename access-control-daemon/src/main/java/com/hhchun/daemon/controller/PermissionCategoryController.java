package com.hhchun.daemon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hhchun.daemon.entity.domain.PermissionCategoryEntity;
import com.hhchun.daemon.service.PermissionCategoryService;
import com.hhchun.daemon.common.utils.R;


/**
 * 权限类别/分类
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/permissioncategory" )
public class PermissionCategoryController {
    @Autowired
    private PermissionCategoryService permissionCategoryService;


    /**
     * 保存
     */
    @RequestMapping("/save" )
    public R<?> save(@RequestBody PermissionCategoryEntity permissionCategory) {
            permissionCategoryService.save(permissionCategory);

        return R.success();
    }

}
