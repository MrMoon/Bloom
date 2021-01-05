package com.bloom.hospital.service.impl;

import com.bloom.hospital.model.Inventory;
import com.bloom.hospital.repository.InventoryRepository;
import com.bloom.hospital.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Mono<Inventory> createInventory(Mono<Inventory> inventoryMono) {
        return inventoryMono.flatMap(this.inventoryRepository::save);
    }

    @Override
    public Mono<Inventory> getInventory(String inventoryMangedBy) {
        return this.inventoryRepository.findById(Long.parseLong(inventoryMangedBy));
    }

    @Override
    public Mono<Inventory> updateInventory(Mono<Inventory> inventoryMono) {
        return inventoryMono.flatMap(this.inventoryRepository::save);
    }

    @Override
    public Mono<Void> deleteInventory(String inventoryMangedBy) {
        return this.inventoryRepository.deleteById(Long.parseLong(inventoryMangedBy));
    }
}
