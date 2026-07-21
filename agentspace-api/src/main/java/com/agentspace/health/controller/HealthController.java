package com.agentspace.health.controller;

import com.agentspace.ai.dto.ApiResponse;
import com.agentspace.health.dto.HealthResponse;
import com.agentspace.health.service.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<HealthResponse>> health() {

        return ResponseEntity.ok(

                ApiResponse.<HealthResponse>builder()

                        .success(true)

                        .message("AgentSpace API is running successfully")

                        .data(healthService.getHealth())

                        .build()

        );

    }

}