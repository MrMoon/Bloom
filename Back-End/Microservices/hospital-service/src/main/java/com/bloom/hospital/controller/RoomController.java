package com.bloom.hospital.controller;

import com.bloom.hospital.model.Room;
import com.bloom.hospital.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/inventory")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{roomNumber}")
    public Mono<Room> getRoomByNumber(@PathVariable("roomNumber") String roomNumber) {
        return this.roomService.getRoomByNumber(roomNumber);
    }

    @PostMapping("/")
    public Mono<Room> createRoom(@RequestBody Mono<Room> roomMono) {
        return this.roomService.createRoom(roomMono);
    }

    @PutMapping("/")
    public Mono<Room> updateRoom(@RequestBody Mono<Room> roomMono) {
        return this.roomService.updateRoom(roomMono);
    }

    @DeleteMapping("/{roomNumber}")
    public Mono<Void> deleteRoomByNumber(@PathVariable("roomNumber") String roomNumber) {
        return this.roomService.deleteRoomById(roomNumber);
    }

}
