package com.agentspace.ai.config;

import com.google.genai.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GeminiProperties.class)
@RequiredArgsConstructor
public class GeminiConfiguration {

    private final GeminiProperties properties;

    @Bean
    public Client geminiClient() {
        System.out.println("Gemini API Key = " + properties.getApiKey());

        return Client.builder()
                .apiKey(properties.getApiKey())
                .build();

    }

}