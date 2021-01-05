package com.bloom.demo.service.employee;

import com.bloom.demo.model.employee.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Mono<Employee> saveEmployee(Employee employee);
    Mono<Employee> updateEmployee(Employee employee);
    Mono<Employee> getEmployeeById(String employeeId);
    Mono<Void> deleteEmployeeById(String employeeId);

}
