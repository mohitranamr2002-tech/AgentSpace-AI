package com.agentspace.room.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

    private Long id;

    private String roomName;

    private String roomType;

    private Double length;

    private Double width;

    private Double height;

    private Double budget;

    private String preferredStyle;

    private LocalDateTime createdAt;

}