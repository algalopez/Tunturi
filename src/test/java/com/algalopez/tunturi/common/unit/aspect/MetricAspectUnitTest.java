package com.algalopez.tunturi.common.unit.aspect;

import com.algalopez.tunturi.common.annotation.Metric;
import com.algalopez.tunturi.common.aspect.MetricAspect;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MetricAspectUnitTest {

    private static final String SERVICE_NAME = "tunturi";
    private static final String SERVICE_ID = "mad01";
    private static final String TASK = "echo";
    private static final String METRICS_NAME = SERVICE_NAME + "." + SERVICE_ID + "." + TASK;

    @Mock
    private MetricRegistry metricRegistry;

    @Mock
    private Timer timer;

    @Mock
    private Timer.Context timerContext;

    @Mock
    private Metric metricAnnotation;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    private MetricAspect metricAspect;

    @Test
    public void testTrace_serviceExecutedWithTiming() throws Throwable {
        metricAspect = new MetricAspect(metricRegistry, SERVICE_NAME, SERVICE_ID);
        when(metricRegistry.timer(METRICS_NAME)).thenReturn(timer);
        when(timer.time()).thenReturn(timerContext);
        when(metricAnnotation.value()).thenReturn(TASK);

        metricAspect.sendMetrics(proceedingJoinPoint);

        InOrder orderVerifier = Mockito.inOrder(timer, proceedingJoinPoint, timerContext);
        orderVerifier.verify(timer).time();
        orderVerifier.verify(proceedingJoinPoint).proceed();
        orderVerifier.verify(timerContext).stop();
    }

    @Test(expected = RuntimeException.class)
    public void testTrace_ServiceExecutedWithTiming_EvenWhenExceptionThrown() throws Throwable {
        metricAspect = new MetricAspect(metricRegistry, SERVICE_NAME, SERVICE_ID);
        when(metricRegistry.timer(METRICS_NAME)).thenReturn(timer);
        when(timer.time()).thenReturn(timerContext);
        when(metricAnnotation.value()).thenReturn(TASK);
        doThrow(new RuntimeException("ForcedError")).when(proceedingJoinPoint).proceed();

        metricAspect.sendMetrics(proceedingJoinPoint);

        InOrder orderVerifier = Mockito.inOrder(timer, proceedingJoinPoint, timerContext);
        orderVerifier.verify(timer).time();
        orderVerifier.verify(proceedingJoinPoint).proceed();
        orderVerifier.verify(timerContext).stop();
    }

}
