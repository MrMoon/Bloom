package com.bloom.employee.repository;

import com.bloom.employee.model.Nurse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface NurseRepository extends ReactiveCrudRepository<Nurse , Long> {
}
