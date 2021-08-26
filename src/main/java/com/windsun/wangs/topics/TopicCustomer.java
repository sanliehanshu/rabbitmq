package com.windsun.wangs.topics;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author：wangsheng
 * @Description： topic  # 零个或多个单词；* 一个单词
 * @Date：2021/8/26 21:58
 */
@Component
public class TopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 不指定名称代表创建临时队列
                    exchange = @Exchange(type = "topic",name = "topics"),// 绑定的交换机
                    key = {"user.save","user.*"}
            )
    })
    public void receive1(String message){
        System.err.println("message1: "+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 不指定名称代表创建临时队列
                    exchange = @Exchange(type = "topic",name = "topics"),// 绑定的交换机
                    key = {"order.#","product.#"}
            )
    })
    public void receive2(String message){
        System.err.println("message2: "+message);
    }
}
