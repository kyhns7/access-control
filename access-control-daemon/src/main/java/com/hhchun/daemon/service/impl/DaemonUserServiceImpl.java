package com.hhchun.daemon.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhchun.daemon.common.utils.TokenUtils;
import com.hhchun.daemon.dao.DaemonUserDao;
import com.hhchun.daemon.entity.domain.DaemonUserEntity;
import com.hhchun.daemon.entity.dto.DaemonUserLoginDto;
import com.hhchun.daemon.exception.UnknownErrorException;
import com.hhchun.daemon.listener.event.DaemonUserLoginEvent;
import com.hhchun.daemon.service.DaemonUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service("daemonUserService")
public class DaemonUserServiceImpl extends ServiceImpl<DaemonUserDao, DaemonUserEntity> implements DaemonUserService {


    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public DaemonUserEntity getDaemonUserById(Long daemonUserId) {
        return getById(daemonUserId);
    }

    @Override
    public DaemonUserEntity getDaemonUserByOpenid(String openid) {
        return getOne(new LambdaQueryWrapper<DaemonUserEntity>()
                .eq(DaemonUserEntity::getOpenid, openid), false);
    }

    @Override
    public String daemonUserLogin(DaemonUserLoginDto loginDto) {
        final String code = loginDto.getCode();
        final WxMaJscode2SessionResult result;
        try {
            result = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new UnknownErrorException("登录失败,未知错误");
        }
        final String openid = result.getOpenid();
        DaemonUserEntity daemonUser = getDaemonUserByOpenid(openid);
        if (daemonUser == null) {
            // 新用户
            daemonUser = new DaemonUserEntity();
            daemonUser.setNickName("后台用户" + RandomUtil.randomString(5));
            daemonUser.setOpenid(openid);
            save(daemonUser);
        }

        final Long daemonUserId = daemonUser.getId();
        final String token = TokenUtils.getToken(daemonUserId);
        // 发布后台用户登录事件
        publisher.publishEvent(new DaemonUserLoginEvent(daemonUserId, token, this));
        return token;
    }

    @Override
    public String daemonUserLoginTest(DaemonUserLoginDto loginDto) {
        final String openid = loginDto.getCode();
        DaemonUserEntity daemonUser = getDaemonUserByOpenid(openid);
        if (daemonUser == null) {
            // 新用户
            daemonUser = new DaemonUserEntity();
            daemonUser.setNickName("后台用户" + RandomUtil.randomString(5));
            daemonUser.setOpenid(openid);
            save(daemonUser);
        }
        final Long daemonUserId = daemonUser.getId();
        final String token = TokenUtils.getToken(daemonUserId);

        // 发布后台用户登录事件
        publisher.publishEvent(new DaemonUserLoginEvent(daemonUserId, token, this));
        return token;
    }
}