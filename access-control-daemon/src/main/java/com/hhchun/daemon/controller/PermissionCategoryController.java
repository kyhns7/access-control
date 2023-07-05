package com.hhchun.daemon.controller;


import com.hhchun.daemon.common.constant.ValidationConstant.ADD;
import com.hhchun.daemon.common.constant.ValidationConstant.UPDATE;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.entity.dto.PermissionCategoryDto;
import com.hhchun.daemon.entity.dto.PermissionCategorySearchDto;
import com.hhchun.daemon.entity.vo.PermissionCategoryVo;
import com.hhchun.daemon.service.PermissionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 权限类别/分类
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/daemon/permission-category" )
public class PermissionCategoryController {
    @Autowired
    private PermissionCategoryService permissionCategoryService;

    /**
     * 保存/新增权限类别
     */
    @PostMapping("/save-permission-category")
    public R<?> savePermissionCategory(@Validated(ADD.class) @RequestBody PermissionCategoryDto permissionCategoryDto) {
        permissionCategoryService.savePermissionCategory(permissionCategoryDto);

        return R.success();
    }

    /**
     * 修改权限类别
     */
    @PutMapping("/modify-permission-category")
    public R<?> modifyPermissionCategory(@Validated(UPDATE.class) @RequestBody PermissionCategoryDto permissionCategoryDto) {
        permissionCategoryService.modifyPermissionCategory(permissionCategoryDto);
        return R.success();
    }

    /**
     * 删除权限类别
     */
    @DeleteMapping("/remove-permission-category/{permissionCategoryId}")
    public R<?> removePermissionCategory(@PathVariable("permissionCategoryId") Long permissionCategoryId) {
        permissionCategoryService.removePermissionCategory(permissionCategoryId);
        return R.success();
    }

    /**
     * 查询权限类别列表
     */
    @GetMapping("/permission-category-list")
    public R<PageResult<PermissionCategoryVo>> getPermissionCategoryList(@Validated PermissionCategorySearchDto searchDto) {
        PageResult<PermissionCategoryVo> result = permissionCategoryService.getPermissionCategoryList(searchDto);
        return R.success(result);
    }

}
