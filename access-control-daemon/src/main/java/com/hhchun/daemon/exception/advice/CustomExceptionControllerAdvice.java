package com.hhchun.daemon.exception.advice;

import com.hhchun.daemon.common.constant.ResultCodeConstant;
import com.hhchun.daemon.common.utils.R;
import com.hhchun.daemon.exception.IllegalConditionException;
import com.hhchun.daemon.exception.InvalidArgumentException;
import com.hhchun.daemon.exception.UnknownErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@Order(Integer.MAX_VALUE - 2)
public class CustomExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<String> errorInfos = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String errorInfo = StringUtils.collectionToDelimitedString(errorInfos, "\n");
        log.info(errorInfo);
        return R.error(ResultCodeConstant.ARGUMENT_NOT_VALID.getCode(), errorInfo);
    }

    @ExceptionHandler(value = IllegalConditionException.class)
    public R<?> illegalConditionExceptionHandler(IllegalConditionException e) {
        log.info(e.getMessage());
        return R.error(ResultCodeConstant.ILLEGAL_CONDITION.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = InvalidArgumentException.class)
    public R<?> invalidArgumentExceptionHandler(InvalidArgumentException e) {
        log.info(e.getMessage());
        return R.error(ResultCodeConstant.ARGUMENT_NOT_VALID.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = UnknownErrorException.class)
    public R<?> unknownErrorExceptionHandler(UnknownErrorException e) {
        log.error(e.getMessage());
        return R.error(ResultCodeConstant.ERROR.getCode(), e.getMessage());
    }
}
