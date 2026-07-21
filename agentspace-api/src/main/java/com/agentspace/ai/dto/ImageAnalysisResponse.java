package com.agentspace.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageAnalysisResponse {

    /**
     * AI generated room analysis
     */
    private String analysis;

}