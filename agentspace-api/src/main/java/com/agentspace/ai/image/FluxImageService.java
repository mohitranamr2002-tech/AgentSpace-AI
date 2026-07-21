package com.agentspace.ai.image;

import com.agentspace.ai.config.FluxConfig;
import com.agentspace.config.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FluxImageService implements ImageService {

    private final FluxConfig fluxConfig;

    private final StorageProperties storageProperties;

    @Value("${application.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    @Override
    public List<String> generateImages(String prompt) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(fluxConfig.getApiKey());

        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = """
                {
                  "inputs":"%s"
                }
                """.formatted(prompt);

        HttpEntity<String> entity =
                new HttpEntity<>(body, headers);

        String endpoint =
                "https://api-inference.huggingface.co/models/"
                        + fluxConfig.getImageModel();

        ResponseEntity<byte[]> response =
                restTemplate.exchange(
                        endpoint,
                        HttpMethod.POST,
                        entity,
                        byte[].class
                );

        if (response.getBody() == null) {

            throw new RuntimeException("Image generation failed.");

        }

        String fileName =
                "design-"
                        + UUID.randomUUID()
                        + ".png";

        Path outputPath =
                Path.of(
                        storageProperties.getGeneratedImages(),
                        fileName
                );

        try {

            Files.createDirectories(outputPath.getParent());

            Files.write(outputPath, response.getBody());

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to save generated image",
                    ex
            );

        }

        String imageUrl =
                baseUrl
                        + "/generated/"
                        + fileName;

        return List.of(imageUrl);

    }

}