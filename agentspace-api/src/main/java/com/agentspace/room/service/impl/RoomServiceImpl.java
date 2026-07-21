package com.agentspace.room.service.impl;

import com.agentspace.room.dto.RoomRequest;
import com.agentspace.room.dto.RoomResponse;
import com.agentspace.room.entity.Room;
import com.agentspace.room.mapper.RoomMapper;
import com.agentspace.room.repository.RoomRepository;
import com.agentspace.room.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    @Override
    public RoomResponse createRoom(RoomRequest request) {

        Room room = roomMapper.toEntity(request);

        Room savedRoom = roomRepository.save(room);

        return roomMapper.toResponse(savedRoom);

    }

    @Override
    public RoomResponse getRoomById(Long roomId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Room not found with id : " + roomId));

        return roomMapper.toResponse(room);

    }

    @Override
    public List<RoomResponse> getAllRooms() {

        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toResponse)
                .toList();

    }

    @Override
    public RoomResponse updateRoom(Long roomId,
                                   RoomRequest request) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Room not found with id : " + roomId));

        roomMapper.updateEntity(request, room);

        Room updatedRoom = roomRepository.save(room);

        return roomMapper.toResponse(updatedRoom);

    }

    @Override
    public void deleteRoom(Long roomId) {

        if (!roomRepository.existsById(roomId)) {

            throw new EntityNotFoundException(
                    "Room not found with id : " + roomId);

        }

        roomRepository.deleteById(roomId);

    }

}