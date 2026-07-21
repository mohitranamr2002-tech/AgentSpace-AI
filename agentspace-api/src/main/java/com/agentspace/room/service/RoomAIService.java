package com.agentspace.room.service;

import com.agentspace.ai.dto.DesignResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RoomAIService {

    DesignResponse generateDesign(Long roomId,
                                  MultipartFile image);

}