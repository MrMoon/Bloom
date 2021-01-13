package com.bloom.demo.repository.patient;

import com.bloom.demo.model.patient.Patient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientRepository extends ReactiveCrudRepository<Patient, Long> {

}
