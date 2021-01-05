package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.Inventory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Long> {

    /*@Modifying
    @Query("SELECT * FROM inventory WHERE inventory_managed_by = :nurseId")*/
    Flux<Inventory> findAllByInventoryMangedBy(Long nurseId);

}
