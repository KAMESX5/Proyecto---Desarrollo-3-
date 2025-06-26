package com.example.detalleordenservice.rabbitmq;

import com.example.detalleordenservice.RequestProducto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private final AmqpTemplate amqpTemplate;

    public RabbitMQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void enviarUpdateProducto(RequestProducto requestProducto)
    {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.PRODUCTOUPDATE_EXCHANGE,
                RabbitMQConfig.PRODUCTOUPDATE_ROUTING_KEY,
                requestProducto
        );
    }

}
