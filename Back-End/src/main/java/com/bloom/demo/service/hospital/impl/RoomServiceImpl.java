package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.hospital.Room;
import com.bloom.demo.repository.hospital.RoomRepository;
import com.bloom.demo.service.hospital.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Mono<Room> createRoom(Room room) {
        return this.roomRepository.save(room);
    }

    @Override
    public Mono<Room> getRoomByNumber(String roomNumber) {
        return this.roomRepository.findById(Long.parseLong(roomNumber));
    }

    @Override
    public Mono<Room> updateRoom(Room room) {
        return this.roomRepository.save(room);
    }

    @Override
    public Mono<Void> deleteRoomById(String roomNumber) {
        return this.roomRepository.deleteById(Long.parseLong(roomNumber));
    }

    @Override
    public Flux<Room> getALl() {
        return this.roomRepository.findAll();
    }
}
