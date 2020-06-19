package com.smartosc.team5.aop;

import com.smartosc.team5.entities.ApiLog;
import com.smartosc.team5.services.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 11/06/2020 - 04:14 PM
 * @created_by ThaoPhuong
 * @since 11/06/2020
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Autowired
    private ApiLogService apiLogService;

    @Pointcut("within(com.smartosc.team5..*)")
    public void service() {
    }

    @Around("service()")
    public Object aroundServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }

    @AfterThrowing(pointcut = "service()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) throws IllegalAccessException {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        log.error("Exception in {}() with message = {}", codeSignature.getDeclaringTypeName(), ex.getMessage());
        ApiLog apiLog = new ApiLog();
        apiLog.setCalledTime(Calendar.getInstance().getTime());
        apiLog.setErrorMessage(ex.getMessage());
        apiLog.setRetryNum(1);
        List<String> args = new ArrayList<>();
        String[] argNames = codeSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        for (int i = 0; i < argNames.length; i++) {
            args.add(argNames[i] + ":" + argValues[i].toString());
        }
        apiLog.setData(String.join(", ", args));
        apiLogService.saveApiLog(apiLog);
        log.error(String.join(", ", args));
    }
}
