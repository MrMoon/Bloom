package com.bloom.employee.service;

import com.bloom.employee.model.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Mono<Employee> saveEmployee(Employee employee);
    Mono<Employee> updateEmployee(Employee employee);
    Mono<Employee> getEmployeeById(Long employeeId);
    Mono<Void> deleteEmployeeById(Long employeeId);

}
