package com.yetech.springbootapi.support;

import com.yetech.springbootapi.util.ConstantUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName: BaseController
 * @Description: 基本 controller
 * @Author: Demon
 * @Date: 2020/6/1 16:36
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
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
