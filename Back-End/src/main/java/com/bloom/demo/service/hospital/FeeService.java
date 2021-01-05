package com.bloom.demo.service.hospital;

import com.bloom.demo.model.hospital.Fee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeeService {

    Mono<Fee> createFee(Fee fee);

    Mono<Fee> getFeeByNumber(String feeNumber);

    Mono<Fee> updateFee(Fee fee);

    Mono<Void> deleteFeeByNumber(String feeNumber);

    Flux<Fee> getFeeByPatientId(String patientId);

    Mono<Double> getPatientFinalPrice(String patientId);

}
