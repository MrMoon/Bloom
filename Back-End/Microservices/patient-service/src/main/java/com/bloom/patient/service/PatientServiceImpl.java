package com.bloom.patient.service;

import com.bloom.patient.model.Patient;
import com.bloom.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Mono<Patient> createPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Mono<Patient> updatePatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Mono<Patient> getPatientById(Long patientId) {
        return this.patientRepository.findById(patientId);
    }

    @Override
    public Mono<Void> deletePatientById(Long patientId) {
        return this.patientRepository.deleteById(patientId);
    }
}
