package com.yetech.springbootapi.aop;

import com.yetech.springbootapi.util.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenAspect
 * @Description: 控制层的aop
 * @Author: Demon
 * @Date: 2019/11/6 15:34
 * @Copyright: 2019 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class ControllerAspect {

    /**
     * 写入具体切面点
     *
     * @apiNote execution 路径中的 "." 如果是精确到类，则一个就行，如果是精确到包就得两个 "."
     * @apiNote execution 路径若只精确到包,就无法使用 before、after、afterReturuning、around 四个通知模块，启动会报错
     **/
    @Pointcut("execution(public * com.yetech.springbootapi.controller..*Controller.*(..))")
    public void aspect() {}

    /**
     * 环绕通知
     * 1：before之前
     * 2：afterReturning之后
     *
     * @apiNote 1：@Around 下接的方法的参数必须为 ProceedingJoinPoint 类型,
     * @apiNote 2：proceedingJoinPoint.proceed() 产生的结果即为  @AfterReturning 中的 result,可 debug 调试
     * @apiNote 3：由于@Around要提前获取到目的方法的执行结果，且@Around提前于@AfterThrowing运行，因此异常将在@Around中被捕获，从而导致@AfterThrowing捕获不到异常，因此@Around与@AfterThrowing混合使用
     **/
    @Around(value = "aspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        log.info("接口方法：{}" ,methodSignature.getDeclaringType().getCanonicalName()+"."+methodSignature.getMethod().getName());
        try {
            //判断是否有注解
            CustomVerify tokenAction = methodSignature.getMethod().getAnnotation(CustomVerify.class);
            if(tokenAction != null && tokenAction.value()){
                log.info("进入自定义AOP中···");
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if(attributes != null){
                    HttpServletRequest request = attributes.getRequest();

                    return proceedingJoinPoint.proceed();
                }
                return new ResponseBean<>().setErrMsg("获取请求失败");
            }else{
                return proceedingJoinPoint.proceed();
            }


        } catch (Exception e) {
            log.info("ControllerApi环绕AOP error ：{}", e);
            return new ResponseBean<>().setErrMsg(e.getMessage());
        }
    }
}
