package com.agentspace.room.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

    @NotBlank(message = "Room name is required")
    private String roomName;

    @NotNull(message = "Room type is required")
    private String roomType;

    @NotNull(message = "Length is required")
    @DecimalMin(value = "1.0")
    private Double length;

    @NotNull(message = "Width is required")
    @DecimalMin(value = "1.0")
    private Double width;

    @NotNull(message = "Height is required")
    @DecimalMin(value = "1.0")
    private Double height;

    @NotNull(message = "Budget is required")
    @DecimalMin(value = "1000")
    private Double budget;

    @NotNull(message = "Preferred style is required")
    private String preferredStyle;

}