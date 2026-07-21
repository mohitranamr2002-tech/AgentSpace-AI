package com.agentspace.ai.config;

import com.google.genai.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GeminiConfiguration {

    private final GeminiProperties properties;

    @Bean
    public Client geminiClient() {

        return Client.builder()
                .apiKey(properties.getApiKey())
                .build();

    }

}