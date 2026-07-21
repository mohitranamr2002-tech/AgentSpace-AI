package com.agentspace.ai.vision;

import com.agentspace.ai.dto.ImageAnalysisResponse;
import org.springframework.web.multipart.MultipartFile;

public interface VisionService {

    ImageAnalysisResponse analyzeImage(MultipartFile image);

}