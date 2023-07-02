package com.hhchun.daemon.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.hhchun.daemon.config.properties.WxMaProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@AllArgsConstructor
@Configuration
@EnableConfigurationProperties({WxMaProperties.class})
public class WxConfiguration {
    private final WxMaProperties wxMaProperties;

    @Bean
    public WxMaService wxMaService() {
        WxMaService maService = new WxMaServiceImpl();
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(StringUtils.trimToNull(wxMaProperties.getAppid()));
        config.setSecret(StringUtils.trimToNull(wxMaProperties.getSecret()));
        maService.setWxMaConfig(config);
        return maService;
    }

}
