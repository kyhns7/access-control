package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.common.base.Preconditions;
import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.common.utils.Query;
import com.kyhns7.rbac.dao.MenuDao;
import com.kyhns7.rbac.dao.RoleDao;
import com.kyhns7.rbac.entity.domain.MenuEntity;
import com.kyhns7.rbac.entity.domain.RoleEntity;
import com.kyhns7.rbac.entity.dto.MenuDto;
import com.kyhns7.rbac.entity.dto.RoleDto;
import com.kyhns7.rbac.entity.dto.RoleSearchDto;
import com.kyhns7.rbac.entity.vo.MenuVo;
import com.kyhns7.rbac.entity.vo.RoleVo;
import com.kyhns7.rbac.service.MenuService;
import com.kyhns7.rbac.service.RoleService;
import com.kyhns7.rbac.service.event.CrudEvent;
import com.kyhns7.rbac.service.event.MenuEvent;
import com.kyhns7.rbac.service.event.RoleEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public List<MenuVo> getMenuTree() {
        List<MenuVo> list = list().stream().map(m -> {
            MenuVo mv = new MenuVo();
            BeanUtils.copyProperties(m, mv);
            return mv;
        }).toList();
        Map<Long, MenuVo> map = list.stream().collect(Collectors.toMap(MenuVo::getId, m -> m));
        ArrayList<MenuVo> roots = new ArrayList<>();
        for (MenuVo mv : list) {
            Long parentId = mv.getParentId();
            if (parentId == MenuEntity.ROOT_PARENT_ID) {
                roots.add(mv);
            }
            MenuVo pmv = map.get(parentId);
            if (pmv != null) {
                pmv.getChildren().add(mv);
            }
        }
        // 排序
        sortMenuTree(roots);
        return roots;
    }

    @Override
    public void saveMenu(MenuDto menuDto) {
        Long parentId = menuDto.getParentId();
        Integer type = menuDto.getType();
        String path = menuDto.getPath();
        String name = menuDto.getName();
        String title = menuDto.getTitle();
        String symbol = menuDto.getSymbol();
        Preconditions.checkArgument(getById(parentId) != null, "请选择父菜单");
        Preconditions.checkArgument(MenuEntity.TYPES.contains(type), "请选择菜单类型");
        Preconditions.checkArgument(StringUtils.hasLength(title), "请输入菜单名称");
        if (type == MenuEntity.TYPE_MENU || type == MenuEntity.TYPE_IFRAME || type == MenuEntity.TYPE_EXTERNAL_LINK) {
            Preconditions.checkArgument(StringUtils.hasLength(path), "请输入路由路径");
            Preconditions.checkArgument(getMenuByPath(path) == null, "路由路径已存在");
            Preconditions.checkArgument(StringUtils.hasLength(name), "请输入路由名称");
        }
        if (type == MenuEntity.TYPE_BUTTON) {
            Preconditions.checkArgument(StringUtils.hasLength(symbol), "请输入权限标识");
            MenuEntity sm = getMenuBySymbol(symbol);
            Preconditions.checkArgument(sm == null, "权限标识已存在");
        }
        MenuEntity menu = new MenuEntity();
        BeanUtils.copyProperties(menuDto, menu);
        save(menu);
        publisher.publishEvent(new MenuEvent(this, CrudEvent.Event.CREATE, menu.getId()));
    }

    @Override
    public void modifyMenu(MenuDto menuDto) {
        Long id = menuDto.getId();
        MenuEntity menu = getById(id);
        Preconditions.checkArgument(menu != null, "请刷新后再试");
        Long parentId = Optional.ofNullable(menuDto.getParentId()).orElse(menu.getParentId());
        Integer type = Optional.ofNullable(menuDto.getType()).orElse(menu.getType());
        String title = Optional.ofNullable(menuDto.getTitle()).orElse(menu.getTitle());
        String path = Optional.ofNullable(menuDto.getPath()).orElse(menu.getPath());
        String name = Optional.ofNullable(menuDto.getName()).orElse(menu.getName());
        String symbol = Optional.ofNullable(menuDto.getSymbol()).orElse(menu.getSymbol());

        Preconditions.checkArgument(getById(parentId) != null, "请选择父菜单");
        Preconditions.checkArgument(MenuEntity.TYPES.contains(type), "请选择菜单类型");
        Preconditions.checkArgument(StringUtils.hasLength(title), "请输入菜单名称");
        if (type == MenuEntity.TYPE_MENU || type == MenuEntity.TYPE_IFRAME || type == MenuEntity.TYPE_EXTERNAL_LINK) {
            Preconditions.checkArgument(StringUtils.hasLength(path), "请输入路由路径");
            Preconditions.checkArgument(getMenuByPath(path) == null, "路由路径已存在");
            Preconditions.checkArgument(StringUtils.hasLength(name), "请输入路由名称");
        }
        if (type == MenuEntity.TYPE_BUTTON) {
            Preconditions.checkArgument(StringUtils.hasLength(symbol), "请输入权限标识");
            MenuEntity sm = getMenuBySymbol(symbol);
            Preconditions.checkArgument(sm == null, "权限标识已存在");
        }
        BeanUtils.copyProperties(menuDto, menu);
        updateById(menu);
        publisher.publishEvent(new MenuEvent(this, CrudEvent.Event.UPDATE, menu.getId()));
    }

    @Override
    public void removeMenu(Long id) {
        MenuEntity menu = getById(id);
        Preconditions.checkArgument(menu != null, "请刷新后再试");
        removeById(id);
        publisher.publishEvent(new MenuEvent(this, CrudEvent.Event.DELETE, id));
    }

    @Override
    public MenuEntity getMenuByPath(String path) {
        if (StringUtils.hasLength(path)) {
            return getOne(new LambdaQueryWrapper<MenuEntity>()
                    .eq(MenuEntity::getPath, path));
        }
        return null;
    }

    @Override
    public MenuEntity getMenuBySymbol(String symbol) {
        if (StringUtils.hasLength(symbol)) {
            return getOne(new LambdaQueryWrapper<MenuEntity>()
                    .eq(MenuEntity::getSymbol, symbol));
        }
        return null;
    }

    private void sortMenuTree(List<MenuVo> mvs) {
        mvs.sort(Comparator.comparingInt(MenuVo::getRank));
        for (MenuVo mv : mvs) {
            List<MenuVo> children = mv.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sortMenuTree(children);
            }
        }
    }
}