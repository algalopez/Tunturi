package com.algalopez.tunturi.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class AuditableAspect {

    @Before("execution(public * com.algalopez.tunturi..*Service*.*(..))")
    public void logServiceCall(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String arguments = Stream.of(args).map(Object::toString).collect(Collectors.joining(","));
        String signatureName = joinPoint.getSignature().getName();

        log.info("{}({})", signatureName, arguments);
    }
}
