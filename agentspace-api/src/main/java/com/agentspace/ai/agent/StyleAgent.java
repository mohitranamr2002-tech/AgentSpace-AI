package com.agentspace.ai.agent;

import com.agentspace.ai.dto.AIResponse;
import com.agentspace.ai.prompt.PromptLoader;
import com.agentspace.ai.prompt.PromptTemplateService;
import com.agentspace.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StyleAgent {

    private final AIService aiService;
    private final PromptLoader promptLoader;
    private final PromptTemplateService templateService;

    public AIResponse suggestStyle(
            String roomDescription,
            String plannerResult
    ) {

        String template =
                promptLoader.loadPrompt("style.md");

        String prompt =
                templateService.replaceVariables(
                        template,
                        Map.of(
                                "ROOM_DESCRIPTION", roomDescription,
                                "PLANNER", plannerResult
                        )
                );

        return aiService.generateResponse(prompt);

    }

}