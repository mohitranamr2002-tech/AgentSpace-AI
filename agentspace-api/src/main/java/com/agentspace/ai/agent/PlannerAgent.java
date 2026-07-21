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
public class PlannerAgent {

    private final AIService aiService;
    private final PromptLoader promptLoader;
    private final PromptTemplateService templateService;

    public AIResponse createPlan(String roomDescription) {

        String template = promptLoader.loadPrompt("planner.md");

        String prompt = templateService.replaceVariables(
                template,
                Map.of(
                        "ROOM_DESCRIPTION", roomDescription
                )
        );

        return aiService.generateResponse(prompt);

    }

}