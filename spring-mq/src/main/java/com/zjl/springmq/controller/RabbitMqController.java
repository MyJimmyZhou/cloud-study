package com.zjl.springmq.controller;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitmq")
public class RabbitMqController {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @RequestMapping(value = "/declare", method = RequestMethod.GET)
    public boolean declareQueue() {
        rabbitAdmin.declareExchange(new TopicExchange("test.topic.exchange", false, false, null));
        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout.exchange", false, false, null));
        rabbitAdmin.declareExchange(new DirectExchange("test.direct.exchange", false, false, null));

        rabbitAdmin.declareQueue(new Queue("topic.queue", false, false, false, null));
        rabbitAdmin.declareQueue(new Queue("fanout.queue", false, false, false, null));
        rabbitAdmin.declareQueue(new Queue("direct.queue", false, false, false, null));

        rabbitAdmin.declareBinding(new Binding("fanout.queue", Binding.DestinationType.QUEUE
                , "test.fanout.exchange", "", null));

        rabbitAdmin.declareBinding(new Binding(
                "test.fanout.exchange",//目标：交换机名
                Binding.DestinationType.EXCHANGE, //绑定目标类型：交换机
                "test.topic.exchange", //发起绑定的交换机
                "test", //路由key
                null));

        //发送消息
        //正常的消息流转 从test.direct.exchange-》direct.queue
        rabbitAdmin.getRabbitTemplate().convertAndSend("test.direct.exchange", "direct.key", "直连交换机消息111");
        //消息先到test.topic.exchange-》test.fanout.exchange-》fanout.queue
        rabbitAdmin.getRabbitTemplate().convertAndSend("test.topic.exchange", "test", "多级流转消息2222");
        return true;
    }
}
