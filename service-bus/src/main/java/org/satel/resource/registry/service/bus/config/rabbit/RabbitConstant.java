package org.satel.resource.registry.service.bus.config.rabbit;

public class RabbitConstant {
    public static final String DEFAULT_EXCHANGE = "${spring.rabbitmq.template.exchange}";
    public static final String TO_1C_ROUTING_KEY = "${spring.rabbitmq.to1CRoutingKey}";
    public static final String TO_1C_QUEUE = "${spring.rabbitmq.to1CQueue}";
    public static final String FROM_1C_ROUTING_KEY = "${spring.rabbitmq.from1CRoutingKey}";
    public static final String FROM_1C_QUEUE = "${spring.rabbitmq.from1CQueue}";
    public static final String TO_PROJECT_SERVER_ROUTING_KEY = "${spring.rabbitmq.toProjectServerRoutingKey}";
    public static final String TO_PROJECT_SERVER_QUEUE = "${spring.rabbitmq.toProjectServerQueue}";
    public static final String FROM_PROJECT_SERVER_ROUTING_KEY = "${spring.rabbitmq.fromProjectServerRoutingKey}";
    public static final String FROM_PROJECT_SERVER_QUEUE = "${spring.rabbitmq.fromProjectServerQueue}";
    public static final String RABBITMQ_HOST = "${spring.rabbitmq.host}";
    public static final String RABBITMQ_PORT = "${spring.rabbitmq.port}";
    public static final String RABBITMQ_USER_NAME = "${spring.rabbitmq.username}";
    public static final String RABBITMQ_PASSWORD = "${spring.rabbitmq.password}";
    public static final String RABBITMQ_VIRTUAL_HOST = "${spring.rabbitmq.virtual-host}";
    public static final String TO_1C_RABBIT_TEMPLATE = "rabbitTemplateTo1C";
    public static final String FROM_1C_RABBIT_TEMPLATE = "rabbitTemplateFrom1C";
    public static final String TO_PROJECT_SERVER_RABBIT_TEMPLATE = "rabbitTemplateToProjectServer";
    public static final String FROM_PROJECT_SERVER_RABBIT_TEMPLATE = "rabbitTemplateFromProjectServer";

    private RabbitConstant() {
    }
}
