package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.employee.Rank;
import com.bloom.demo.model.hospital.Inventory;
import com.bloom.demo.repository.employee.NurseRepository;
import com.bloom.demo.repository.hospital.InventoryRepository;
import com.bloom.demo.service.hospital.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final NurseRepository nurseRepository;

    @Override
    public Mono<Inventory> createInventory(Inventory inventory) {
        return this.nurseRepository.findById(inventory.getInventoryMangedBy()).flatMap(nurse -> {
            if (nurse.getNurseRank().equals(Rank.ONE)) return this.inventoryRepository.save(inventory);
            else return Mono.empty();
        });
    }

    @Override
    public Mono<Inventory> getInventory(String inventoryId, String inventoryMangedBy) {
        return this.inventoryRepository.findByInventoryIdAndInventoryMangedBy(Long.parseLong(inventoryId), Long.parseLong(inventoryMangedBy));
    }

    @Override
    public Mono<Inventory> updateInventory(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    @Override
    public Mono<Void> deleteInventory(String inventoryId, String inventoryMangedBy) {
        return this.inventoryRepository.deleteByInventoryIdAndInventoryMangedBy(Long.parseLong(inventoryId), Long.parseLong(inventoryMangedBy));
    }

    @Override
    public Flux<Inventory> getInventoriesByNurseId(String nurseId) {
        return this.inventoryRepository.findAllByInventoryMangedBy(Long.parseLong(nurseId));
    }

    @Override
    public Mono<Long> getNumberByRecordType(String recordType) {
        return this.inventoryRepository
                .findAllByInventoryRecordType(recordType)
                .count();
    }
}
