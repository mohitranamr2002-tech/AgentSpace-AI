package com.agentspace.ai.controller;

import com.agentspace.ai.dto.AIRequest;
import com.agentspace.ai.dto.ApiResponse;
import com.agentspace.ai.dto.DesignResponse;
import com.agentspace.ai.orchestrator.DesignOrchestrator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AIController {

    private final DesignOrchestrator designOrchestrator;

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<DesignResponse>> generateDesign(

            @Valid
            @RequestBody AIRequest request

    ) {

        DesignResponse response =
                designOrchestrator.generateDesign(
                        request.getPrompt()
                );

        return ResponseEntity.ok(

                ApiResponse.<DesignResponse>builder()

                        .success(true)

                        .message("Interior design generated successfully.")

                        .data(response)

                        .build()

        );

    }

}