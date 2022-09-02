package com.example.demo.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
public class DefaultListener {

    @RabbitHandler
    public void recevieMessage(LinkedHashMap message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            System.out.println(message);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
