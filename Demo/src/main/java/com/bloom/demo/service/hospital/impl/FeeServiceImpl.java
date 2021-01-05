package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.hospital.Fee;
import com.bloom.demo.repository.hospital.FeeRepository;
import com.bloom.demo.service.hospital.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    @Override
    public Mono<Fee> createFee(Fee fee) {
        return this.feeRepository.save(fee);
    }

    @Override
    public Mono<Fee> getFeeByNumber(String feeNumber) {
        return this.feeRepository.findById(Long.parseLong(feeNumber));
    }

    @Override
    public Mono<Fee> updateFee(Fee fee) {
        return this.feeRepository.save(fee);
    }

    @Override
    public Mono<Void> deleteFeeByNumber(String feeNumber) {
        return this.feeRepository.deleteById(Long.parseLong(feeNumber));
    }

    @Override
    public Flux<Fee> getFeeByPatientId(String patientId) {
        return this.feeRepository.findAllByPatientId(Long.parseLong(patientId));
    }

    @Override
    public Mono<Double> getPatientFinalPrice(String patientId) {
        return this.feeRepository.findAllByPatientId(Long.parseLong(patientId)).flatMap(fee -> Mono.just(fee.getFeeAmount())).reduce(0.0 , Double::sum);
    }
}
