package com.demon.springbootapi.handler;

import com.demon.springbootapi.util.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 统一异常处理
 * @Author: Demon
 * @Date: 2020/5/8 11:31
 */
@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean<Object> exceptionHandler(Exception e) {
        e.printStackTrace();
        return new ResponseBean<>().setSuccess(false).setErrMsg(e.getMessage());
    }
}
