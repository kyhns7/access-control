package com.hhchun.daemon.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.common.base.Preconditions;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.dao.PermissionDao;
import com.hhchun.daemon.entity.domain.PermissionCategoryEntity;
import com.hhchun.daemon.entity.domain.PermissionEntity;
import com.hhchun.daemon.entity.dto.PermissionDto;
import com.hhchun.daemon.entity.dto.PermissionSearchDto;
import com.hhchun.daemon.entity.vo.PermissionVo;
import com.hhchun.daemon.listener.event.PermissionEvent;
import com.hhchun.daemon.service.PermissionCategoryService;
import com.hhchun.daemon.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PermissionCategoryService permissionCategoryService;

    @Override
    public void savePermission(PermissionDto permissionDto) {
        String symbol = permissionDto.getSymbol();
        Long categoryId = permissionDto.getCategoryId();
        PermissionEntity permission = getPermissionBySymbol(symbol);
        Preconditions.checkCondition(permission == null, StrUtil.format("权限标识[{}]已存在", symbol));
        PermissionCategoryEntity permissionCategory = permissionCategoryService.getPermissionCategoryById(categoryId);
        Preconditions.checkCondition(permissionCategory != null, "权限类别不存在");
        permission = new PermissionEntity();
        BeanUtils.copyProperties(permissionDto, permission);
        save(permission);
    }

    @Override
    public PermissionEntity getPermissionBySymbol(String symbol) {
        return getOne(new LambdaQueryWrapper<PermissionEntity>()
                .eq(PermissionEntity::getSymbol, symbol), false);
    }

    @Override
    public void modifyPermission(PermissionDto permissionDto) {
        final Long permissionId = permissionDto.getId();
        PermissionEntity permission = getPermissionById(permissionId);
        Preconditions.checkCondition(permission != null, "权限不存在");

        String symbol = permissionDto.getSymbol();
        if (StringUtils.hasLength(symbol)) {
            PermissionEntity symbolPermission = getPermissionBySymbol(symbol);
            Preconditions.checkCondition(symbolPermission == null || symbolPermission.getId().equals(permissionId),
                    StrUtil.format("权限标识[{}]已存在", symbol));
        }
        Long categoryId = permissionDto.getCategoryId();
        if (categoryId != null) {
            PermissionCategoryEntity permissionCategory = permissionCategoryService.getPermissionCategoryById(categoryId);
            Preconditions.checkCondition(permissionCategory != null, "权限类别不存在");
        }
        BeanUtils.copyProperties(permissionDto, permission);
        updateById(permission);

        // 发布权限修改事件
        publisher.publishEvent(new PermissionEvent(permissionId, PermissionEvent.MODIFY, this));
    }

    @Override
    public PermissionEntity getPermissionById(Long permissionId) {
        return getById(permissionId);
    }

    @Override
    public void removePermission(Long permissionId) {
        PermissionEntity permission = getPermissionById(permissionId);
        Preconditions.checkCondition(permission != null, "权限不存在");
        removeById(permissionId);

        // 发布权限删除事件
        publisher.publishEvent(new PermissionEvent(permissionId, PermissionEvent.REMOVE, this));
    }

    @Override
    public PageResult<PermissionVo> getPermissionList(PermissionSearchDto searchDto) {
        Long permissionId = searchDto.getId();
        String symbol = searchDto.getSymbol();
        String name = searchDto.getName();
        String des = searchDto.getDes();
        String subject = searchDto.getSubject();
        Long categoryId = searchDto.getCategoryId();
        LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PermissionEntity::getCreateTime);
        wrapper.eq(permissionId != null, PermissionEntity::getId, permissionId);
        wrapper.eq(categoryId != null, PermissionEntity::getCategoryId, categoryId);
        wrapper.like(StringUtils.hasLength(symbol), PermissionEntity::getSymbol, symbol);
        wrapper.like(StringUtils.hasLength(name), PermissionEntity::getName, name);
        wrapper.like(StringUtils.hasLength(des), PermissionEntity::getDes, des);
        wrapper.like(StringUtils.hasLength(subject), PermissionEntity::getSubject, subject);
        IPage<PermissionEntity> page = page(searchDto.getPage(), wrapper);
        List<PermissionEntity> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new PageResult<>();
        }
        List<Long> categoryIds = records.stream().map(PermissionEntity::getCategoryId).collect(Collectors.toList());
        Map<Long, PermissionCategoryEntity> PermissionCategoryMap = permissionCategoryService.listByIds(categoryIds)
                .stream().collect(Collectors.toMap(PermissionCategoryEntity::getId, pc -> pc));
        List<PermissionVo> permissionVos = records.stream().map(permission -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(permission, permissionVo);
            PermissionCategoryEntity permissionCategory = PermissionCategoryMap.get(permissionVo.getCategoryId());
            permissionVo.setCategoryName(permissionCategory.getName());
            return permissionVo;
        }).collect(Collectors.toList());
        return PageResult.convert(page, permissionVos);
    }
}