package com.agentspace.room.util;

import com.agentspace.room.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomPromptBuilder {

    public String buildPrompt(Room room, String imageAnalysis) {

        return """
                You are an expert Interior Designer.

                ===========================
                ROOM INFORMATION
                ===========================

                Room Name : %s
                Room Type : %s

                Dimensions

                Length : %.2f ft
                Width  : %.2f ft
                Height : %.2f ft

                Budget : ₹%.2f

                Preferred Style : %s

                ===========================
                AI IMAGE ANALYSIS
                ===========================

                %s

                ===========================
                TASK
                ===========================

                Create a professional interior design recommendation considering:

                • Room dimensions
                • Available budget
                • Preferred style
                • Existing furniture
                • Lighting
                • Flooring
                • Walls
                • Windows
                • Space utilization

                Respond in Markdown.
                """
                .formatted(
                        room.getRoomName(),
                        room.getRoomType(),
                        room.getLength(),
                        room.getWidth(),
                        room.getHeight(),
                        room.getBudget(),
                        room.getPreferredStyle(),
                        imageAnalysis
                );

    }

}