package com.kyhns7.rbac.common.constant;

public enum ResultCodeConstant {
    SUCCESS(200, "成功"),
    ERROR(500, "系统错误"),
    PERMISSION(403, "无访问权限"),
    ARGUMENT_NOT_VALID(400, "参数无效"),
    ILLEGAL_CONDITION(410, "非法条件");

    ResultCodeConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Integer code;
    private String message;
}
