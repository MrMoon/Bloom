package com.bloom.demo.repository.employee;

import com.bloom.demo.model.employee.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

}
