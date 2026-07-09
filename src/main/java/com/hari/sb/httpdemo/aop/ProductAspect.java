package com.hari.sb.httpdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Aspect // this annotation only tells the core AspectJ compiler what the class is it wont create bean
@Component // this is needed if we need for this class
@Slf4j
public class ProductAspect {
    private final ObjectMapper objectMapper;

    public ProductAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Before("execution(* com.hari.sb.httpdemo..*(..))") // double .. are needed to keep logs in nestead packages
    public void logRequest(JoinPoint joinPoint) {
        try {
            log.info("Before advice - class name {} , method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
            log.info("Before advice - RequestBody {} ", objectMapper.writeValueAsString(joinPoint.getArgs()));
        } catch (Exception e) {
            log.warn("Could not serialize arguments to JSON", e);
        }
    }

    @AfterReturning(value = "execution(* com.hari.sb.httpdemo..*(..))", returning = "response") // double .. are needed to keep logs in nestead packages
    public void logResponse(JoinPoint joinPoint, Object response) {
        try {
            log.info("After advice - class name {} , method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
            log.info("After advice - ResponseBody {} ", objectMapper.writeValueAsString(response));
        } catch (Exception e) {
            log.warn("Could not serialize arguments to JSON", e);
        }
    }
}
