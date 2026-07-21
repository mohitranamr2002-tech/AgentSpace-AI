package com.agentspace.room.service.impl;

import com.agentspace.ai.dto.DesignResponse;
import com.agentspace.ai.orchestrator.DesignOrchestrator;
import com.agentspace.ai.vision.VisionAgent;
import com.agentspace.room.entity.Room;
import com.agentspace.room.repository.RoomRepository;
import com.agentspace.room.service.RoomAIService;
import com.agentspace.room.util.RoomPromptBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RoomAIServiceImpl implements RoomAIService {

    private final RoomRepository roomRepository;

    private final RoomPromptBuilder roomPromptBuilder;

    private final DesignOrchestrator designOrchestrator;

    private final VisionAgent visionAgent;

    @Override
    public DesignResponse generateDesign(Long roomId,
                                         MultipartFile image) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Room not found with id : " + roomId));

        /*
         * Step 1
         * Analyze uploaded room image
         */
        String imageAnalysis =
                visionAgent.analyzeRoom(image);

        /*
         * Step 2
         * Merge Room + Vision Prompt
         */
        String finalPrompt =
                roomPromptBuilder.buildPrompt(
                        room,
                        imageAnalysis
                );

        /*
         * Step 3
         * Execute Agent Workflow
         */
        return designOrchestrator.generateDesign(finalPrompt);

    }

}