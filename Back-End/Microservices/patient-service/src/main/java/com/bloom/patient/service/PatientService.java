package com.bloom.patient.service;

import com.bloom.patient.model.Patient;
import reactor.core.publisher.Mono;

public interface PatientService {

    Mono<Patient> createPatient(Patient patient);
    Mono<Patient> updatePatient(Patient patient);
    Mono<Patient> getPatientById(Long patientId);
    Mono<Void> deletePatientById(Long patientId);

}
