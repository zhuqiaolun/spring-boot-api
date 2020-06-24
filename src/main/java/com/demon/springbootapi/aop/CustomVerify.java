package com.demon.springbootapi.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: TokenVerify
 * @Description: 创建Token切点注解
 * @Author: Demon
 * @Date: 2019/11/6 16:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomVerify {

    boolean value() default true;

}
