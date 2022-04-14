package com.ep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/*
 * 引导类，springboot的项目入口
 * */
@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {

        SpringApplication.run(LoginApplication.class, args);
    }

}
