package com.hussar.dc.scs.frm.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.hussar.crm"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}