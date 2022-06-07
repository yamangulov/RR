package org.satel.resource.registry.service.bus.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.satel.resource.registry.service.bus.model.Action;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RabbitConsumer {
    RabbitTemplate rabbitTemplate;

    private String receiveMessage() {
        return (String) rabbitTemplate.receiveAndConvert();
    }

    public Action processMessage(Class<? extends Action> cls) throws JsonProcessingException {
        String message = receiveMessage();
        //TODO добавить обработку null message
        return new ObjectMapper().readValue(message, cls);
    }
}
