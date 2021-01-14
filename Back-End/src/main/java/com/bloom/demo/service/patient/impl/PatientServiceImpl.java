package com.bloom.demo.service.patient.impl;

import com.bloom.demo.model.StatNumbers;
import com.bloom.demo.model.patient.Gender;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.repository.patient.PatientRepository;
import com.bloom.demo.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Mono<Patient> createPatient(Patient patient) {
        return this.patientRepository
                .save(patient)
                .flatMap(savedPatient -> {
                    savedPatient.setPatientAge(this.getAge(savedPatient.getPatientDateOfBirth()));
                    return Mono.just(savedPatient);
                });
    }

    @Override
    public Mono<Patient> updatePatient(Patient patient) {
        return this.patientRepository
                .save(patient)
                .flatMap(savedPatient -> {
                    savedPatient.setPatientAge(this.getAge(savedPatient.getPatientDateOfBirth()));
                    return Mono.just(savedPatient);
                });
    }

    @Override
    public Mono<Patient> getPatientById(String patientId) {
        return this.patientRepository
                .findById(Long.parseLong(patientId))
                .flatMap(patient -> {
                    patient.setPatientAge(this.getAge(patient.getPatientDateOfBirth()));
                    return Mono.just(patient);
                });
    }

    @Override
    public Mono<Void> deletePatientById(String patientId) {
        return this.patientRepository.deleteById(Long.parseLong(patientId));
    }

    @Override
    public Flux<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Mono<StatNumbers> getNumbersAdmitted() {
        return this.patientRepository
                .countPatientsByIsAdmitted(true)
                .flatMap(numberOfAdmitted -> this.patientRepository
                        .count()
                        .flatMap(numberOfPatient -> {
                            StatNumbers statNumbers = new StatNumbers();
                            statNumbers.setX(numberOfAdmitted);
                            statNumbers.setTotal(numberOfPatient);
                            return Mono.just(statNumbers);
                        }));
    }

    @Override
    public Mono<StatNumbers> getNumbersGender() {
        return this.patientRepository
                .countPatientsByGender(Gender.FEMALE)
                .flatMap(numberOfFemales -> this.patientRepository
                        .count().flatMap(numberOfPatients -> {
                            StatNumbers statNumbers = new StatNumbers();
                            statNumbers.setTotal(numberOfPatients);
                            statNumbers.setX(numberOfFemales);
                            return Mono.just(statNumbers);
                        }));
    }

    @Override
    public Mono<StatNumbers> getNumberOfTeens() {
        return this.patientRepository
                .countDistinctByPatientDateOfBirthAfter(LocalDate.now().minusYears(18))
                .flatMap(numberOfTeens -> this.patientRepository
                        .count().flatMap(numberOfPatients -> {
                            StatNumbers statNumbers = new StatNumbers();
                            statNumbers.setTotal(numberOfPatients);
                            statNumbers.setX(numberOfTeens);
                            return Mono.just(statNumbers);
                        }));
    }

    private int getAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
