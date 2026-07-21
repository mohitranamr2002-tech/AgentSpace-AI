package com.agentspace.ai.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FluxConfig {

    @Value("${ai.huggingface.api-key}")
    private String apiKey;

    @Value("${ai.huggingface.image-model}")
    private String imageModel;

}