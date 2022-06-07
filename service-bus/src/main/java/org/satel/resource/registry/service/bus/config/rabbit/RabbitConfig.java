package org.satel.resource.registry.service.bus.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.satel.resource.registry.service.bus.config.rabbit.RabbitConstant.*;

@Slf4j
@EnableRabbit
@Configuration
public class RabbitConfig {
    @Value(DEFAULT_EXCHANGE)
    private String defaultExchange;

    @Value(TO_1C_ROUTING_KEY)
    private String to1CRoutingKey;

    @Value(TO_1C_QUEUE)
    private String to1CQueue;

    @Value(FROM_1C_ROUTING_KEY)
    private String from1CRoutingKey;

    @Value(FROM_1C_QUEUE)
    private String from1CQueue;

    @Value(TO_PROJECT_SERVER_ROUTING_KEY)
    private String toProjectServerRoutingKey;

    @Value(TO_PROJECT_SERVER_QUEUE)
    private String toProjectServerQueue;

    @Value(FROM_PROJECT_SERVER_ROUTING_KEY)
    private String fromProjectServerRoutingKey;

    @Value(FROM_PROJECT_SERVER_QUEUE)
    private String fromProjectServerQueue;

    @Value(RABBITMQ_HOST)
    String host;

    @Value(RABBITMQ_PORT)
    String port;

    @Value(RABBITMQ_USER_NAME)
    String username;

    @Value(RABBITMQ_PASSWORD)
    String password;

    @Value(RABBITMQ_VIRTUAL_HOST)
    String virtualHost;

    @Bean("to1CQueue")
    public Queue to1CQueue() {
        return new Queue("to1CQueue", false);
    }

    @Bean("from1CQueue")
    public Queue from1CQueue() {
        return new Queue("from1CQueue", false);
    }

    @Bean("toProjectServerQueue")
    public Queue toProjectServerQueue() {
        return new Queue("toProjectServerQueue", false);
    }

    @Bean("fromProjectServerQueue")
    public Queue fromProjectServerQueue() {
        return new Queue("fromProjectServerQueue", false);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, Integer.parseInt(port));
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(TO_1C_RABBIT_TEMPLATE)
    @Primary
    public RabbitTemplate rabbitTemplateTo1C() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(to1CQueue().getName());
        rabbitTemplate.setExchange(defaultExchange);
        rabbitTemplate.setRoutingKey(to1CRoutingKey);
        return rabbitTemplate;
    }

    @Bean(FROM_1C_RABBIT_TEMPLATE)
    public RabbitTemplate rabbitTemplateFrom1C() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(from1CQueue().getName());
        rabbitTemplate.setExchange(defaultExchange);
        rabbitTemplate.setRoutingKey(from1CRoutingKey);
        return rabbitTemplate;
    }

    @Bean(TO_PROJECT_SERVER_RABBIT_TEMPLATE)
    public RabbitTemplate rabbitTemplateToProjectServer() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(toProjectServerQueue().getName());
        rabbitTemplate.setExchange(defaultExchange);
        rabbitTemplate.setRoutingKey(toProjectServerRoutingKey);
        return rabbitTemplate;
    }

    @Bean(FROM_PROJECT_SERVER_RABBIT_TEMPLATE)
    public RabbitTemplate rabbitTemplateFromProjectServer() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(fromProjectServerQueue().getName());
        rabbitTemplate.setExchange(defaultExchange);
        rabbitTemplate.setRoutingKey(fromProjectServerRoutingKey);
        return rabbitTemplate;
    }
}
