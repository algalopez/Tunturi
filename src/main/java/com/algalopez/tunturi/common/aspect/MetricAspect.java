package com.algalopez.tunturi.common.aspect;

import com.algalopez.tunturi.common.annotation.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@EnableMetrics
@Slf4j
@ConditionalOnProperty(prefix = "metrics.graphite", name = "enabled", havingValue = "true")
public class MetricAspect {

    private static final String SEPARATOR = ".";
    private final MetricRegistry metricRegistry;
    private final String serviceName;
    private final String serviceId;

    public MetricAspect(MetricRegistry metricRegistry,
                        @Value("${service.name}") String serviceName,
                        @Value("${service.id}") String serviceId) {
        this.metricRegistry = metricRegistry;
        this.serviceName = serviceName;
        this.serviceId = serviceId;
    }

    @Around("@annotation(com.algalopez.tunturi.common.annotation.Metric)")
    public Object sendMetrics(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String metricMethod = method.getAnnotation(Metric.class).value();

        String metricName = String.join(SEPARATOR, Arrays.asList(serviceName, serviceId, metricMethod));

        final Timer.Context context = metricRegistry.timer(metricName).time();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            context.stop();
            log.info("Sending metrics: {}", metricName);
        }
    }
}
