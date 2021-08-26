package com.windsun.wangs.helloworld;

/**
 * @ClassName : HelloCustomer
 * @Description :
 * @Author : ws
 * @Date: 2021-08-26 16:24
 * @Version 1.0
 */

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloCustomer {

    @RabbitHandler
    public void receive1(String message){
        System.err.println("message = " + message);
    }
}
