package org.satel.resource.registry.service.bus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.satel.resource.registry.service.bus.client.RabbitConsumer;
import org.satel.resource.registry.service.bus.client.RabbitProducer;
import org.satel.resource.registry.service.bus.config.rabbit.RabbitConfig;
import org.satel.resource.registry.service.bus.model.TestGetFrom1CQueue;
import org.satel.resource.registry.service.bus.model.TestGetFromProjectServerQueue;
import org.satel.resource.registry.service.bus.model.TestSendTo1CQueue;
import org.satel.resource.registry.service.bus.model.TestSendToProjectServerQueue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api
@AllArgsConstructor
public class ApiController {
    private RabbitConfig rabbitConfig;

    private RabbitProducer rabbitProducer;
    private RabbitConsumer rabbitConsumer;

    @GetMapping("testGetFrom1CQueue")
    @ApiOperation("testGetFrom1CQueue")
    public ResponseEntity<?> testGetFrom1CQueue() throws JsonProcessingException {
        rabbitConsumer.setRabbitTemplate(rabbitConfig.rabbitTemplateFrom1C());
        TestGetFrom1CQueue message = (TestGetFrom1CQueue)rabbitConsumer.processMessage(TestGetFrom1CQueue.class);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("testGetFromProjectServerQueue")
    @ApiOperation("testGetFromProjectServerQueue")
    public ResponseEntity<?> testGetFromProjectServerQueue() throws JsonProcessingException {
        rabbitConsumer.setRabbitTemplate(rabbitConfig.rabbitTemplateFromProjectServer());
        TestGetFromProjectServerQueue message = (TestGetFromProjectServerQueue) rabbitConsumer.processMessage(TestGetFromProjectServerQueue.class);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("testSendTo1CQueue")
    @ApiOperation("testSendTo1CQueue")
    public ResponseEntity<?> testSendTo1CQueue(TestSendTo1CQueue testSendTo1CQueue) throws JsonProcessingException {
        rabbitProducer.produce(testSendTo1CQueue);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("testSendToProjectServerQueue")
    @ApiOperation("testSendToProjectServerQueue")
    public ResponseEntity<?> testSendToProjectServerQueue(TestSendToProjectServerQueue testSendToProjectServerQueue) throws JsonProcessingException {
        rabbitProducer.produce(testSendToProjectServerQueue);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
