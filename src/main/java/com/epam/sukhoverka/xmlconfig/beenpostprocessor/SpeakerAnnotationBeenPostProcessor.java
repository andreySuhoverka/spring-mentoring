package com.epam.sukhoverka.xmlconfig.beenpostprocessor;

import com.epam.sukhoverka.xmlconfig.annotation.Speaker;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class SpeakerAnnotationBeenPostProcessor implements BeanPostProcessor{
    private Map<String, Class> map = new HashMap();

    public Object postProcessBeforeInitialization(Object bean, String beanName){
        Class clazz = bean.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(method.getAnnotation(Speaker.class) != null){
                map.put(beanName, clazz);
                break;
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(final Object bean, String beanName){
        final Class clazz = map.get(beanName);
        if(map.containsKey(beanName)){
            return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), (proxy, method, args) -> {
                Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
                if(m.getAnnotation(Speaker.class) != null){
                    System.out.println("@Speaker applied to " + clazz.getSimpleName() + "." + m.getName() + "() method");
                }
                return method.invoke(bean, args);
            });

        }
        return bean;
    }
}
