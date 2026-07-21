package com.agentspace.ai.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    /**
     * Generated image path or URL
     */
    private String imageUrl;

    /**
     * Prompt used for generation
     */
    private String prompt;

}