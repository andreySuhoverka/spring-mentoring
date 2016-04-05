package com.epam.sukhoverka.javaconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@Component
public class SimpleBean {
    @Value("${javaconfig}")
    private String javaconfig;

    @PostConstruct
    void alertConfig() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println(javaconfig);
    }
}
