package com.kyhns7.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kyhns7.rbac.entity.domain.MenuEntity;
import com.kyhns7.rbac.entity.dto.MenuDto;
import com.kyhns7.rbac.entity.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<MenuEntity> {

    List<MenuVo> getMenuTree();

    void saveMenu(MenuDto menuDto);

    void modifyMenu(MenuDto menuDto);

    MenuEntity getMenuByPath(String path);

    MenuEntity getMenuBySymbol(String symbol);

    void removeMenu(Long id);
}
