package com.bloom.hospital.repository;

import com.bloom.hospital.model.PatientEntry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientEntryRepository extends ReactiveCrudRepository<PatientEntry, Long> {

}
