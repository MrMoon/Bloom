package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.repository.hospital.PatientEntryRepository;
import com.bloom.demo.service.hospital.PatientEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientEntryServiceImpl implements PatientEntryService {

    private final PatientEntryRepository patientEntryRepository;

    @Override
    public Mono<PatientEntry> createPatientEntry(PatientEntry patientEntry) {
        return this.patientEntryRepository.save(patientEntry);
    }

    @Override
    public Mono<PatientEntry> getPatientEntryById(String patientEntryId) {
        return this.patientEntryRepository.findById(Long.parseLong(patientEntryId));
    }

    @Override
    public Mono<PatientEntry> updatePatientEntry(PatientEntry patientEntry) {
        return this.patientEntryRepository.save(patientEntry);
    }

    @Override
    public Mono<Void> deletePatientEntryById(String patientEntryId) {
        return this.patientEntryRepository.deleteById(Long.parseLong(patientEntryId));
    }
}
