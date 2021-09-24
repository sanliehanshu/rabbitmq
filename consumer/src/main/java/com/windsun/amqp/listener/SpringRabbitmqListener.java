package com.windsun.amqp.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @ClassName : SpringRabbitmqListener
 * @Description :
 * @Author : ws
 * @Date: 2021-09-24 10:41
 * @Version 1.0
 */
@Component
public class SpringRabbitmqListener {

    /*@RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue(String msg){
        System.out.println("消费者接收到的simple.queue的消息为："+msg);
    }*/

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到的simple.queue的消息为：" + msg + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接收到的simple.queue的消息为：" + msg + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String msg) {
        System.out.println("fanout.queue1的消息为：" + msg );
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue2(String msg) {
        System.out.println("fanout.queue2的消息为：" + msg);
    }

    /**
     * 使用注解的方式声明queue，routingKey,exchange及绑定关系
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenerDirectQueue1(String msg) {
        System.out.println("direct.queue1的消息为：" + msg);
    }

    /**
     * 使用注解的方式声明queue，routingKey,exchange及绑定关系 direct
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "direct.exchange",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenerDirectQueue2(String msg) {
        System.out.println("direct.queue2的消息为：" + msg);
    }

    /**
     * 使用注解的方式声明queue，routingKey,exchange及绑定关系 topic
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void listenerTopicQueue1(String msg) {
        System.out.println("topic.queue1的消息为：" + msg);
    }

    /**
     * 使用注解的方式声明queue，routingKey,exchange及绑定关系 topic
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listenerTopicQueue2(String msg) {
        System.out.println("topic.queue2的消息为：" + msg);
    }
}
