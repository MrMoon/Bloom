package com.bloom.demo.service.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import reactor.core.publisher.Mono;

public interface PatientEntryService {

    Mono<PatientEntry> createPatientEntry(PatientEntry patientEntry);

    Mono<PatientEntry> getPatientEntryById(String patientEntryId);

    Mono<PatientEntry> updatePatientEntry(PatientEntry patientEntry);

    Mono<Void> deletePatientEntryById(String patientEntryId);

}
