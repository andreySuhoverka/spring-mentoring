package com.epam.sukhoverka.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Runner {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ContextConfig.class);
    }
}
