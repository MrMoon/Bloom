package com.bloom.hospital.service;

import com.bloom.hospital.model.Fee;
import reactor.core.publisher.Mono;

public interface FeeService {

    Mono<Fee> createFee(Mono<Fee> feeMono);

    Mono<Fee> getFeeByNumber(String feeNumber);

    Mono<Fee> updateFee(Mono<Fee> feeMono);

    Mono<Void> deleteFeeByNumber(String feeNumber);

}
