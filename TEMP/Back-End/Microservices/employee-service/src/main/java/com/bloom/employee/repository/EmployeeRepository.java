package com.bloom.employee.repository;

import com.bloom.employee.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee , Long> {

}
