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
public class ReportAgent {

    private final AIService aiService;
    private final PromptLoader promptLoader;
    private final PromptTemplateService templateService;

    public AIResponse generateReport(
            String planner,
            String style,
            String budget
    ) {

        String template =
                promptLoader.loadPrompt("report.md");

        String prompt =
                templateService.replaceVariables(
                        template,
                        Map.of(
                                "PLANNER", planner,
                                "STYLE", style,
                                "BUDGET", budget
                        )
                );

        return aiService.generateResponse(prompt);

    }

}