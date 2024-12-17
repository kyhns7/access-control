package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.dao.RoleMenusDao;
import com.kyhns7.rbac.entity.domain.RoleMenuEntity;
import com.kyhns7.rbac.entity.dto.RoleMenuDto;
import com.kyhns7.rbac.service.RoleMenuService;
import com.kyhns7.rbac.service.event.RoleMenuEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service("roleMenuServiceImpl")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenusDao, RoleMenuEntity> implements RoleMenuService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        List<RoleMenuEntity> list = list(new LambdaQueryWrapper<RoleMenuEntity>()
                .eq(RoleMenuEntity::getRoleId, roleId));
        return list.stream().map(RoleMenuEntity::getMenuId).toList();
    }

    @Transactional
    @Override
    public void saveRoleMenu(RoleMenuDto roleMenuDto) {
        Long roleId = roleMenuDto.getRoleId();
        List<Long> menuIds = roleMenuDto.getMenuIds();
        remove(new LambdaQueryWrapper<RoleMenuEntity>()
                .eq(RoleMenuEntity::getRoleId, roleId));
        if (CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        List<RoleMenuEntity> roleMenus = menuIds.stream().map(menuId -> {
            RoleMenuEntity roleMenu = new RoleMenuEntity();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            return roleMenu;
        }).toList();
        saveBatch(roleMenus);

        publisher.publishEvent(new RoleMenuEvent(this, roleId));
    }
}