package com.hhchun.daemon.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {

    /**
     * 设置微信小程序的appid
     */
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
    private String secret;

    /**
     * 小程序版本
     * 默认"release" 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"
     */
    private String envVersion;

}
