package com.agentspace.ai.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageGeneratorAgent {

    private final ImagePromptAgent promptAgent;

    private final ImageService imageService;

    public List<String> generateImages(
            String roomAnalysis,
            String planner,
            String style,
            String budget,
            String report) {

        String prompt = promptAgent.buildPrompt(
                roomAnalysis,
                planner,
                style,
                budget,
                report
        );

        return imageService.generateImages(prompt);

    }

}