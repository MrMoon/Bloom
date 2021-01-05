package com.bloom.demo.service.hospital;

import com.bloom.demo.model.hospital.Inventory;
import reactor.core.publisher.Mono;

public interface InventoryService {

    Mono<Inventory> createInventory(Inventory inventory);

    Mono<Inventory> getInventory(String inventoryMangedBy);

    Mono<Inventory> updateInventory(Inventory inventory);

    Mono<Void> deleteInventory(String inventoryMangedBy);
}
