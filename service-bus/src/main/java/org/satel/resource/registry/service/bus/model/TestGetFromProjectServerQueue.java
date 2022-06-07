package org.satel.resource.registry.service.bus.model;


import lombok.Data;

import java.util.UUID;

@Data
public class TestGetFromProjectServerQueue implements Action {
    private UUID uuid;
    private String body;
}