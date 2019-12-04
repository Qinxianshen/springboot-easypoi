package com.qin.easypoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.qin"})
public class EasypoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypoiApplication.class, args);
    }

}
