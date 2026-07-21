package com.agentspace.ai.orchestrator;

import com.agentspace.ai.agent.BudgetAgent;
import com.agentspace.ai.agent.PlannerAgent;
import com.agentspace.ai.agent.ReportAgent;
import com.agentspace.ai.agent.StyleAgent;
import com.agentspace.ai.dto.AIResponse;
import com.agentspace.ai.dto.DesignResponse;
import com.agentspace.ai.image.ImageGeneratorAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DesignOrchestrator {

    private final PlannerAgent plannerAgent;

    private final StyleAgent styleAgent;

    private final BudgetAgent budgetAgent;

    private final ReportAgent reportAgent;

    private final ImageGeneratorAgent imageGeneratorAgent;

    public DesignResponse generateDesign(String roomDescription) {

        AIResponse plannerResponse =
                plannerAgent.createPlan(roomDescription);

        AIResponse styleResponse =
                styleAgent.suggestStyle(
                        roomDescription,
                        plannerResponse.getContent()
                );

        AIResponse budgetResponse =
                budgetAgent.estimateBudget(
                        roomDescription,
                        plannerResponse.getContent(),
                        styleResponse.getContent()
                );

        AIResponse reportResponse =
                reportAgent.generateReport(
                        plannerResponse.getContent(),
                        styleResponse.getContent(),
                        budgetResponse.getContent()
                );

        List<String> images =
                imageGeneratorAgent.generateImages(
                        roomDescription,
                        plannerResponse.getContent(),
                        styleResponse.getContent(),
                        budgetResponse.getContent(),
                        reportResponse.getContent()
                );

        return DesignResponse.builder()
                .roomAnalysis(roomDescription)
                .planner(plannerResponse.getContent())
                .style(styleResponse.getContent())
                .budget(budgetResponse.getContent())
                .finalReport(reportResponse.getContent())
                .generatedImages(images)
                .build();
    }
}