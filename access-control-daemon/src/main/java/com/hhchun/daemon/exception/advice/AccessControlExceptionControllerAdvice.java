package com.hhchun.daemon.exception.advice;


import com.hhchun.daemon.common.constant.ResultCodeConstant;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.exception.AccessDeniedException;
import com.hhchun.daemon.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Integer.MAX_VALUE - 2)
public class AccessControlExceptionControllerAdvice {

    @ExceptionHandler(value = AccessDeniedException.class)
    public R<?> accessDeniedExceptionHandler(AccessDeniedException e) {
        log.info(e.getMessage());
        return R.error(ResultCodeConstant.ACCESS_DENIED.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public R<?> authenticationExceptionHandler(AuthenticationException e) {
        log.info(e.getMessage());
        return R.error(ResultCodeConstant.AUTHENTICATION_ERROR.getCode(), e.getMessage());
    }
}
