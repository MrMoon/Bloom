package com.bloom.hospital.controller;

import com.bloom.hospital.model.Fee;
import com.bloom.hospital.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/fee")
public class FeeController {

    private final FeeService feeService;

    @GetMapping("/{feeNumber}")
    public Mono<Fee> getFeeById(@PathVariable("feeNumber") String feeNumber) {
        return this.feeService.getFeeByNumber(feeNumber);
    }

    @PostMapping("/")
    public Mono<Fee> createFee(@RequestBody Mono<Fee> feeMono) {
        return this.feeService.createFee(feeMono);
    }

    @PutMapping("/")
    public Mono<Fee> updateFee(@RequestBody Mono<Fee> feeMono) {
        return this.feeService.updateFee(feeMono);
    }

    @DeleteMapping("/{feeNumber}")
    public Mono<Void> deleteFee(@PathVariable("feeNumber") String feeNumber) {
        return this.feeService.deleteFeeByNumber(feeNumber);
    }

}
