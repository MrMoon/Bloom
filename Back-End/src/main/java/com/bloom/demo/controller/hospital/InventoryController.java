package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.hospital.Inventory;
import com.bloom.demo.service.hospital.InventoryService;
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
        return inventoryMono.flatMap(this.inventoryService::createInventory);
    }

    @PutMapping("/")
    public Mono<Inventory> updateInventory(@RequestBody Mono<Inventory> inventoryMono) {
        return inventoryMono.flatMap(this.inventoryService::updateInventory);
    }

    @DeleteMapping("/{inventoryMangedBy}")
    public Mono<Void> deleteInventory(@PathVariable("inventoryMangedBy") String inventoryManagedBy) {
        return this.inventoryService.deleteInventory(inventoryManagedBy);
    }
}
