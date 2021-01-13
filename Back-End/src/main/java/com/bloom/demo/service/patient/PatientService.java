package com.bloom.demo.service.patient;

import com.bloom.demo.model.patient.Patient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientService {

    Mono<Patient> createPatient(Patient patient);

    Mono<Patient> updatePatient(Patient patient);

    Mono<Patient> getPatientById(String patientId);

    Mono<Void> deletePatientById(String patientId);

    Flux<Patient> getAll();

}
