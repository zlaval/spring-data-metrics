package com.zlrx.dbperftest.config;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

@Component
public class MetricsExclusionScanner implements BeanPostProcessor {

    private final MetricsExclusionRegistry registry;

    public MetricsExclusionScanner(MetricsExclusionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            Class<?>[] interfaces = ClassUtils.getAllInterfaces(bean);
            for (Class<?> iface : interfaces) {
                if (iface.getName().startsWith("com.zlrx.")) {
                    inspectClass(iface);
                }
            }

            Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
            if (targetClass != null &&!targetClass.getName().startsWith("com.zlrx.")){
                inspectClass(targetClass);
            }

        } catch (Exception e) {
        }

        return bean;
    }

    private void inspectClass(Class<?> clazz) {
        for (Method method : clazz.getMethods()) {
            if (AnnotationUtils.findAnnotation(method, ExcludeFromMetrics.class) != null) {
                registry.addExclusion(clazz.getSimpleName(), method.getName());
            }
        }
    }
}