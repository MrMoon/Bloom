package com.bloom.hospital.service;

import com.bloom.hospital.model.Inventory;
import reactor.core.publisher.Mono;

public interface InventoryService {

    Mono<Inventory> createInventory(Mono<Inventory> inventoryMono);

    Mono<Inventory> getInventory(String inventoryMangedBy);

    Mono<Inventory> updateInventory(Mono<Inventory> inventoryMono);

    Mono<Void> deleteInventory(String inventoryMangedBy);
}
