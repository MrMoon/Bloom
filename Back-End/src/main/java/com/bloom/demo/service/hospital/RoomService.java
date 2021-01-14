package com.bloom.demo.service.hospital;

import com.bloom.demo.model.hospital.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {

    Mono<Room> createRoom(Room room);

    Mono<Room> getRoomByNumber(String roomNumber);

    Mono<Room> updateRoom(Room room);

    Mono<Void> deleteRoomById(String roomNumber);

    Flux<Room> getALl();

    Mono<Long> getRoomTypesNumber(String roomType);

}
