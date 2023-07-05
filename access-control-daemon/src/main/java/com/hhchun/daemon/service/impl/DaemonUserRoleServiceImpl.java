package com.hhchun.daemon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.common.base.Preconditions;
import com.hhchun.daemon.common.utils.PageResult;
import com.hhchun.daemon.dao.DaemonUserRoleDao;
import com.hhchun.daemon.entity.domain.DaemonUserEntity;
import com.hhchun.daemon.entity.domain.DaemonUserRoleEntity;
import com.hhchun.daemon.entity.domain.RoleEntity;
import com.hhchun.daemon.entity.dto.DaemonUserRoleDto;
import com.hhchun.daemon.entity.dto.DaemonUserRoleSearchDto;
import com.hhchun.daemon.entity.vo.DaemonUserRoleVo;
import com.hhchun.daemon.listener.event.DaemonUserRoleEvent;
import com.hhchun.daemon.service.DaemonUserRoleService;
import com.hhchun.daemon.service.DaemonUserService;
import com.hhchun.daemon.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service("daemonUserRoleService")
public class DaemonUserRoleServiceImpl extends ServiceImpl<DaemonUserRoleDao, DaemonUserRoleEntity> implements DaemonUserRoleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DaemonUserRoleDao daemonUserRoleDao;

    @Autowired
    private DaemonUserService daemonUserService;
    @Autowired
    private RoleService roleService;

    @Override
    public DaemonUserRoleEntity getDaemonUserRole(Long daemonUserId, Long roleId) {
        return getOne(new LambdaQueryWrapper<DaemonUserRoleEntity>()
                .eq(DaemonUserRoleEntity::getDaemonUserId, daemonUserId)
                .eq(DaemonUserRoleEntity::getRoleId, roleId), false);
    }

    @Override
    public DaemonUserRoleEntity getDaemonUserRoleById(Long daemonUserRoleId) {
        return getById(daemonUserRoleId);
    }

    @Override
    public void saveDaemonUserRole(DaemonUserRoleDto daemonUserRoleDto) {
        Long daemonUserId = daemonUserRoleDto.getDaemonUserId();
        DaemonUserEntity daemonUser = daemonUserService.getDaemonUserById(daemonUserId);
        Preconditions.checkCondition(daemonUser != null, "后台用户不存在");
        Long roleId = daemonUserRoleDto.getRoleId();
        RoleEntity role = roleService.getRoleById(roleId);
        Preconditions.checkCondition(role != null, "角色不存在");
        DaemonUserRoleEntity daemonUserRole = getDaemonUserRole(daemonUserId, roleId);
        Preconditions.checkCondition(daemonUserRole == null, "后台用户与角色已关联过");
        daemonUserRole = new DaemonUserRoleEntity();
        BeanUtils.copyProperties(daemonUserRoleDto, daemonUserRole);
        save(daemonUserRole);

        // 发布新增后台用户与角色关联事件
        DaemonUserRoleEvent daemonUserRoleEvent = new DaemonUserRoleEvent(daemonUserRole.getId(), DaemonUserRoleEvent.NEWLY_ADDED, this);
        publisher.publishEvent(daemonUserRoleEvent);
    }

    @Override
    public void modifyDaemonUserRole(DaemonUserRoleDto daemonUserRoleDto) {
        Long daemonUserRoleId = daemonUserRoleDto.getId();
        DaemonUserRoleEntity daemonUserRole = getDaemonUserRoleById(daemonUserRoleId);
        Preconditions.checkCondition(daemonUserRole != null, "不存在");
        Long daemonUserId = daemonUserRoleDto.getDaemonUserId();
        if (daemonUserId != null) {
            DaemonUserEntity daemonUser = daemonUserService.getDaemonUserById(daemonUserId);
            Preconditions.checkCondition(daemonUser != null, "后台用户不存在");
        }
        Long roleId = daemonUserRoleDto.getRoleId();
        if (roleId != null) {
            RoleEntity role = roleService.getRoleById(roleId);
            Preconditions.checkCondition(role != null, "角色不存在");
        }
        Preconditions.checkCondition(getDaemonUserRole(daemonUserId, roleId) == null, "后台用户与角色已关联过");

        BeanUtils.copyProperties(daemonUserRoleDto, daemonUserRole);
        updateById(daemonUserRole);

        // 发布修改后台用户与角色关联事件
        DaemonUserRoleEvent daemonUserRoleEvent = new DaemonUserRoleEvent(daemonUserRoleId, DaemonUserRoleEvent.MODIFY, this);
        publisher.publishEvent(daemonUserRoleEvent);
    }

    @Override
    public void removeDaemonUserRole(Long daemonUserRoleId) {
        DaemonUserRoleEntity daemonUserRole = getDaemonUserRoleById(daemonUserRoleId);
        Preconditions.checkCondition(daemonUserRole != null, "不存在");
        removeById(daemonUserRoleId);

        // 发布删除后台用户与角色关联事件
        DaemonUserRoleEvent daemonUserRoleEvent = new DaemonUserRoleEvent(daemonUserRoleId, DaemonUserRoleEvent.REMOVE, this);
        publisher.publishEvent(daemonUserRoleEvent);
    }

    @Override
    public PageResult<DaemonUserRoleVo> getDaemonUserList(DaemonUserRoleSearchDto searchDto) {
        Long roleId = searchDto.getRoleId();
        Preconditions.checkArgument(roleId != null, "角色id不能为空");
        return getDaemonUserRoleList(searchDto);
    }

    @Override
    public PageResult<DaemonUserRoleVo> getRoleList(DaemonUserRoleSearchDto searchDto) {
        Long daemonUserId = searchDto.getDaemonUserId();
        Preconditions.checkArgument(daemonUserId != null, "后台用户id不能为空");
        return getDaemonUserRoleList(searchDto);
    }

    @Override
    public PageResult<DaemonUserRoleVo> getDaemonUserRoleList(DaemonUserRoleSearchDto searchDto) {
        Page<DaemonUserRoleVo> page = daemonUserRoleDao.getDaemonUserRoleList(searchDto.getPage(DaemonUserRoleEntity.class), searchDto);
        return PageResult.convert(page, page.getRecords());
    }


}