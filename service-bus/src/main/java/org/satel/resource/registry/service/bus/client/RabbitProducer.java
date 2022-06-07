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
public class RabbitProducer {
    RabbitTemplate rabbitTemplate;

    public void produce(Action action) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(action));
    }
}
