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
public class BudgetAgent {

    private final AIService aiService;
    private final PromptLoader promptLoader;
    private final PromptTemplateService templateService;

    public AIResponse estimateBudget(
            String roomDescription,
            String plannerResult,
            String styleResult
    ) {

        String template =
                promptLoader.loadPrompt("budget.md");

        String prompt =
                templateService.replaceVariables(
                        template,
                        Map.of(
                                "ROOM_DESCRIPTION", roomDescription,
                                "PLANNER", plannerResult,
                                "STYLE", styleResult
                        )
                );

        return aiService.generateResponse(prompt);

    }

}