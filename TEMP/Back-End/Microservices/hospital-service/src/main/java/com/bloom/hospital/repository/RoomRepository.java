package com.bloom.hospital.repository;

import com.bloom.hospital.model.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {

}
