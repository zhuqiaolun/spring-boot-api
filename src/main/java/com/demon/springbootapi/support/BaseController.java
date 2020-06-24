package com.demon.springbootapi.support;

import com.demon.springbootapi.util.ConstantUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName: BaseController
 * @Description: 基本 controller
 * @Author: Demon
 * @Date: 2020/6/1 16:36
 */
public class BaseController {

    private ServletRequestAttributes getServletRequestAttributes(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    protected String getAppId(){
        return String.valueOf(this.getServletRequestAttributes().getRequest().getAttribute(ConstantUtil.APP_ID));
    }

    protected String getAppKey(){
        return String.valueOf(this.getServletRequestAttributes().getRequest().getAttribute(ConstantUtil.APP_KEY));
    }

}
