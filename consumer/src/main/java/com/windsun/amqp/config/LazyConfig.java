package com.windsun.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：wangsheng
 * @Description： 惰性队列
 * @Date：2021/10/7 10:56
 */
@Configuration
public class LazyConfig {

    /**
     * 声明惰性队列
     * @return
     */
    @Bean
    public Queue lazyQueue(){
        return QueueBuilder.durable("lazy.queue")
                .lazy()
                .build();
    }

    /**
     * 声明普通队列
     * @return
     */
    @Bean
    public Queue normalQueue(){
        return QueueBuilder.durable("normal.queue")
                .build();
    }
}
