package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.hospital.Inventory;
import com.bloom.demo.repository.hospital.InventoryRepository;
import com.bloom.demo.service.hospital.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Mono<Inventory> createInventory(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    @Override
    public Mono<Inventory> getInventory(String inventoryMangedBy) {
        return this.inventoryRepository.findById(Long.parseLong(inventoryMangedBy));
    }

    @Override
    public Mono<Inventory> updateInventory(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    @Override
    public Mono<Void> deleteInventory(String inventoryMangedBy) {
        return this.inventoryRepository.deleteById(Long.parseLong(inventoryMangedBy));
    }
}
