package com.agentspace.ai.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageGenerationRequest {

    private String prompt;

}