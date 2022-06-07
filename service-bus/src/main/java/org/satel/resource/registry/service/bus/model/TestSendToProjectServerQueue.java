package org.satel.resource.registry.service.bus.model;


import lombok.Data;

import java.util.UUID;

@Data
public class TestSendToProjectServerQueue implements Action {
    private UUID uuid;
    private String body;
}
