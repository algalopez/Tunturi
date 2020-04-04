package com.algalopez.tunturi.config;

import com.codahale.metrics.MetricAttribute;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jmx.JmxReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@EnableMetrics
@Component
@ConditionalOnProperty(prefix = "metrics.graphite", name = "enabled", havingValue = "true")
public class MetricsConfig extends MetricsConfigurerAdapter {

    private final String host;
    private final int port;
    private final long period;
    private final String prefix;
    private final List<String> enabledMetrics;

    public MetricsConfig(@Value("${metrics.graphite.hostname}") String host,
                         @Value("${metrics.graphite.port}") int port,
                         @Value("${metrics.graphite.prefix}") String prefix,
                         @Value("${metrics.graphite.time.between.polls}") int period,
                         @Value("#{'${metrics.graphite.filter:}'.split(',')}") List<String> enabledMetrics) {

        this.host = host;
        this.port = port;
        this.prefix = prefix;
        this.period = period;
        this.enabledMetrics = enabledMetrics;
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        registerReporter(getJmxReporter(metricRegistry)).start();
        registerReporter(getGraphiteReporter(metricRegistry)).start(period, TimeUnit.MILLISECONDS);
    }

    private JmxReporter getJmxReporter(MetricRegistry metricRegistry) {
        return JmxReporter.forRegistry(metricRegistry).build();
    }

    private GraphiteReporter getGraphiteReporter(MetricRegistry metricRegistry) {
        Graphite graphite = new Graphite(new InetSocketAddress(host, port));
        return GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith(prefix)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .disabledMetricAttributes(getExcludedMetrics())
                .filter(MetricFilter.ALL)
                .build(graphite);
    }

    private Set<MetricAttribute> getExcludedMetrics() {
        Set<MetricAttribute> excludedMetrics = new HashSet<>();
        for (MetricAttribute metricAttribute : MetricAttribute.values()) {
            if (!enabledMetrics.contains(metricAttribute.getCode())) {
                excludedMetrics.add(metricAttribute);
            }
        }
        return excludedMetrics;
    }
}
