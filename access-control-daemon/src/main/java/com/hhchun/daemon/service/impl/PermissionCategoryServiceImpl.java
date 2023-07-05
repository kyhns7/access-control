package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.common.base.Preconditions;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.dao.PermissionCategoryDao;
import com.hhchun.daemon.entity.dto.PermissionCategoryDto;
import com.hhchun.daemon.entity.dto.PermissionCategorySearchDto;
import com.hhchun.daemon.entity.vo.PermissionCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.hhchun.daemon.entity.domain.PermissionCategoryEntity;
import com.hhchun.daemon.service.PermissionCategoryService;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("permissionCategoryService")
public class PermissionCategoryServiceImpl extends ServiceImpl<PermissionCategoryDao, PermissionCategoryEntity> implements PermissionCategoryService {

    @Override
    public void savePermissionCategory(PermissionCategoryDto permissionCategoryDto) {
        PermissionCategoryEntity permissionCategory = new PermissionCategoryEntity();
        BeanUtils.copyProperties(permissionCategoryDto, permissionCategory);
        save(permissionCategory);
    }

    @Override
    public void modifyPermissionCategory(PermissionCategoryDto permissionCategoryDto) {
        final Long permissionCategoryId = permissionCategoryDto.getId();
        PermissionCategoryEntity permissionCategory = getPermissionCategoryById(permissionCategoryId);
        Preconditions.checkCondition(permissionCategory != null, "权限类别不存在");
        BeanUtils.copyProperties(permissionCategoryDto, permissionCategory);
        updateById(permissionCategory);
    }

    @Override
    public PermissionCategoryEntity getPermissionCategoryById(Long permissionCategoryId) {
        return getById(permissionCategoryId);
    }

    @Override
    public void removePermissionCategory(Long permissionCategoryId) {
        PermissionCategoryEntity permissionCategory = getPermissionCategoryById(permissionCategoryId);
        Preconditions.checkCondition(permissionCategory != null, "权限类别不存在");
        removeById(permissionCategoryId);
    }

    @Override
    public PageResult<PermissionCategoryVo> getPermissionCategoryList(PermissionCategorySearchDto searchDto) {
        Long permissionCategoryId = searchDto.getId();
        String name = searchDto.getName();
        String des = searchDto.getDes();
        LambdaQueryWrapper<PermissionCategoryEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PermissionCategoryEntity::getCreateTime);
        wrapper.eq(permissionCategoryId != null, PermissionCategoryEntity::getId, permissionCategoryId);
        wrapper.like(StringUtils.hasLength(name), PermissionCategoryEntity::getName, name);
        wrapper.like(StringUtils.hasLength(des), PermissionCategoryEntity::getDes, des);
        IPage<PermissionCategoryEntity> page = page(searchDto.getPage(), wrapper);
        List<PermissionCategoryVo> permissionCategoryVos = page.getRecords().stream().map(permissionCategory -> {
            PermissionCategoryVo permissionCategoryVo = new PermissionCategoryVo();
            BeanUtils.copyProperties(permissionCategory, permissionCategoryVo);
            return permissionCategoryVo;
        }).collect(Collectors.toList());
        return PageResult.convert(page, permissionCategoryVos);
    }
}