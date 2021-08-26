package com.windsun.wangs.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/8/26 21:58
 */
@Component
public class DirectCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 不指定名称代表创建临时队列
                    exchange = @Exchange(value = "directs",type = "direct"),// 绑定的交换机
                    key = {"info"}
            )
    })
    public void receive1(String message){
        System.err.println("message1: "+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 不指定名称代表创建临时队列
                    exchange = @Exchange(value = "directs",type = "direct"),// 绑定的交换机
                    key = {"error"}
            )
    })
    public void receive2(String message){
        System.err.println("message2: "+message);
    }
}
