package com.agentspace.ai.service;

import com.agentspace.ai.config.GeminiProperties;
import com.agentspace.ai.dto.AIResponse;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiAIService implements AIService {

    private final Client client;
    private final GeminiProperties properties;

    @Override
    public AIResponse generateResponse(String prompt) {

        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                            properties.getModel(),
                            prompt,
                            null
                    );

            return AIResponse.builder()
                    .content(response.text())
                    .model(properties.getModel())
                    .build();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Unable to generate AI response",
                    ex
            );

        }

    }

}