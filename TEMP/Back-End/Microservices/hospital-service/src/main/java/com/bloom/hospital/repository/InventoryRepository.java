package com.bloom.hospital.repository;

import com.bloom.hospital.model.Inventory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Long> {

}
