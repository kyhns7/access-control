package com.hhchun.daemon.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.common.base.Preconditions;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.dao.RoleDao;
import com.hhchun.daemon.entity.domain.RoleEntity;
import com.hhchun.daemon.entity.dto.RoleDto;
import com.hhchun.daemon.entity.dto.RoleSearchDto;
import com.hhchun.daemon.entity.vo.RoleVo;
import com.hhchun.daemon.listener.event.RoleEvent;
import com.hhchun.daemon.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public RoleEntity getRoleById(Long roleId) {
        return getById(roleId);
    }

    @Override
    public RoleEntity getRoleBySymbol(String symbol) {
        return getOne(new LambdaQueryWrapper<RoleEntity>()
                .eq(RoleEntity::getSymbol, symbol), false);
    }

    @Override
    public void saveRole(RoleDto roleDto) {
        String symbol = roleDto.getSymbol();
        RoleEntity role = getRoleBySymbol(symbol);
        Preconditions.checkCondition(role != null, StrUtil.format("角色标识[{}]已存在", symbol));
        role = new RoleEntity();
        BeanUtils.copyProperties(roleDto, role);
        save(role);
    }

    @Override
    public void modifyRole(RoleDto roleDto) {
        final Long roleId = roleDto.getId();
        final String symbol = roleDto.getSymbol();
        RoleEntity role = getRoleById(roleId);
        Preconditions.checkCondition(role != null, "角色不存在");
        if (StringUtils.hasLength(symbol)) {
            RoleEntity symbolRole = getRoleBySymbol(symbol);
            Preconditions.checkCondition(symbolRole == null || symbolRole.getId().equals(roleId), StrUtil.format("角色标识[{}]已存在", symbol));
        }
        BeanUtils.copyProperties(roleDto, role);
        updateById(role);
    }

    @Override
    public void removeRole(Long roleId) {
        RoleEntity role = getRoleById(roleId);
        Preconditions.checkCondition(role != null, "角色不存在");
        removeById(roleId);

        // 发布删除角色事件
        publisher.publishEvent(new RoleEvent(roleId, RoleEvent.REMOVE, this));
    }

    @Override
    public PageResult<RoleVo> getRoleList(RoleSearchDto searchDto) {
        Long roleId = searchDto.getId();
        String symbol = searchDto.getSymbol();
        String name = searchDto.getName();
        String des = searchDto.getDes();
        Integer def = searchDto.getDef();
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RoleEntity::getCreateTime);
        wrapper.eq(roleId != null, RoleEntity::getId, roleId);
        wrapper.like(StringUtils.hasLength(symbol), RoleEntity::getSymbol, symbol);
        wrapper.like(StringUtils.hasLength(name), RoleEntity::getName, name);
        wrapper.like(StringUtils.hasLength(des), RoleEntity::getDes, des);
        wrapper.eq(def != null, RoleEntity::getDef, def);
        IPage<RoleEntity> page = page(searchDto.getPage(), wrapper);
        List<RoleVo> roleVos = page.getRecords().stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role, roleVo);
            return roleVo;
        }).collect(Collectors.toList());
        return PageResult.convert(page, roleVos);
    }

}