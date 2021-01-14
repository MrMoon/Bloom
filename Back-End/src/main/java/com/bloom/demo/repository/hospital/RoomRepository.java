package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {

    Mono<Long> countByRoomType(String roomType);

}
