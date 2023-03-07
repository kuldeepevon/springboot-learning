package com.learning.sample.services;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckBn {
//        implements InitializingBean,        DisposableBean {
//    @Override
//    public void afterPropertiesSet()
//            throws Exception
//    {
//        System.out.println(
//                "Bean HelloWorld has been "
//                        + "instantiated and I'm the "
//                        + "init() method");
//    }

//    @Bean(initMethod="init", destroyMethod = "")
    @Bean
    public HelloWorld sayHello() {
        return new HelloWorld();
    }
//    @Override
//    // This method is invoked
//    // just after the container
//    // is closed
//    public void destroy() throws Exception
//    {
//        System.out.println(
//                "Container has been closed "
//                        + "and I'm the destroy() method");
//    }
}
