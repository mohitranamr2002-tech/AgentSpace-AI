package com.agentspace.ai.vision;

import com.agentspace.ai.dto.ImageAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class VisionAgent {

    private final VisionService visionService;

    public String analyzeRoom(MultipartFile image) {

        ImageAnalysisResponse response =
                visionService.analyzeImage(image);

        return response.getAnalysis();

    }

}