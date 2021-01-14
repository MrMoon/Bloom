package com.bloom.demo.repository.patient;

import com.bloom.demo.model.patient.Gender;
import com.bloom.demo.model.patient.Patient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PatientRepository extends ReactiveCrudRepository<Patient, Long> {

    Mono<Long> countPatientsByIsAdmitted(boolean isAdmitted);

    Mono<Long> countPatientsByGender(Gender gender);

    Mono<Long> countDistinctByPatientDateOfBirthAfter(LocalDate localDate);
}
