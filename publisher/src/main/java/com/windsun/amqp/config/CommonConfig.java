package com.windsun.amqp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : CommonConfig
 * @Description :定义ConfirmCallback
 * @Author : ws
 * @Date: 2021-09-26 11:12
 * @Version 1.0
 */
@Configuration
@Slf4j
public class CommonConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取rabbitmq对象
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        // 配置ReturnCallback
        /**
         * Returned message callback.
         *
         * @param message    the returned message.
         * @param replyCode  the reply code.
         * @param replyText  the reply text.
         * @param exchange   the exchange.
         * @param routingKey the routing key.
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            // 判断消息是否是延迟消息
            if (message.getMessageProperties().getReceivedDelay() > 0) {
                return;
            }
            // 记录日志
            log.error("消息发送到队列失败，响应码：{}，失败原因：{}，交换机：{}，路由key：{}，消息：{}"
                    , replyCode, replyText, exchange, routingKey, message.toString());
            // 如果有需要的话可以重发，或者有其他处理逻辑
        });


    }
}
