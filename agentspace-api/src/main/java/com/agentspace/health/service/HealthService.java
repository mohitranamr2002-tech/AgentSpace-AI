package com.agentspace.health.service;

import com.agentspace.health.dto.HealthResponse;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public HealthResponse getHealth() {

        return new HealthResponse(
                "AgentSpace AI",
                "1.0.0",
                "UP"
        );

    }

}