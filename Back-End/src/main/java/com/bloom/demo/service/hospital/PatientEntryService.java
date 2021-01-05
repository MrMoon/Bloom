package com.bloom.demo.service.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.model.patient.Patient;
import reactor.core.publisher.Mono;

public interface PatientEntryService {

    Mono<PatientEntry> createPatientEntry(PatientEntry patientEntry);

    Mono<PatientEntry> getPatientEntryById(String patientEntryId);

    Mono<PatientEntry> updatePatientEntry(PatientEntry patientEntry);

    Mono<Void> deletePatientEntryById(String patientEntryId);

    Mono<Patient> getPatientDetailsById(String patientEntryId);

}
