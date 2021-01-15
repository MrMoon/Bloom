package com.bloom.demo.service.hospital;

import com.bloom.demo.model.hospital.Inventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryService {

    Mono<Inventory> createInventory(Inventory inventory);

    Mono<Inventory> getInventory(String inventoryId, String inventoryMangedBy);

    Mono<Inventory> updateInventory(Inventory inventory);

    Mono<Void> deleteInventory(String inventoryId, String inventoryMangedBy);

    Flux<Inventory> getInventoriesByNurseId(String nurseId);

    Mono<Long> getNumberByRecordType(String recordType);

}
