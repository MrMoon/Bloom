package com.bloom.hospital.service;

import com.bloom.hospital.model.Room;
import reactor.core.publisher.Mono;

public interface RoomService {

    Mono<Room> createRoom(Mono<Room> roomMono);

    Mono<Room> getRoomByNumber(String roomNumber);

    Mono<Room> updateRoom(Mono<Room> roomMono);

    Mono<Void> deleteRoomById(String roomNumber);

}
