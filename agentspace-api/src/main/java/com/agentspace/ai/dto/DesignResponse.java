package com.agentspace.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignResponse {

    private String roomAnalysis;

    private String planner;

    private String style;

    private String budget;

    private String finalReport;

    private List<String> generatedImages;

    @Builder.Default
    private LocalDateTime generatedAt = LocalDateTime.now();
}