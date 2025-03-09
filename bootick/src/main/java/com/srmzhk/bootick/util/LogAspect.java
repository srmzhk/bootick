package com.srmzhk.bootick.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("execution(* com.srmzhk.bootick..*(..)) && !execution(* com.srmzhk.bootick.repository..*(..))")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        // Получаем имя класса и метода
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Class: {} | Method: {} | Request: {}", className, methodName, Arrays.toString(args));

        // Выполняем метод
        Object result = joinPoint.proceed();

        log.info("Class: {} | Method: {} | Response: {}", className, methodName, result);
        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.srmzhk.bootick..*(..)) " +
            "&& !execution(* com.srmzhk.bootick.config..*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        log.error("Exception occurred in Class: {} | Method: {} | Exception: {}", className, methodName, exception.getMessage());

        // for stackTraceExceptions
        // log.error("Exception occurred in Class: {} | Method: {} | Exception: {}", className, methodName, exception.getMessage(), exception);
    }

    @Before("execution(* com.srmzhk.bootick.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: " + joinPoint.getSignature().getName());

        // Получаем параметры метода
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            StringBuilder sb = new StringBuilder("Arguments: ");
            for (Object arg : args) {
                sb.append(arg).append(" ");
            }
            log.info(sb.toString());
        }
    }

    @After("execution(* com.srmzhk.bootick.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Exiting method: " + joinPoint.getSignature().getName());
    }
}
