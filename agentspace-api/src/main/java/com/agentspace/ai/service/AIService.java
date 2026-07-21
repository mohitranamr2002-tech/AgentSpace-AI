package com.agentspace.ai.service;

import com.agentspace.ai.dto.AIResponse;

public interface AIService {

    AIResponse generateResponse(String prompt);

}