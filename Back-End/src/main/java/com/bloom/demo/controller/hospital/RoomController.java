package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.hospital.Room;
import com.bloom.demo.service.hospital.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{roomNumber}")
    public Mono<Room> getRoomByNumber(@PathVariable("roomNumber") String roomNumber) {
        return this.roomService.getRoomByNumber(roomNumber);
    }

    @PostMapping("/")
    public Mono<Room> createRoom(@RequestBody Mono<Room> roomMono) {
        return roomMono.flatMap(this.roomService::createRoom);
    }

    @PutMapping("/")
    public Mono<Room> updateRoom(@RequestBody Mono<Room> roomMono) {
        return roomMono.flatMap(this.roomService::updateRoom);
    }

    @DeleteMapping("/{roomNumber}")
    public Mono<Void> deleteRoomByNumber(@PathVariable("roomNumber") String roomNumber) {
        return this.roomService.deleteRoomById(roomNumber);
    }

}
