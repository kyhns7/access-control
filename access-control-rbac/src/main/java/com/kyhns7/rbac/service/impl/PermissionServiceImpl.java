package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.common.base.Preconditions;
import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.common.utils.Query;
import com.kyhns7.rbac.dao.PermissionDao;
import com.kyhns7.rbac.entity.dto.PermissionDto;
import com.kyhns7.rbac.entity.dto.PermissionSearchDto;
import com.kyhns7.rbac.entity.vo.PermissionVo;
import com.kyhns7.rbac.service.RolePermissionService;
import com.kyhns7.rbac.service.UserRoleService;
import com.kyhns7.rbac.service.event.CrudEvent;
import com.kyhns7.rbac.service.event.PermissionEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import com.kyhns7.rbac.entity.domain.PermissionEntity;
import com.kyhns7.rbac.service.PermissionService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Transactional
    @Override
    public void savePermission(PermissionDto permissionDto) {
        String name = permissionDto.getName();
        String symbol = permissionDto.getSymbol();
        String resource = permissionDto.getResource();
        String describe = permissionDto.getDescribe();
        Preconditions.checkArgument(StringUtils.hasLength(name), "请输入权限名称");
        Preconditions.checkArgument(StringUtils.hasLength(symbol), "请输入权限标识");
        Preconditions.checkArgument(StringUtils.hasLength(resource), "请输入权限主体");
        PermissionEntity sp = getPermissionBySymbol(symbol);
        Preconditions.checkArgument(sp == null, "权限标识已存在");
        PermissionEntity permission = new PermissionEntity();
        permission.setName(name);
        permission.setSymbol(symbol);
        permission.setResource(resource);
        permission.setDescribe(describe);
        save(permission);
        publisher.publishEvent(new PermissionEvent(this, CrudEvent.Event.CREATE, permission.getId()));
    }


    @Transactional
    @Override
    public void modifyPermission(PermissionDto permissionDto) {
        Long id = permissionDto.getId();
        String name = permissionDto.getName();
        String symbol = permissionDto.getSymbol();
        String resource = permissionDto.getResource();
        String describe = permissionDto.getDescribe();
        Preconditions.checkArgument(id != null, "id cannot be empty!");
        PermissionEntity permission = getById(id);
        Preconditions.checkCondition(permission != null, "请刷新后再试");
        if (StringUtils.hasLength(symbol) && !symbol.equals(permission.getSymbol())) {
            PermissionEntity sp = getPermissionBySymbol(symbol);
            Preconditions.checkArgument(sp == null, "权限标识已存在");
        }
        permission.setName(name);
        permission.setSymbol(symbol);
        permission.setResource(resource);
        permission.setDescribe(describe);
        updateById(permission);
        publisher.publishEvent(new PermissionEvent(this, CrudEvent.Event.UPDATE, id));
    }

    @Transactional
    @Override
    public void removePermission(Long id) {
        PermissionEntity permission = getById(id);
        Preconditions.checkCondition(permission != null, "请刷新后再试");
        Preconditions.checkCondition(permission.getInternal() == PermissionEntity.INTERNAL_YES,
                "系统内置权限,无法删除");
        removeById(id);
        publisher.publishEvent(new PermissionEvent(this, CrudEvent.Event.DELETE, id));
    }

    @Override
    public PageResult<PermissionVo> getPermissionList(PermissionSearchDto searchDto) {
        String symbol = searchDto.getSymbol();
        String name = searchDto.getName();
        String resource = searchDto.getResource();
        LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(symbol), PermissionEntity::getSymbol, symbol);
        wrapper.like(StringUtils.hasLength(name), PermissionEntity::getName, name);
        wrapper.like(StringUtils.hasLength(resource), PermissionEntity::getResource, resource);
        IPage<PermissionEntity> page = page(new Query<PermissionEntity>().getPage(searchDto.getPageParams()), wrapper);
        List<PermissionVo> pvs = page.getRecords().stream().map(p -> {
            PermissionVo pv = new PermissionVo();
            BeanUtils.copyProperties(p, pv);
            return pv;
        }).toList();
        return PageResult.convert(page, pvs);
    }

    @Override
    public PermissionEntity getPermissionBySymbol(String symbol) {
        if (StringUtils.hasLength(symbol)) {
            return getOne(new LambdaQueryWrapper<PermissionEntity>()
                    .eq(PermissionEntity::getSymbol, symbol));
        }
        return null;
    }

    @Override
    public List<PermissionEntity> getPermissionsByUserId(Long userId) {
        List<Long> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        List<Long> permissionIds = rolePermissionService.getPermissionIdsByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return new ArrayList<>();
        }
        return getPermissionByIds(permissionIds);
    }

    @Override
    public List<PermissionEntity> getPermissionByIds(List<Long> ids) {
        Preconditions.checkArgument(ids != null, "ids == null");
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return listByIds(ids);
    }
}