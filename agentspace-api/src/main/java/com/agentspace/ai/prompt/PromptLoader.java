package com.agentspace.ai.prompt;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class PromptLoader {

    private static final String PROMPT_FOLDER = "prompts/";

    public String loadPrompt(String fileName) {

        try {

            ClassPathResource resource =
                    new ClassPathResource(PROMPT_FOLDER + fileName);

            try (InputStream inputStream = resource.getInputStream()) {

                return new String(
                        inputStream.readAllBytes(),
                        StandardCharsets.UTF_8
                );

            }

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to load prompt : " + fileName,
                    ex
            );

        }

    }

}