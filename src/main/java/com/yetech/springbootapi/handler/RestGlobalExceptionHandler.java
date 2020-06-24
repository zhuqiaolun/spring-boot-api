package com.yetech.springbootapi.handler;

import com.yetech.springbootapi.util.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 统一异常处理
 * @Author: Demon
 * @Date: 2020/5/8 11:31
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
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
