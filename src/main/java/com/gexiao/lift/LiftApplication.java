package com.gexiao.lift;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gexiao.lift.dao")
public class LiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiftApplication.class, args);
    }

}
