package com.windsun.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : ConsumerApplication
 * @Description :
 * @Author : ws
 * @Date: 2021-09-24 10:39
 * @Version 1.0
 */
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
