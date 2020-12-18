package com.bloom.patient.repository;

import com.bloom.patient.model.Patient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientRepository extends ReactiveCrudRepository<Patient , Long> {

}
