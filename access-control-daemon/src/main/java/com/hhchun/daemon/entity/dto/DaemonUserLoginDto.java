package com.hhchun.daemon.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DaemonUserLoginDto {
    /**
     * 微信登录code
     */
    @NotBlank(message = "code不能为空")
    private String code;
}
