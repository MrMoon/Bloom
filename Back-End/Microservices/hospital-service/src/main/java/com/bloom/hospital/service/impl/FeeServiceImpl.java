package com.bloom.hospital.service.impl;

import com.bloom.hospital.model.Fee;
import com.bloom.hospital.repository.FeeRepository;
import com.bloom.hospital.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    @Override
    public Mono<Fee> createFee(Mono<Fee> feeMono) {
        return feeMono.flatMap(this.feeRepository::save);
    }

    @Override
    public Mono<Fee> getFeeByNumber(String feeNumber) {
        return this.feeRepository.findById(Long.parseLong(feeNumber));
    }

    @Override
    public Mono<Fee> updateFee(Mono<Fee> feeMono) {
        return feeMono.flatMap(this.feeRepository::save);
    }

    @Override
    public Mono<Void> deleteFeeByNumber(String feeNumber) {
        return this.feeRepository.deleteById(Long.parseLong(feeNumber));
    }
}
