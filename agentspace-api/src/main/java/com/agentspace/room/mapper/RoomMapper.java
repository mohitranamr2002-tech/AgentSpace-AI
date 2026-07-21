package com.agentspace.room.mapper;

import com.agentspace.room.dto.RoomRequest;
import com.agentspace.room.dto.RoomResponse;
import com.agentspace.room.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room toEntity(RoomRequest request) {

        if (request == null) {
            return null;
        }

        Room room = new Room();

        room.setRoomName(request.getRoomName());
        room.setRoomType(request.getRoomType());
        room.setLength(request.getLength());
        room.setWidth(request.getWidth());
        room.setHeight(request.getHeight());
        room.setBudget(request.getBudget());
        room.setPreferredStyle(request.getPreferredStyle());

        return room;

    }

    public RoomResponse toResponse(Room room) {

        if (room == null) {
            return null;
        }

        return RoomResponse.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .roomType(room.getRoomType())
                .length(room.getLength())
                .width(room.getWidth())
                .height(room.getHeight())
                .budget(room.getBudget())
                .preferredStyle(room.getPreferredStyle())
                .createdAt(room.getCreatedAt())
                .build();

    }

    public void updateEntity(RoomRequest request, Room room) {

        room.setRoomName(request.getRoomName());
        room.setRoomType(request.getRoomType());
        room.setLength(request.getLength());
        room.setWidth(request.getWidth());
        room.setHeight(request.getHeight());
        room.setBudget(request.getBudget());
        room.setPreferredStyle(request.getPreferredStyle());

    }

}