package com.lu.edu.utils.exception;


import com.lu.edu.utils.exception.DiyException;
import com.lu.edu.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;


/**
 * @Description: 注意是RestControllerAdvice
 * @Author: 雨同我
 * @DateTime: 2024/10/21 12:36
 * @param: null:
 * @return:
 */

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GloablExceptonHandler {
    // 指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e) {
        e.printStackTrace();
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(DiyException.class)
    @ResponseBody
    public CommonResult error(DiyException e) {
        log.info("进入了全局异常处理");
        e.printStackTrace();
        log.error(e.getMsg());
        return CommonResult.failed(e.getCode(), e.getMessage());
    }

    // 参数校验异常 spring-data-validate

    /**
     * @Description: javax.validation.ConstraintViolationException:
     *
     *               这个异常通常表示在使用 Bean Validation（例如，JSR
     *               303）进行数据校验时发生了违反约束的情况。例如，字段上可能有一个注解，要求其值不能为空，但实际传递的值为空。
     *               org.springframework.validation.BindException:
     *
     *               这个异常通常表示在数据绑定过程中出现错误。它可能包含多个字段的错误，每个字段的错误信息都可以通过 FieldError
     *               对象访问。
     *               org.springframework.web.bind.MethodArgumentNotValidException:
     *
     *               这个异常通常是在控制器方法参数上使用了 @Valid 注解，然后校验失败时抛出的。类似于
     *               BindException，它也包含了多个字段的错误信息，但是额外提供了一些与 Spring MVC 集成相关的功能。
     * @Author: 雨同我
     * @DateTime: 2023/12/4 17:58
     * @param: null:
     * @return:
     */

    @ExceptionHandler(value = { BindException.class, ValidationException.class, MethodArgumentNotValidException.class })
    public CommonResult handleValidatedException(Exception e) {
        CommonResult<String> resp = null;

        if (e instanceof MethodArgumentNotValidException) {
            // BeanValidation exception
            // ex.getBindingResult().getAllErrors() 也是可以的

            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            resp = CommonResult.failed(HttpStatus.BAD_REQUEST.value(),
                    ex.getBindingResult().getFieldErrors().stream()
                            .map((t) -> {
                                return t.getField() + " " + t.getDefaultMessage();
                            })
                            .collect(Collectors.joining("; ")));
        } else if (e instanceof ConstraintViolationException) {
            // BeanValidation GET simple param
            ConstraintViolationException ex = (ConstraintViolationException) e;
            resp = CommonResult.failed(HttpStatus.BAD_REQUEST.value(),
                    ex.getConstraintViolations().stream()
                            .map(k -> {
                                return k.getRootBean() + " ---" + k.getPropertyPath() + ":" + k.getMessage();
                            })
                            .collect(Collectors.joining("; ")));
        } else if (e instanceof BindException) {
            // BeanValidation GET object param
            BindException ex = (BindException) e;
            resp = CommonResult.failed(HttpStatus.BAD_REQUEST.value(),
                    ex.getFieldErrors().stream()
                            .map((t) -> {
                                return t.getField() + " " + t.getDefaultMessage();
                            })
                            .collect(Collectors.joining("; ")));
        }

        assert resp != null;
        log.error("自定义的--》参数校验异常:{}", resp);
        return CommonResult.failed("请求参数缺失");
    }


}
