package com.agentspace.room.service;

import com.agentspace.room.dto.RoomRequest;
import com.agentspace.room.dto.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomResponse createRoom(RoomRequest request);

    RoomResponse getRoomById(Long roomId);

    List<RoomResponse> getAllRooms();

    RoomResponse updateRoom(Long roomId,
                            RoomRequest request);

    void deleteRoom(Long roomId);

}