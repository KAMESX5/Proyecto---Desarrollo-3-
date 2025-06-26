package com.example.ordenservice.rabbitmq;

import com.example.ordenservice.RequestOrden;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private final AmqpTemplate amqpTemplate;

    public RabbitMQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void enviarCreateDetalle(RequestOrden request) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.DETALLECREATE_EXCHANGE,
                RabbitMQConfig.DETALLECREATE_ROUTING_KEY,
                request
        );
    }

}
