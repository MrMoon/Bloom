package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.StatNumbers;
import com.bloom.demo.model.hospital.Room;
import com.bloom.demo.repository.hospital.PatientEntryRepository;
import com.bloom.demo.repository.hospital.RoomRepository;
import com.bloom.demo.service.hospital.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final PatientEntryRepository patientEntryRepository;

    @Override
    public Mono<Room> createRoom(Room room) {
        room.setRoomType(room.getRoomType().toLowerCase(Locale.ROOT));
        return this.roomRepository.save(room);
    }

    @Override
    public Mono<Room> getRoomByNumber(String roomNumber) {
        return this.roomRepository.findById(Long.parseLong(roomNumber));
    }

    @Override
    public Mono<Room> updateRoom(Room room) {
        room.setRoomType(room.getRoomType().toLowerCase(Locale.ROOT));
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

    @Override
    public Mono<Long> getRoomTypesNumber(String roomType) {
        return this.roomRepository.countByRoomType(roomType.toLowerCase(Locale.ROOT));
    }

    @Override
    public Mono<StatNumbers> getNumberOfRoomsWithStatus() {
        return this.roomRepository
                .count()
                .flatMap(numberOfRooms -> {
                    StatNumbers statNumbers = new StatNumbers();
                    statNumbers.setTotal(numberOfRooms);
                    return this.patientEntryRepository
                            .countDistinctRoomUsed()
                            .flatMap(numberOfUsedRooms -> {
                                statNumbers.setX(numberOfUsedRooms);
                                return Mono.just(statNumbers);
                            });
                });
    }
}
