package com.kyhns7.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyhns7.rbac.common.base.Preconditions;
import com.kyhns7.rbac.common.utils.PageResult;
import com.kyhns7.rbac.common.utils.Query;
import com.kyhns7.rbac.dao.RoleDao;
import com.kyhns7.rbac.entity.domain.PermissionEntity;
import com.kyhns7.rbac.entity.dto.RoleDto;
import com.kyhns7.rbac.entity.dto.RoleSearchDto;
import com.kyhns7.rbac.entity.vo.PermissionVo;
import com.kyhns7.rbac.entity.vo.RoleVo;
import com.kyhns7.rbac.service.UserRoleService;
import com.kyhns7.rbac.service.event.CrudEvent;
import com.kyhns7.rbac.service.event.RoleEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.kyhns7.rbac.entity.domain.RoleEntity;
import com.kyhns7.rbac.service.RoleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public PageResult<RoleVo> getRoleList(RoleSearchDto searchDto) {
        String symbol = searchDto.getSymbol();
        String name = searchDto.getName();
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(symbol), RoleEntity::getSymbol, symbol);
        wrapper.like(StringUtils.hasLength(name), RoleEntity::getName, name);
        IPage<RoleEntity> page = page(new Query<RoleEntity>().getPage(searchDto.getPageParams()), wrapper);
        List<RoleVo> rvs = page.getRecords().stream().map(r -> {
            RoleVo rv = new RoleVo();
            BeanUtils.copyProperties(r, rv);
            return rv;
        }).toList();
        return PageResult.convert(page, rvs);
    }

    @Transactional
    @Override
    public void saveRole(RoleDto roleDto) {
        String name = roleDto.getName();
        String symbol = roleDto.getSymbol();
        String describe = roleDto.getDescribe();
        RoleEntity sr = getRoleBySymbol(symbol);
        Preconditions.checkArgument(sr == null, "角色标识已存在");
        RoleEntity role = new RoleEntity();
        role.setName(name);
        role.setSymbol(symbol);
        role.setDescribe(describe);
        save(role);
        publisher.publishEvent(new RoleEvent(this, CrudEvent.Event.CREATE, role.getId()));
    }

    @Override
    public RoleEntity getRoleBySymbol(String symbol) {
        if (StringUtils.hasLength(symbol)) {
            return getOne(new LambdaQueryWrapper<RoleEntity>()
                    .eq(RoleEntity::getSymbol, symbol));
        }
        return null;
    }

    @Transactional
    @Override
    public void modifyRole(RoleDto roleDto) {
        Long id = roleDto.getId();
        String name = roleDto.getName();
        String symbol = roleDto.getSymbol();
        String describe = roleDto.getDescribe();
        RoleEntity role = getById(id);
        Preconditions.checkCondition(role != null, "请刷新后再试");
        if (StringUtils.hasLength(symbol) && !symbol.equals(role.getSymbol())) {
            RoleEntity sr = getRoleBySymbol(symbol);
            Preconditions.checkArgument(sr == null, "角色标识已存在");
        }
        role.setName(name);
        role.setSymbol(symbol);
        role.setDescribe(describe);
        updateById(role);
        publisher.publishEvent(new RoleEvent(this, CrudEvent.Event.UPDATE, id));
    }

    @Override
    public void removeRole(Long id) {
        RoleEntity role = getById(id);
        Preconditions.checkCondition(role != null, "请刷新后再试");
        Preconditions.checkCondition(role.getInternal() == RoleEntity.INTERNAL_NO,
                "请刷新后再试");
        removeById(id);
        publisher.publishEvent(new RoleEvent(this, CrudEvent.Event.DELETE, id));
    }

    @Override
    public List<RoleEntity> getRolesByUserId(Long userId) {
        Preconditions.checkArgument(userId != null, "userId == null");
        List<Long> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return listByIds(roleIds);
    }


}