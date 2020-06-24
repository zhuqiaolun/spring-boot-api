package com.yetech.springbootapi.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: TokenVerify
 * @Description: 创建Token切点注解
 * @Author: Demon
 * @Date: 2019/11/6 16:22
 * @Copyright: 2019 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomVerify {

    boolean value() default true;

}
