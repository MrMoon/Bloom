package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {


}
