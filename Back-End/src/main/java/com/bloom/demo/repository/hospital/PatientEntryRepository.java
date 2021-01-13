package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientEntryRepository extends ReactiveCrudRepository<PatientEntry, Long> {

    Flux<PatientEntry> findPatientEntriesByPatientDoctorId(Long patientDoctorId);

    Mono<PatientEntry> findPatientEntryByPatientId(Long patientId);
}
