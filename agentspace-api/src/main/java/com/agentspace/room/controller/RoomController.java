package com.agentspace.room.controller;

import com.agentspace.ai.dto.ApiResponse;
import com.agentspace.ai.dto.DesignResponse;
import com.agentspace.room.dto.RoomRequest;
import com.agentspace.room.dto.RoomResponse;
import com.agentspace.room.service.RoomAIService;
import com.agentspace.room.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;

    private final RoomAIService roomAIService;

    /**
     * Create Room
     */
    @PostMapping
    public ResponseEntity<ApiResponse<RoomResponse>> createRoom(
            @Valid @RequestBody RoomRequest request) {

        RoomResponse room = roomService.createRoom(request);

        ApiResponse<RoomResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Room created successfully.");
        response.setData(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get Room By Id
     */
    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse<RoomResponse>> getRoomById(
            @PathVariable Long roomId) {

        RoomResponse room = roomService.getRoomById(roomId);

        ApiResponse<RoomResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Room fetched successfully.");
        response.setData(room);

        return ResponseEntity.ok(response);
    }

    /**
     * Get All Rooms
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoomResponse>>> getAllRooms() {

        List<RoomResponse> rooms = roomService.getAllRooms();

        ApiResponse<List<RoomResponse>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Rooms fetched successfully.");
        response.setData(rooms);

        return ResponseEntity.ok(response);
    }

    /**
     * Update Room
     */
    @PutMapping("/{roomId}")
    public ResponseEntity<ApiResponse<RoomResponse>> updateRoom(
            @PathVariable Long roomId,
            @Valid @RequestBody RoomRequest request) {

        RoomResponse room = roomService.updateRoom(roomId, request);

        ApiResponse<RoomResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Room updated successfully.");
        response.setData(room);

        return ResponseEntity.ok(response);
    }

    /**
     * Delete Room
     */
    @DeleteMapping("/{roomId}")
    public ResponseEntity<ApiResponse<String>> deleteRoom(
            @PathVariable Long roomId) {

        roomService.deleteRoom(roomId);

        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Room deleted successfully.");
        response.setData("Room deleted successfully.");

        return ResponseEntity.ok(response);
    }

    /**
     * Generate AI Interior Design
     */
    @PostMapping(
            value = "/{roomId}/generate-design",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<DesignResponse>> generateDesign(

            @PathVariable Long roomId,

            @RequestParam("image") MultipartFile image

    ) {

        DesignResponse response =
                roomAIService.generateDesign(roomId, image);

        ApiResponse<DesignResponse> apiResponse =
                new ApiResponse<>();

        apiResponse.setSuccess(true);
        apiResponse.setMessage("Interior design generated successfully.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }

}