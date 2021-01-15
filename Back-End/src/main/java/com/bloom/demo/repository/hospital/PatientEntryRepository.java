package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.model.hospital.PatientEntryType;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientEntryRepository extends ReactiveCrudRepository<PatientEntry, Long> {

    Flux<PatientEntry> findPatientEntriesByPatientDoctorId(Long patientDoctorId);

    Mono<PatientEntry> findPatientEntryByPatientId(Long patientId);

    Mono<Long> countAllByPatientEntryType(PatientEntryType patientEntryType);

    @Query("SELECT COUNT(DISTINCT patient_room_number) FROM patient_entry;")
    Mono<Long> countDistinctRoomUsed();
}
