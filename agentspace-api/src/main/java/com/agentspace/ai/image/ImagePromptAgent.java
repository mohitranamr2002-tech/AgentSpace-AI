package com.agentspace.ai.image;

import org.springframework.stereotype.Component;

@Component
public class ImagePromptAgent {

    public String buildPrompt(
            String roomAnalysis,
            String planner,
            String style,
            String budget,
            String report) {

        return """
Create an ultra-realistic interior design.

Room Analysis:
%s

Layout:
%s

Interior Style:
%s

Budget:
%s

Design Summary:
%s

Requirements:

- Photorealistic
- Interior Architecture
- Modern Furniture
- Natural Lighting
- Premium Materials
- HDR
- 8K
- Interior Photography
- Wide Angle
- Highly Detailed
""".formatted(
                roomAnalysis,
                planner,
                style,
                budget,
                report
        );

    }

}