package com.agentspace.ai.vision;

import com.agentspace.ai.config.GeminiProperties;
import com.agentspace.ai.dto.ImageAnalysisResponse;
import com.agentspace.ai.prompt.PromptLoader;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GeminiVisionService implements VisionService {

    private static final Set<String> SUPPORTED_CONTENT_TYPES =
            Set.of("image/png", "image/jpeg", "image/webp");

    private final Client client;
    private final GeminiProperties properties;
    private final PromptLoader promptLoader;

    @Override
    public ImageAnalysisResponse analyzeImage(MultipartFile image) {

        String contentType = image.getContentType();

        if (contentType == null || !SUPPORTED_CONTENT_TYPES.contains(contentType)) {

            throw new IllegalArgumentException(
                    "Unsupported image type : " + contentType
                            + ". Supported types : " + SUPPORTED_CONTENT_TYPES
            );

        }

        try {
            System.out.println("==================================");
            System.out.println("Gemini Vision Started");
            System.out.println("Model : " + properties.getModel());
            System.out.println("Content Type : " + image.getContentType());
            System.out.println("Image Name : " + image.getOriginalFilename());
            System.out.println("Image Size : " + image.getSize());
            System.out.println("==================================");

            byte[] imageBytes = image.getBytes();

            String visionPrompt =
                    promptLoader.loadPrompt("vision.md");

            Part imagePart = Part.fromBytes(imageBytes, contentType);
            Part textPart = Part.fromText(visionPrompt);

            Content content = Content.fromParts(imagePart, textPart);

            GenerateContentResponse response =
                    client.models.generateContent(
                            properties.getModel(),
                            content,
                            null
                    );

            return ImageAnalysisResponse.builder()
                    .analysis(response.text())
                    .build();

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to read uploaded image",
                    ex
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            throw new RuntimeException(
                    "Unable to analyze room image : " + ex.getMessage(),
                    ex
            );

        }

    }

}
