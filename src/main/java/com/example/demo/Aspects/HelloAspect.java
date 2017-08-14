package com.example.demo.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Aspect
@Component
public class HelloAspect {
    private Logger logger=Logger.getLogger(getClass().getName());
    //定义切入点
    @Pointcut("execution(public * com.example.demo.*.*(..))")
    public void webLog(){}
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest=servletRequestAttributes.getRequest();
        // 记录下请求内容
//        logger.info("URL : " + httpServletRequest.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + httpServletRequest.getMethod());
//        logger.info("IP : " + httpServletRequest.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        throw new Exception("触发AOP:Before");
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
        throw new Exception("触发AOP:AfterReturning");
    }
}
