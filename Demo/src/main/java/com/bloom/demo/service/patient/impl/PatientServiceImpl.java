package com.bloom.demo.service.patient.impl;

import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.repository.patient.PatientRepository;
import com.bloom.demo.service.employee.DoctorService;
import com.bloom.demo.service.hospital.RoomService;
import com.bloom.demo.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final RoomService roomService;
    private final DoctorService doctorService;

    @Override
    public Mono<Patient> createPatient(Patient patient) {
        return this.patientRepository.save(patient).flatMap(savedPatient -> {
            savedPatient.setPatientAge(this.getAge(savedPatient.getPatientDateOfBirth()));
            return Mono.just(savedPatient);
        });
    }

    @Override
    public Mono<Patient> updatePatient(Patient patient) {
        return this.patientRepository.save(patient).flatMap(savedPatient -> {
            savedPatient.setPatientAge(this.getAge(savedPatient.getPatientDateOfBirth()));
            return Mono.just(savedPatient);
        });
    }

    @Override
    public Mono<Patient> getPatientById(String patientId) {
        return this.patientRepository.findById(Long.parseLong(patientId)).flatMap(patient -> {
            patient.setPatientAge(this.getAge(patient.getPatientDateOfBirth()));
            return Mono.just(patient);
        });
    }

    @Override
    public Mono<Void> deletePatientById(String patientId) {
        return this.patientRepository.deleteById(Long.parseLong(patientId));
    }

    private int getAge(LocalDate birthDate) {
        return Period.between(birthDate , LocalDate.now()).getYears();
    }
}
