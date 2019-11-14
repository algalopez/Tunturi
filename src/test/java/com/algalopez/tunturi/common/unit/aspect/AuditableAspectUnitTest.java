package com.algalopez.tunturi.common.unit.aspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.algalopez.tunturi.common.aspect.AuditableAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuditableAspectUnitTest {

    @Mock
    private Appender<ILoggingEvent> appender;

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private Signature signature;

    @Captor
    private ArgumentCaptor<ILoggingEvent> captor;

    private AuditableAspect auditableAspect;

    @Before
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(AuditableAspect.class);
        logger.addAppender(appender);

        this.auditableAspect = new AuditableAspect();
    }

    @Test
    public void testAuditableAspect() {

        when(joinPoint.getArgs()).thenReturn(new Object[]{"arg1", "arg2"});
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("methodName");

        auditableAspect.logServiceCall(joinPoint);

        verify(appender, times(1)).doAppend(captor.capture());

        List<ILoggingEvent> iLoggingEvents = captor.getAllValues();
        assertEquals(1, iLoggingEvents.size());
        ILoggingEvent iLoggingEvent = iLoggingEvents.get(0);
        assertEquals(Level.INFO, iLoggingEvent.getLevel());
        assertEquals("methodName(arg1,arg2)", iLoggingEvent.getFormattedMessage());
    }
}
