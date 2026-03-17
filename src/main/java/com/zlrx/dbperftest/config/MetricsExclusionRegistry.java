package com.zlrx.dbperftest.config;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MetricsExclusionRegistry {

    private final Set<String> excludedKeys = ConcurrentHashMap.newKeySet();

    public void addExclusion(String simpleClassName, String methodName) {
        excludedKeys.add(simpleClassName + "." + methodName);
    }

    public boolean isExcluded(String simpleClassName, String methodName) {
        return excludedKeys.contains(simpleClassName + "." + methodName);
    }
}