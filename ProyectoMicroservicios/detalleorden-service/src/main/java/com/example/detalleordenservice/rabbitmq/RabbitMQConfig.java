package com.example.detalleordenservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUECREATE = "detalleordencreate.queue";

    @Bean
    public Queue createdetalleQueue() {
        return new Queue(QUEUECREATE, false);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    // === Producto ===
    public static final String PRODUCTOUPDATE_QUEUE = "productoupdate.queue";
    public static final String PRODUCTOUPDATE_EXCHANGE = "productoupdate.exchange";
    public static final String PRODUCTOUPDATE_ROUTING_KEY = "productoupdate.routingkey";

    @Bean
    public Queue productoUpdateQueue() {
        return new Queue(PRODUCTOUPDATE_QUEUE, false);
    }

    @Bean
    public DirectExchange productoUpdateExchange() {
        return new DirectExchange(PRODUCTOUPDATE_EXCHANGE);
    }

    @Bean
    public Binding productoupdatebinding(Queue productoUpdateQueue, DirectExchange productoUpdateExchange) {
        return BindingBuilder.bind(productoUpdateQueue).to(productoUpdateExchange).with(PRODUCTOUPDATE_ROUTING_KEY);
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
