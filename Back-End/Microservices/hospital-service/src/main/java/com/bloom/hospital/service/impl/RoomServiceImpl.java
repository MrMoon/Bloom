package com.bloom.hospital.service.impl;

import com.bloom.hospital.model.Room;
import com.bloom.hospital.repository.RoomRepository;
import com.bloom.hospital.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Mono<Room> createRoom(Mono<Room> roomMono) {
        return roomMono.flatMap(this.roomRepository::save);
    }

    @Override
    public Mono<Room> getRoomByNumber(String roomNumber) {
        return this.roomRepository.findById(Long.parseLong(roomNumber));
    }

    @Override
    public Mono<Room> updateRoom(Mono<Room> roomMono) {
        return roomMono.flatMap(this.roomRepository::save);
    }

    @Override
    public Mono<Void> deleteRoomById(String roomNumber) {
        return this.roomRepository.deleteById(Long.parseLong(roomNumber));
    }
}
