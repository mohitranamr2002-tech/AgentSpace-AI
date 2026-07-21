package com.agentspace.ai.orchestrator;

import com.agentspace.ai.agent.BudgetAgent;
import com.agentspace.ai.agent.PlannerAgent;
import com.agentspace.ai.agent.ReportAgent;
import com.agentspace.ai.agent.StyleAgent;
import com.agentspace.ai.dto.AIResponse;
import com.agentspace.ai.dto.DesignResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesignOrchestrator {

    private final PlannerAgent plannerAgent;

    private final StyleAgent styleAgent;

    private final BudgetAgent budgetAgent;

    private final ReportAgent reportAgent;

    public DesignResponse generateDesign(String roomDescription) {

        // Step 1 : Planner

        AIResponse plannerResponse =
                plannerAgent.createPlan(roomDescription);

        String planner = plannerResponse.getContent();

        // Step 2 : Style

        AIResponse styleResponse =
                styleAgent.suggestStyle(
                        roomDescription,
                        planner
                );

        String style = styleResponse.getContent();

        // Step 3 : Budget

        AIResponse budgetResponse =
                budgetAgent.estimateBudget(
                        roomDescription,
                        planner,
                        style
                );

        String budget = budgetResponse.getContent();

        // Step 4 : Final Report

        AIResponse reportResponse =
                reportAgent.generateReport(
                        planner,
                        style,
                        budget
                );

        return DesignResponse.builder()
                .planner(planner)
                .style(style)
                .budget(budget)
                .finalReport(reportResponse.getContent())
                .build();

    }

}