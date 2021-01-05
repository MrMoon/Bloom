package com.bloom.demo.repository.employee;

import com.bloom.demo.model.employee.Doctor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DoctorRepository extends ReactiveCrudRepository<Doctor, Long> {
}
