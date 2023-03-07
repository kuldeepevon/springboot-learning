package com.learning.sample.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;

public class HelloWorld {

    @PostConstruct
    public void init()
            throws Exception
    {
        System.out.println(
                "Bean HelloWorld has been "
                        + "instantiated and I'm the "
                        + "init() method");
    }


    @PreDestroy
    public void close() throws Exception
    {
        System.out.println(
                "Container has been closed "
                        + "and I'm the destroy() method");
    }
}
