package com.windsun.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/9/27 23:04
 */
@Configuration
public class ErrorMessageConfig {

    /**
     * 声明交换机
     * @return
     */
    @Bean
    public DirectExchange errorMessageExchange(){
        return new DirectExchange("error.direct");
    }

    /**
     * 声明队列；默认是持久化；以下几种都可以声明持久化队列
     * 1.new queue("queueName");
     * 2.new queue("queueName",true);
     * 3.QueueBuilder.durable("simple.queue").build();
     * @return
     */
    @Bean
    public Queue errorQueue(){
        return new Queue("error.queue");
    }

    /**
     * 绑定队列，交换机，ratingKey
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(errorQueue()).to(errorMessageExchange()).with("error");
    }

    /**
     * 实现将恢复的消息重新发布到指定的交换器
     * @param rabbitTemplate
     * @return
     */
    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate,"error.direct","error");
    }
}
