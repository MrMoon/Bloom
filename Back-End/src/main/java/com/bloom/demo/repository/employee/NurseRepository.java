package com.bloom.demo.repository.employee;

import com.bloom.demo.model.employee.Nurse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface NurseRepository extends ReactiveCrudRepository<Nurse, Long> {
}
