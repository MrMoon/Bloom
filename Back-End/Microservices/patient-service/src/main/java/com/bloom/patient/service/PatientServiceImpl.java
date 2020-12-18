package com.bloom.patient.service;

import com.bloom.patient.model.Patient;
import com.bloom.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Mono<Patient> createPatient(Patient patient) {
        return this.patientRepository.save(patient).map(savedPatient -> {
            savedPatient.setPatientAge(getAge(savedPatient.getPatientDateOfBirth()));
            return savedPatient;
        });
    }

    @Override
    public Mono<Patient> updatePatient(Patient patient) {
        return this.patientRepository.save(patient).map(savedPatient -> {
            savedPatient.setPatientAge(getAge(savedPatient.getPatientDateOfBirth()));
            return savedPatient;
        });
    }

    @Override
    public Mono<Patient> getPatientById(Long patientId) {
        return this.patientRepository.findById(patientId).map(patient -> {
            patient.setPatientAge(getAge(patient.getPatientDateOfBirth()));
            return patient;
        });
    }

    @Override
    public Mono<Void> deletePatientById(Long patientId) {
        return this.patientRepository.deleteById(patientId);
    }

    private int getAge(LocalDate birthDate) {
        return Period.between(birthDate , LocalDate.now()).getYears();
    }
}
