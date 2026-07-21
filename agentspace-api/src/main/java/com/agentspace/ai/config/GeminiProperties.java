package com.agentspace.ai.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gemini")
public class GeminiProperties {

    /**
     * Gemini API Key
     */
    private String apiKey;

    /**
     * Gemini Model
     * Example:
     * gemini-2.5-flash
     */
    private String model;

}