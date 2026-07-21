package com.agentspace.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignResponse {

    /**
     * Planner Agent Output
     */
    private String planner;

    /**
     * Style Agent Output
     */
    private String style;

    /**
     * Budget Agent Output
     */
    private String budget;

    /**
     * Final Report
     */
    private String finalReport;

}