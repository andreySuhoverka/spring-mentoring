package com.epam.sukhoverka.xmlconfig;

import com.epam.sukhoverka.xmlconfig.bean.IBookReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
        IBookReader bookReader = ctx.getBean("bookReader", IBookReader.class);
        ctx.getBean("bookReader", IBookReader.class);
        bookReader.sayBookTitle();
        bookReader.sayBookAuthor();

    }
}
