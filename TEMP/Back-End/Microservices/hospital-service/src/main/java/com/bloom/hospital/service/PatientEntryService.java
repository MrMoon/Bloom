package com.bloom.hospital.service;

import com.bloom.hospital.model.PatientEntry;
import reactor.core.publisher.Mono;

public interface PatientEntryService {

    Mono<PatientEntry> createPatientEntry(Mono<PatientEntry> patientEntryMono);

    Mono<PatientEntry> getPatientEntryById(String patientEntryId);

    Mono<PatientEntry> updatePatientEntry(Mono<PatientEntry> patientEntryMono);

    Mono<Void> deletePatientEntryById(String patientEntryId);

}
