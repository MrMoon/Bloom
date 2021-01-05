package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.hospital.Fee;
import com.bloom.demo.service.hospital.FeeService;
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
        return feeMono.flatMap(this.feeService::createFee);
    }

    @PutMapping("/")
    public Mono<Fee> updateFee(@RequestBody Mono<Fee> feeMono) {
        return feeMono.flatMap(this.feeService::updateFee);
    }

    @DeleteMapping("/{feeNumber}")
    public Mono<Void> deleteFee(@PathVariable("feeNumber") String feeNumber) {
        return this.feeService.deleteFeeByNumber(feeNumber);
    }

}
