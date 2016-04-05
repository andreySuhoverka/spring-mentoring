package com.epam.sukhoverka.xmlconfig.beenpostprocessor;

import com.epam.sukhoverka.xmlconfig.annotation.BookReaderTracker;
import com.epam.sukhoverka.xmlconfig.bean.BookReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class BookReaderTrackerAnnotationBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap();


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() != BookReader.class) return bean;
        Class clazz = bean.getClass();
        for (Method m : clazz.getMethods()) {
            if (m.getAnnotation(BookReaderTracker.class) != null) {
                map.put(beanName, clazz);
                break;
            }
        }
        return bean;
    }


    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        if (map.containsKey(beanName)) {
            final Class clazz = map.get(beanName);
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), clazz.getInterfaces(), (proxy, method, args) -> {
                Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
                if (m.getAnnotation(BookReaderTracker.class) != null) {
                    System.out.println(clazz.getSimpleName() + "." + method.getName() + "() method invoked");
                }
                return method.invoke(bean, args);
            });
        }
        return bean;
    }

}

