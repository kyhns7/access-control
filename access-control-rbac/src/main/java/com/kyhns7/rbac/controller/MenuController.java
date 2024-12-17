package com.kyhns7.rbac.controller;

import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.common.utils.R;
import com.kyhns7.rbac.entity.dto.MenuDto;
import com.kyhns7.rbac.entity.dto.RoleDto;
import com.kyhns7.rbac.entity.dto.RoleSearchDto;
import com.kyhns7.rbac.entity.vo.MenuVo;
import com.kyhns7.rbac.entity.vo.RoleVo;
import com.kyhns7.rbac.service.MenuService;
import com.kyhns7.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单
 *
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/tree")
    public R<List<MenuVo>> getMenuTree() {
        List<MenuVo> tree = menuService.getMenuTree();
        return R.success(tree);
    }

    @PostMapping("/save")
    public R<?> saveMenu(@RequestBody MenuDto menuDto) {
        menuService.saveMenu(menuDto);

        return R.success();
    }

    @PostMapping("/modify")
    public R<?> modifyMenu(@RequestBody MenuDto menuDto) {
        menuService.modifyMenu(menuDto);

        return R.success();
    }

    @PostMapping("/remove/{id}")
    public R<?> removeMenu(@PathVariable("id") Long id) {
        menuService.removeMenu(id);

        return R.success();
    }

}
