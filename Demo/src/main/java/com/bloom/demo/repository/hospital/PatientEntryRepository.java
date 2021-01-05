package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientEntryRepository extends ReactiveCrudRepository<PatientEntry, Long> {

}
