package com.windsun.amqp.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：wangsheng
 * @Description：声明绑定队列和交换机，队列再绑定死信交换机
 * @Date：2021/9/29 23:06
 */
@Configuration
public class TTLMessageConfig {

    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl.direct");
    }

    @Bean
    public Queue ttlQueue(){
        return QueueBuilder
                .durable("ttl.queue")
                .ttl(1000)
                .deadLetterExchange("dl.direct")
                .deadLetterRoutingKey("dl")
                .build();
    }

    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with("ttl");
    }

}
