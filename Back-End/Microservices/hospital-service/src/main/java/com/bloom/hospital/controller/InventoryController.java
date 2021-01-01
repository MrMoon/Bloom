package com.bloom.hospital.controller;

import com.bloom.hospital.model.Inventory;
import com.bloom.hospital.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{inventoryMangedBy}")
    public Mono<Inventory> getInventory(@PathVariable("inventoryMangedBy") String inventoryMangedBy) {
        return this.inventoryService.getInventory(inventoryMangedBy);
    }

    @PostMapping("/")
    public Mono<Inventory> createInventory(@RequestBody Mono<Inventory> inventoryMono) {
        return this.inventoryService.createInventory(inventoryMono);
    }

    @PutMapping("/")
    public Mono<Inventory> updateInventory(@RequestBody Mono<Inventory> inventoryMono) {
        return this.inventoryService.updateInventory(inventoryMono);
    }

    @DeleteMapping("/{inventoryMangedBy}")
    public Mono<Void> deleteInventory(@PathVariable("inventoryMangedBy") String inventoryManagedBy) {
        return this.inventoryService.deleteInventory(inventoryManagedBy);
    }
}
