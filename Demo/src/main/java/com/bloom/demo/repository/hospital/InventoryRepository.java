package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.Inventory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Long> {

}
