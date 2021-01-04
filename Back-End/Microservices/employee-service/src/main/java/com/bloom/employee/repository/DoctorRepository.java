package com.bloom.employee.repository;

import com.bloom.employee.model.Doctor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DoctorRepository extends ReactiveCrudRepository<Doctor , Long> {

}
