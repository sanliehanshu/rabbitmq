package com.windsun.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : FanoutConfig
 * @Description :
 * @Author : ws
 * @Date: 2021-09-24 15:09
 * @Version 1.0
 */
@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange createFanoutExchange(){
        return new FanoutExchange("fanout.exchange");
    }

    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定队列和交换机
     * @param fanoutExchange
     * @param fanoutQueue1
     * @return
     */
    @Bean
    public Binding bingQueue1(FanoutExchange fanoutExchange, Queue fanoutQueue1){
        return  BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    /**
     * 绑定队列和交换机
     * @param fanoutExchange
     * @param fanoutQueue2
     * @return
     */
    @Bean
    public Binding bingQueue2(FanoutExchange fanoutExchange,Queue fanoutQueue2){
        return  BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    /**
     * 声明object的队列，测试发消息的类型
     * @return
     */
    @Bean
    public Queue objectQueue(){
        return new Queue("object.queue");
    }

    /**
     * 使用Jackson序列化对象
     * @return
     */
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
