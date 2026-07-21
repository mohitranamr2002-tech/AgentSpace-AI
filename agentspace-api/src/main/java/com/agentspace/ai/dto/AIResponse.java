package com.agentspace.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIResponse {

    /**
     * AI Generated Content
     */
    private String content;

    /**
     * Model Name
     * Example:
     * gemini-2.5-flash
     */
    private String model;

}