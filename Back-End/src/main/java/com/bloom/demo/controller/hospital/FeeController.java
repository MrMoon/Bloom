package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.StatNumbers;
import com.bloom.demo.model.hospital.Fee;
import com.bloom.demo.model.hospital.FeePatient;
import com.bloom.demo.service.hospital.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hospital/fee")
public class FeeController {

    private final FeeService feeService;

    @GetMapping("/")
    public Flux<FeePatient> getAll() {
        return this.feeService.getAll();
    }

    @GetMapping("/price")
    public Mono<Double> getSumAll() {
        return this.feeService.getAllFees();
    }

    @GetMapping("/yesterday")
    public Mono<Double> getSumYesterday() {
        return this.feeService.getTotalBeforeToday();
    }

    @GetMapping("/analysis")
    public Mono<StatNumbers> getAnalysis() {
        return this.feeService.getFeeAnalysis();
    }

    @GetMapping("/{feeNumber}")
    public Mono<Fee> getFeeById(@PathVariable("feeNumber") String feeNumber) {
        return this.feeService.getFeeByNumber(feeNumber);
    }

    @GetMapping("/patient/price/{patientId}")
    public Mono<Double> getAllFeesSumForPatient(@PathVariable("patientId") String patientId) {
        return this.feeService.getPatientFinalPrice(patientId);
    }

    @GetMapping("/patient/{patientId}")
    public Flux<Fee> getFeeByPatientId(@PathVariable("patientId") String patientId) {
        return this.feeService.getFeeByPatientId(patientId);
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
