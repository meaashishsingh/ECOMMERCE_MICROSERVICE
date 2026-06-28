package com.ecommerce.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {


    @Value("${rabbitmq.queue.name}")
    private String queueName;


    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;


    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    // Creates Queue
    @Bean
    public Queue orderQueue() {

        return QueueBuilder.durable(queueName).build();
    }


    // Creates Topic Exchange
    @Bean
    public TopicExchange orderExchange() {

        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }


    // Creates Binding
    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange orderExchange) {

        return BindingBuilder.bind(orderQueue).to(orderExchange).with(routingKey);
    }


    // Automatically creates queue/exchange/binding in RabbitMQ
    @Bean
    public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin= new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);

        return new RabbitAdmin(connectionFactory);
    }


    // Java Object <--> JSON converter
    @Bean
    public MessageConverter jacksonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }


    // Producer
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jacksonMessageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);


        rabbitTemplate.setMessageConverter(jacksonMessageConverter);


        rabbitTemplate.setExchange(exchangeName);


        return rabbitTemplate;
    }

}