package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.hospital.Inventory;
import com.bloom.demo.service.hospital.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hospital/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{inventoryId}/{inventoryMangedBy}")
    public Mono<Inventory> getInventory(@PathVariable("inventoryId") String inventoryId, @PathVariable("inventoryMangedBy") String inventoryMangedBy) {
        return this.inventoryService.getInventory(inventoryId, inventoryMangedBy);
    }

    @GetMapping("/{nurseId}")
    public Flux<Inventory> getNurseInventories(@PathVariable("nurseId") String nurseId) {
        return this.inventoryService.getInventoriesByNurseId(nurseId);
    }

    @GetMapping("/number/{recordType}")
    public Mono<Long> getInventoryRecordTypeNumbers(@PathVariable("recordType") String recordType) {
        return this.inventoryService.getNumberOfRecordByRecordType(recordType);
    }

    @PostMapping("/")
    public Mono<Inventory> createInventory(@RequestBody Mono<Inventory> inventoryMono) {
        return inventoryMono.flatMap(this.inventoryService::createInventory);
    }

    @PutMapping("/")
    public Mono<Inventory> updateInventory(@RequestBody Mono<Inventory> inventoryMono) {
        return inventoryMono.flatMap(this.inventoryService::updateInventory);
    }

    @DeleteMapping("/{inventoryId}/{inventoryMangedBy}")
    public Mono<Void> deleteInventory(@PathVariable("inventoryId") String inventoryId, @PathVariable("inventoryMangedBy") String inventoryManagedBy) {
        return this.inventoryService.deleteInventory(inventoryId, inventoryManagedBy);
    }
}
