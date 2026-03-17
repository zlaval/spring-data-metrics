package com.zlrx.dbperftest.config;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsFilterConfig {

    @Bean
    public MeterFilter excludeAnnotatedFilter(MetricsExclusionRegistry registry) {
        return new MeterFilter() {
            @Override
            public MeterFilterReply accept(Meter.Id id) {
                String className = id.getTag("repository");
                if (className == null) {
                    className = id.getTag("class");
                }
                String methodName = id.getTag("method");

                if (className != null && methodName != null) {
                    if (registry.isExcluded(className, methodName)) {
                        return MeterFilterReply.DENY;
                    }
                }

                return MeterFilterReply.NEUTRAL;
            }
        };
    }
}
