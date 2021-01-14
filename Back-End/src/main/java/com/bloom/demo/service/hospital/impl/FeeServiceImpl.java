package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.hospital.Fee;
import com.bloom.demo.model.hospital.FeePatient;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.repository.hospital.FeeRepository;
import com.bloom.demo.repository.patient.PatientRepository;
import com.bloom.demo.service.hospital.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;
    private final PatientRepository patientRepository;

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
        return this.feeRepository
                .findAllByPatientId(Long.parseLong(patientId))
                .flatMap(fee -> Mono.just(fee.getFeeAmount()))
                .reduce(0.0, Double::sum);
    }

    @Override
    public Mono<Double> getAllFees() {
        return this.feeRepository
                .findAll()
                .flatMap(fee -> Mono.just(fee.getFeeAmount()))
                .reduce(0.0, Double::sum);
    }

    @Override
    public Flux<FeePatient> getAll() {
        return this.feeRepository
                .findAll()
                .flatMap(fee -> {
                    Mono<Patient> patientMono = this.patientRepository.findById(fee.getPatientId());
                    return patientMono.flatMap(patient -> {
                        FeePatient feePatient = new FeePatient();
                        feePatient.setFeeDate(fee.getFeeDate());
                        feePatient.setFeeAmount(fee.getFeeAmount());
                        feePatient.setPatientName(patient.getPatientName());
                        feePatient.setFeePaymentNumber(fee.getFeePaymentNumber());
                        return Mono.just(feePatient);
                    });
                });
    }

    @Override
    public Mono<Double> getTotalBeforeDay() {
        return this.feeRepository
                .findFeesByFeeDateAfter(LocalDate.now().minusDays(1))
                .flatMap(fee -> Mono.just(fee.getFeeAmount()))
                .reduce(0.0, Double::sum);
    }
}
