package com.example.ordenservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // === Detalle Orden ===
    public static final String DETALLECREATE_QUEUE = "detalleordencreate.queue";
    public static final String DETALLECREATE_EXCHANGE = "detalleordencreate.exchange";
    public static final String DETALLECREATE_ROUTING_KEY = "detalleordencreate.routingkey";

    @Bean
    public Queue detalleCreateQueue() {
        return new Queue(DETALLECREATE_QUEUE, false);
    }

    @Bean
    public DirectExchange detalleCreateExchange() {
        return new DirectExchange(DETALLECREATE_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue detalleCreateQueue, DirectExchange detalleCreateExchange) {
        return BindingBuilder.bind(detalleCreateQueue).to(detalleCreateExchange).with(DETALLECREATE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
