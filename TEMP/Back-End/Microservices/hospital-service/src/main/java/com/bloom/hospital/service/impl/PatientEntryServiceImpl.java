package com.bloom.hospital.service.impl;

import com.bloom.hospital.model.PatientEntry;
import com.bloom.hospital.repository.PatientEntryRepository;
import com.bloom.hospital.service.PatientEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientEntryServiceImpl implements PatientEntryService {

    private final PatientEntryRepository patientEntryRepository;

    @Override
    public Mono<PatientEntry> createPatientEntry(Mono<PatientEntry> patientEntryMono) {
        return patientEntryMono.flatMap(this.patientEntryRepository::save);
    }

    @Override
    public Mono<PatientEntry> getPatientEntryById(String patientEntryId) {
        return this.patientEntryRepository.findById(Long.parseLong(patientEntryId));
    }

    @Override
    public Mono<PatientEntry> updatePatientEntry(Mono<PatientEntry> patientEntryMono) {
        return patientEntryMono.flatMap(this.patientEntryRepository::save);
    }

    @Override
    public Mono<Void> deletePatientEntryById(String patientEntryId) {
        return this.patientEntryRepository.deleteById(Long.parseLong(patientEntryId));
    }
}
